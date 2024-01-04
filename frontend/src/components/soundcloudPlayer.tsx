import React from "react";
import "../styles/post.css";

interface SoundCloudPlayerProps {
  url: string;
}

export const SoundCloudPlayer: React.FC<SoundCloudPlayerProps> = ({ url }) => {
  return (
    <div className="soundcloud-player">
      <iframe
        title="SoundCloud Player"
        width="650"
        height="200"
        scrolling="no"
        frameBorder="no"
        src={`https://w.soundcloud.com/player/?url=${encodeURIComponent(url)}`}
      ></iframe>
    </div>
  );
};
