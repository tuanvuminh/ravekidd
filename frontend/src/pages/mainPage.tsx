import "../styles/mainPage.css";
import { Post } from "../components/post";

export const MainPage = () => {
  return (
    <div>
      <div className="createPostContainer">
        <input className="descriptionInput" type="text" placeholder="LINK" />
        <input className="linkInput" type="text" placeholder="DESCRIPTION..." />
        <div className="postButton">POST</div>
      </div>
      <div>
        <Post />
        <Post />
      </div>
    </div>
  );
};
