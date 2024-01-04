import React, { useState, useEffect } from "react";
import { ProgressBar } from "../components/progressBar";
import axios from "axios";
import "../styles/loginPage.css";

export const LoginPage = () => {
  const [userName, setUsername] = useState("");
  const [invitationCode, setInvitationCode] = useState("");
  const [isDarkMode, setIsDarkMode] = useState(true);
  const [isLoading, setIsLoading] = useState(true);
  const [shouldAnimateFont, setShouldAnimateFont] = useState(false);
  const [error, setError] = useState(false);

  const toggleDarkMode = () => {
    setIsDarkMode(!isDarkMode);
  };

  const handleUsernameChange = (event: any) => {
    setUsername(event.target.value);
  };

  const handleInvitationCodeChange = (event: any) => {
    setInvitationCode(event.target.value);
  };

  useEffect(() => {
    const body = document.body;
    if (isDarkMode) {
      body.classList.add("dark");
    } else {
      body.classList.remove("dark");
    }
  }, [isDarkMode]);

  useEffect(() => {
    setTimeout(() => {
      setIsLoading(false);
    }, 3000);
  }, []);

  const handleEnterButtonClick = async () => {
    try {
      const postData = {
        username: userName,
        password: invitationCode,
      };

      const response = await axios.post(
        "http://localhost:8080/login",
        postData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 200) {
        const token = response.data.accessToken;
        localStorage.setItem("token", token);
        console.log("Login was successful.");

        setShouldAnimateFont(true);
        setTimeout(() => {
          window.location.href = "/main";
        }, 2800);
      } else {
        console.log("Server error:", response.data);
        setError(true);
      }
    } catch (error) {
      console.error("Login error:", error);
      setError(true);
    }
  };

  return (
    <div>
      {isLoading ? (
        <div className="backgroundImage">
          <ProgressBar />
        </div>
      ) : (
        <div className="content">
          {
            <div className={`loginContainer ${isDarkMode ? "dark" : ""}`}>
              <button className="darkModeButton" onClick={toggleDarkMode}>
                {isDarkMode ? "LIGHT MODE" : "DARK MODE"}
              </button>

              <h1 className={`logo ${shouldAnimateFont ? "animateFont" : ""}`}>
                ravekidd
              </h1>

              <input
                className={`${
                  userName ? "usernameInput hasText" : "usernameInput"
                } ${isDarkMode ? "dark" : ""}`}
                type="text"
                placeholder="USERNAME"
                value={userName}
                onChange={handleUsernameChange}
                onKeyDown={(e) => {
                  if (e.key === "Enter") {
                    handleEnterButtonClick();
                  }
                }}
              />

              <input
                className={`${
                  invitationCode ? "codeInput hasText" : "codeInput"
                } ${isDarkMode ? "dark" : ""}`}
                type="text"
                placeholder="INVITATION CODE"
                value={invitationCode}
                onChange={handleInvitationCodeChange}
                onKeyDown={(e) => {
                  if (e.key === "Enter") {
                    handleEnterButtonClick();
                  }
                }}
              />
              <button
                className={`enterButton ${isDarkMode ? "dark" : ""}`}
                onClick={handleEnterButtonClick}
              >
                ENTER
              </button>
              {error && <p>COULD NOT ACCESS.</p>}
            </div>
          }
        </div>
      )}
    </div>
  );
};
