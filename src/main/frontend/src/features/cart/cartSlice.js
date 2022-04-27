import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

const cartItems = JSON.parse(localStorage.getItem('cart'))

const initialState = {
    cartItems: cartItems ? cartItems : [],
}

export const addItem = createAsyncThunk('cart/add', (menuItem) => {
    return {...menuItem, qty: '1'}
})

export const deleteItem = createAsyncThunk('cart/delete', (menuItem) => {
    return menuItem
})

export const changeQty = createAsyncThunk('cart/changeQty', ({id, qty}) => {
    return {id, qty}
})

const cartSlice = createSlice({
    name: 'cart',
    initialState,
    reducers: {
        reset: (state) => initialState
    },
    extraReducers: (builder) => {
        builder
            .addCase(addItem.fulfilled, (state, action) => {
                state.cartItems.push(action.payload)
                localStorage.setItem('cart', JSON.stringify(state.cartItems))
            })
            .addCase(deleteItem.fulfilled, (state, action) => {
                state.cartItems = state.cartItems.filter(item => item.id !== action.payload.id)
                localStorage.setItem('cart', JSON.stringify(state.cartItems))
            })
            .addCase(changeQty.fulfilled, (state, action) => {
                state.cartItems = state.cartItems.filter(item => item.id === action.payload.id ? (item.qty=action.payload.qty) : (item.qty))
                localStorage.setItem('cart', JSON.stringify(state.cartItems))
            })
    }
})

export const {reset} = cartSlice.actions
export default cartSlice.reducer