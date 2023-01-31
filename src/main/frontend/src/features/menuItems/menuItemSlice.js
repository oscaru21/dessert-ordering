import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import menuItemService from "./menuItemService";

const initialState = {
  menuItems: [],
  menuItem: null,
  isLoading: false,
  isError: false,
  isSuccess: false,
  message: "",
};

//get menu items
export const getMenuItems = createAsyncThunk(
  "menuItems/getAll",
  async (_, thunkAPI) => {
    try {
      return await menuItemService.getMenuItems();
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

//get menu item
export const getMenuItem = createAsyncThunk(
  "menuItems/getItem",
  async (menuItemId, thunkAPI) => {
    try {
      return await menuItemService.getMenuItem(menuItemId);
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

//create menu item
export const createMenuItem = createAsyncThunk(
  "menuItems/createItem",
  async (menuItemData, thunkAPI) => {
    try {
      const token = thunkAPI.getState().auth.user.token
      return await menuItemService.createMenuItem(menuItemData, token);
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

export const menuItemSlice = createSlice({
  name: "menuItems",
  initialState,
  reducers: {
    reset: (state) => initialState,
  },
  extraReducers: (builder) => {
    builder
      .addCase(getMenuItems.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getMenuItems.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isSuccess = true;
        state.menuItems = action.payload;
      })
      .addCase(getMenuItems.rejected, (state, action) => {
        state.isError = true;
        state.isLoading = false;
        state.message = action.payload;
      })
      .addCase(getMenuItem.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(getMenuItem.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isSuccess = true;
        state.menuItem = action.payload;
      })
      .addCase(getMenuItem.rejected, (state, action) => {
        state.isError = true;
        state.isLoading = false;
        state.message = action.payload;
      })
      .addCase(createMenuItem.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(createMenuItem.fulfilled, (state, action) => {
        state.isLoading = false;
        state.isSuccess = true;
        state.menuItem = action.payload;
      })
      .addCase(createMenuItem.rejected, (state, action) => {
        state.isError = true;
        state.isLoading = false;
        state.message = action.payload;
      });
  },
});

export const { reset } = menuItemSlice.actions;
export default menuItemSlice.reducer;
