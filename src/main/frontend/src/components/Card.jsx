import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { addItem } from "../features/cart/cartSlice";
import "./Card.css";
import NumberChanger from "./NumberChanger";

function Card({ cardData }) {
  const { id, imgUrl, name, description, price } = cardData;
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const { user } = useSelector((state) => state.auth);
  const { cartItems } = useSelector((state) => state.cart);

  const cartItem = cartItems.find(item => item.id === id) 

  const onCardClick = (e) => {
    if (!e.target.classList.contains("btn")) {
      navigate(`/menu-items/${id}`);
    }
  };
  return (
    <div className="card" onClick={(e) => onCardClick(e)}>
      <div className="card-image">
        <img src={imgUrl} alt="default" />
      </div>
      <div className="card-details">
        <div className="card-title">
          <h3>{name}</h3>
        </div>
        <div className="card-description">
          <p>{description}</p>
        </div>
        <div className="card-price">
          <p>${price}</p>
          {cartItem ? (
            <NumberChanger currentNumber={cartItem.qty} id={cartItem.id} />
          ) : (
            <>
              {user && (
                <button
                  className="btn btn-card"
                  onClick={() => dispatch(addItem(cardData))}
                >
                  +
                </button>
              )}
            </>
          )}
        </div>
      </div>
    </div>
  );
}

export default Card;
