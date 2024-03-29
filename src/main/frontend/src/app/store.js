import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../features/auth/authSlice'
import menuItemReducer from '../features/menuItems/menuItemSlice'
import itemReviewReducer from '../features/itemReviews/itemReviewSlice'
import cartReducer from '../features/cart/cartSlice'

export const store = configureStore({
  reducer: {
    auth: authReducer,
    menuItems: menuItemReducer,
    itemReviews: itemReviewReducer,
    cart: cartReducer,
  },
});
