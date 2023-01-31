import { useDispatch } from "react-redux"
import {changeQty} from '../features/cart/cartSlice'

function NumberChanger({id, currentNumber}) {
    const dispatch = useDispatch()

  return (
    <div className="btn-direction">
    <button
        className="btn btn-card btn-left"
        onClick={() => dispatch(changeQty({id, qty: parseInt(currentNumber) - 1}))}
      >
        -
      </button>
      <p className="btn-number">{currentNumber}</p>
      <button
        className="btn btn-card btn-right"
        onClick={() => dispatch(changeQty({id, qty: parseInt(currentNumber) + 1}))}
      >
        +
      </button>
  </div>
  )
}

export default NumberChanger