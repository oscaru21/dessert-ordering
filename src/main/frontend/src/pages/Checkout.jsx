import { useSelector } from "react-redux";
import CartItem from "../components/CartItem";

function Checkout() {
  const { cartItems } = useSelector((state) => state.cart);

  return (
    <div className='page menu-item-product'>
    <div className="grid">
      <div className="checkout-items">
      {cartItems.map((menuItem) => (
        <CartItem key={menuItem.id} menuItem={menuItem} />
      ))}
      </div>
      <div className="checkout-resume">
        <h1>Checkout</h1>
        <button className="btn btn-black btn-block">
          Pay
        </button>
      </div>
    </div>
      
    </div>
  );
}

export default Checkout;
