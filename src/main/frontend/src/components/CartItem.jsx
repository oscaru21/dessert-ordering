import { FaTrash } from "react-icons/fa";
import { useDispatch } from "react-redux";
import {deleteItem} from '../features/cart/cartSlice'

function CartItem({ menuItem }) {
  const dispatch = useDispatch()
  return (
    <div className="cart-item">
      <div className="cart-item-content">
        <div className="cart-item-image">
          <img src={menuItem.imgUrl} alt="" />
        </div>
        <div className="cart-item-details">
          <div className="cart-item-name">{menuItem.name}</div>
          <div className="cart-item-price">${menuItem.price}</div>
        </div>
        <div className="cart-item-quantity">
          <p>Qty</p>
          {menuItem.qty}
        </div>
      </div>
      <div className="cart-item-delete">
        <button className="btn btn-delete" onClick={() => dispatch(deleteItem(menuItem))}>
          <FaTrash />
        </button>
      </div>
    </div>
  );
}

export default CartItem;
