import React from "react";
import { useNavigate } from "react-router-dom";
import "./Card.css";

function Card({ cardData }) {
    const {id, imgUrl, name, description, price} = cardData
    const navigate = useNavigate()

    const onCardClick = () => {
      navigate(`/menu-items/${id}`)
    }
  return (
    <div className="card" onClick={onCardClick}>
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
        </div>
      </div>
    </div>
  );
}

export default Card;
