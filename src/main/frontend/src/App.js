import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AdminRoute from "./components/AdminRoute";
import Header from "./components/Header";
import AdminPage from "./pages/AdminPage";
import Home from "./pages/Home";
import Login from "./pages/Login";
import MenuItem from "./pages/MenuItem";
import Register from "./pages/Register";

function App() {
  return (
    <div>
      <Router>
        <div className="container">
          <Header/>
          <div className="pages">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/menu-items/:menuItemId" element={<MenuItem />} />
            <Route path="/admin" element={<AdminRoute/>}>
              <Route path="/admin" element={<AdminPage />}/>
            </Route>
          </Routes>
          </div>
        </div>
      </Router>
      <ToastContainer/>
    </div>
  );
}

export default App;
