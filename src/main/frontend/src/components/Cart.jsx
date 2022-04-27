import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import "./Cart.css";
import CartItem from "./CartItem";

function Cart({ children }) {
  const { cartItems } = useSelector((state) => state.cart);

  return (
    <div className="dropdown">
      <div className="dropdown-inner">
        {cartItems.map(menuItem => (<CartItem key={menuItem.id} menuItem={menuItem}/>))}
      </div>
      <Link to="/checkout">
        <button className="btn btn-black dropdown-btn">Checkout</button>
      </Link>
    </div>
  );
}

export default Cart;
