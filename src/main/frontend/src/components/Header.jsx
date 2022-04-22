import { FaSignInAlt, FaSignOutAlt, FaUser } from "react-icons/fa";
import { useSelector, useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { logout, reset } from "../features/auth/authSlice";
import "./Header.css";

function Header() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { user } = useSelector((state) => state.auth);

  const onLogout = () => {
    dispatch(logout());
    dispatch(reset());
    navigate("/");
  };

  return (
    <header className="header">
      <div className="logo">
        <Link to="/">Ila's Bake</Link>
      </div>
      <ul>
        {user ? (
          <>
            {user.isAdmin && 
              <li>
              <Link to="/admin">
                <button className="btn btn-reverse">
                   Admin
                </button>
              </Link>
            </li>
            }
            <li>
              <button className="btn" onClick={onLogout}>
                <FaSignOutAlt /> Logout
              </button>
            </li>
          </>
        ) : (
          <>
            <li>
              <Link to="/login">
                <div className="btn btn-reverse">
                  <FaSignInAlt /> Login
                </div>
              </Link>
            </li>
            <li>
              <Link to="/register">
                <div className="btn">
                  <FaUser /> Register
                </div>
              </Link>
            </li>
          </>
        )}
      </ul>
    </header>
  );
}

export default Header;
