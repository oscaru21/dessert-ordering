import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import cartService from "./cartService";

const cartItems = JSON.parse(localStorage.getItem("cart"));

const initialState = {
  cartItems: cartItems ? cartItems : [],
  isLoading: false,
  isSuccess: false,
  isError: false,
  message: ''
};

export const addItem = createAsyncThunk("cart/add", (menuItem) => {
  return { ...menuItem, qty: "1" };
});

export const deleteItem = createAsyncThunk("cart/delete", (menuItem) => {
  return menuItem;
});

export const changeQty = createAsyncThunk("cart/changeQty", ({ id, qty }) => {
  return { id, qty };
});

export const createOrder = createAsyncThunk(
  "cart/createOrder",
  async (_, thunkAPI) => {
    const orderData = {
      address: "calle la sabana pol d-5 casa #2",
      note: "order",
      cartItems: thunkAPI.getState().cart.cartItems,
    };
    try {
      const token = thunkAPI.getState().auth.user.token;
      return await cartService.createOrder(orderData, token);
    } catch (error) {
      const message =
        (error.response &&
          error.response.data &&
          error.response.data.message) ||
        error.message ||
        error.toString();

      return thunkAPI.rejectWithValue(message);
    }
  }
);

const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    reset: (state) => ({...initialState, cartItems: state.cartItems}),
  },
  extraReducers: (builder) => {
    builder
      .addCase(addItem.fulfilled, (state, action) => {
        state.cartItems.push(action.payload);
        localStorage.setItem("cart", JSON.stringify(state.cartItems));
      })
      .addCase(deleteItem.fulfilled, (state, action) => {
        state.cartItems = state.cartItems.filter(
          (item) => item.id !== action.payload.id
        );
        localStorage.setItem("cart", JSON.stringify(state.cartItems));
      })
      .addCase(changeQty.fulfilled, (state, action) => {
        state.cartItems = state.cartItems.filter((item) =>
          item.id === action.payload.id
            ? (item.qty = action.payload.qty)
            : item.qty
        );
        localStorage.setItem("cart", JSON.stringify(state.cartItems));
      })
      .addCase(createOrder.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(createOrder.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isSuccess = true;
        state.cartItems = []
        console.log(action.payload)
        localStorage.removeItem('cart')
      })
      .addCase(createOrder.rejected, (state, action) => {
        state.isError = true;
        state.isLoading = false;
        state.message = action.payload;
      });
  },
});

export const { reset } = cartSlice.actions;
export default cartSlice.reducer;
