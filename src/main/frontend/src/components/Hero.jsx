import React from "react";
import { FaSignInAlt, FaUser } from "react-icons/fa";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";
import "./Hero.css";

function Hero() {
  const { user } = useSelector((state) => state.auth);
  return (
    <>
      <section className="hero">
        <div className="hero-overlay">
          <header className="hero-header">
            <h2>Everything tastes better in</h2>
            <h1>
              <mark>Ila's Bake</mark>
            </h1>
          </header>
          {!user && (
            <div className="hero-footer">
              <ul>
                <li>
                  <Link to="/register">
                    <div className="btn">
                      <FaUser /> Register
                    </div>
                  </Link>
                </li>
                <li>
                  <Link to="/login">
                    <div className="btn btn-reverse">
                      <FaSignInAlt /> Login
                    </div>
                  </Link>
                </li>
              </ul>
            </div>
          )}
        </div>
      </section>
    </>
  );
}

export default Hero;
