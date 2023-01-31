import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useLocation } from "react-router-dom";

function NavItem({ children, icon }) {
    const [isOpen, setIsOpen] = useState(false)
    const { cartItems } = useSelector((state) => state.cart);

    const location = useLocation()

    useEffect(() => {
      setIsOpen(false)
    }, [location])

  return (
    <li>
      <button className="btn btn-secondary" onClick={() => setIsOpen(!isOpen)}>
        {icon}
        {cartItems.reduce((acc, el) => acc + parseInt(el.qty), 0)}
      </button>
      {isOpen && children}
    </li>
  );
}

export default NavItem;
