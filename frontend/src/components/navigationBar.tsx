import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../styles/navigationBar.css";
import "../styles/sides.css";

export const NavigationBar = (props: any) => {
  const [isDarkMode, setIsDarkMode] = useState(false);
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isFlipped, setIsFlipped] = useState(false);

  const handleFlipClick = () => {
    setIsFlipped(!isFlipped);
  };

  const toggleDarkMode = () => {
    setIsDarkMode(!isDarkMode);
  };

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  useEffect(() => {
    const body = document.body;
    if (isDarkMode) {
      body.classList.add("dark");
    } else {
      body.classList.remove("dark");
    }
  }, [isDarkMode]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    props.onLogout();
  };

  return (
    <div>
      <div className={`navigationContainer ${isDarkMode ? "dark" : ""}`}>
        <button className="darkModeButton" onClick={toggleDarkMode}>
          {isDarkMode ? "LIGHT MODE" : "DARK MODE"}
        </button>

        <Link
          to={"/ambient"}
          className={`ambient-nav ${isDarkMode ? "dark" : ""}`}
        >
          AMBIENT
        </Link>
        <Link to={"/main"} className={`main-nav ${isDarkMode ? "dark" : ""}`}>
          MAIN
        </Link>
        <Link to={"/dark"} className={`dark-nav ${isDarkMode ? "dark" : ""}`}>
          DARK
        </Link>
        <div
          className={`menu-nav ${isDarkMode ? "dark" : ""}`}
          onClick={toggleMenu}
        >
          MENU
          {isMenuOpen && (
            <div className="menu-links">
              <Link to="/profile" className="profileButton">
                PROFILE
              </Link>
              <div className="logOutButton" onClick={handleLogout}>
                LOG OUT
              </div>
            </div>
          )}
        </div>
      </div>

      <div className="sidesContainer">
        <div
          className={`rightSide ${isFlipped ? "flipped" : ""} ${
            isDarkMode ? "rightSideDark" : ""
          }`}
          onClick={handleFlipClick}
        ></div>
        <div
          className={`leftSide ${isFlipped ? "flipped" : ""} ${
            isDarkMode ? "leftSideDark" : ""
          }`}
          onClick={handleFlipClick}
        ></div>
      </div>
    </div>
  );
};
