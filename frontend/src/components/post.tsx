import React, { useState } from "react";
import { SoundCloudPlayer } from "./soundcloudPlayer";
import "../styles/post.css";

export const Post = () => {
  const [isColored, setIsColored] = useState(false);

  const handleClick = () => {
    setIsColored(!isColored);
  };

  return (
    <div>
      <div className="description">RAVEKIDD01: "Lovely music"</div>
      <div className="postContainer">
        <img
          src={require("../assets/icons/profilePhoto.jpg")}
          alt="Icon"
          className="profileImage"
        />

        <div className="postPlayer">
          <SoundCloudPlayer url="https://soundcloud.com/shtgnfestival/shtgn-pdcst-45-servicebot" />
        </div>

        <div
          className={isColored ? "heartLiked" : "heart"}
          onClick={handleClick}
        >
          🖤
        </div>
      </div>
    </div>
  );
};
