import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import { log } from "react-modal/lib/helpers/ariaAppHider";
import itemReviewService from "./itemReviewService";

const initialState = {
  itemReviews: [],
  itemReview: {},
  isLoading: false,
  isSuccess: false,
  isError: false,
  message: "",
};

//get all item reviews
export const getItemReviews = createAsyncThunk(
  "itemReviews/getAll",
  async (menuItemId, thunkAPI) => {
    try {
      return await itemReviewService.getItemReviews(menuItemId);
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

//create new review
export const createItemReview = createAsyncThunk(
  "itemReviews/createReview",
  async (itemReviewData, thunkAPI) => {
      const {menuItemId, text} = itemReviewData
    try {
      const token = thunkAPI.getState().auth.user.token;
      return await itemReviewService.createItemReview(
        menuItemId,
        text,
        token
      );
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

export const itemReviewSlice = createSlice({
  name: "itemReviews",
  initialState,
  reducers: {
    reset: (state) => initialState,
  },
  extraReducers: (builder) => {
    builder
      .addCase(getItemReviews.fulfilled, (state, action) => {
        state.isSuccess = true;
        state.isLoading = false;
        state.itemReviews = action.payload;
      })
      .addCase(getItemReviews.rejected, (state, action) => {
        state.isError = true;
        state.isLoading = false;
        state.message = action.payload;
      })
      .addCase(getItemReviews.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(createItemReview.fulfilled, (state, action) => {
        state.isSuccess = true;
        state.isLoading = false;
        state.itemReview = action.payload;
      })
      .addCase(createItemReview.rejected, (state, action) => {
        state.isError = true;
        state.isLoading = false;
        state.message = action.payload;
      })
      .addCase(createItemReview.pending, (state) => {
        state.isLoading = true;
      });
  },
});

export const { reset } = itemReviewSlice.actions;
export default itemReviewSlice.reducer;
