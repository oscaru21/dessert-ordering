import { useSelector, useDispatch } from "react-redux";
import { createOrder, reset } from '../features/cart/cartSlice'
import CartItem from "../components/CartItem";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Checkout() {
  const { cartItems, isSuccess } = useSelector((state) => state.cart);
  
  const dispatch = useDispatch()
  const navigate = useNavigate()

  useEffect(() => {
    if(isSuccess){
      navigate('/')
    }

    return () => {
      dispatch(reset())
    }
  }, [isSuccess, navigate])


  return (
    <div className="page menu-item-product">
      <div className="grid">
        <div className="checkout-items">
          {cartItems.map((menuItem) => (
            <CartItem key={menuItem.id} menuItem={menuItem} />
          ))}
        </div>
        <div className="checkout-resume">
          <h1>Order Sumary</h1>
          <div className="checkout-element">
            <h4>
              Items ({cartItems.reduce((acc, el) => acc + parseInt(el.qty), 0)}
              )
            </h4>
          </div>
          <div className="checkout-element">
            <h4>Shiping and Handling: </h4>
            <h4>$0.00</h4>
          </div>
          <div className="checkout-element">
            <h4>Total before taxes:</h4>
            <h4>
              $
              {cartItems.reduce(
                (total, item) => total + item.price * item.qty,
                0
              )}
            </h4>
          </div>
          <div className="checkout-element">
            <h2>Order total:</h2>
            <h2>
              $
              {cartItems.reduce(
                (total, item) => total + item.price * item.qty,
                0
              )}
            </h2>
          </div>

          <div className="div">
            <button className="btn btn-black btn-block" onClick={() => dispatch(createOrder())}>Pay</button>
            <p>
              Once your order has been processed you will receive a message once
              your dessert is ready
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Checkout;
