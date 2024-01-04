import React, { useEffect, useState } from "react";
import "../styles/loginPage.css";

export const ProgressBar = () => {
  const [filled, setFilled] = useState(0);
  const isRunning = true;

  useEffect(() => {
    if (filled < 100 && isRunning) {
      setTimeout(() => setFilled((prev) => (prev += 2)), 50);
    }
  }, [filled, isRunning]);

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        flexDirection: "column",
      }}
    >
      <div className="progressBar">
        <div
          style={{
            height: "100%",
            width: `${filled}%`,
            backgroundColor: "black",
            transition: "width 1s",
          }}
        ></div>
        <span className="progressPercent">{filled}%</span>
      </div>
      <div className="loadingText">LOADING</div>
    </div>
  );
};
