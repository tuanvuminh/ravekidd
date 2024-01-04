import { NavigationBar } from "../components/navigationBar";
import { Post } from "../components/post";
import "../styles/profile.css";

export const Profile = () => {
  return (
    <div>
      <div className="profileContainer">
        <div className="yourPosts">YOUR POSTS</div>
        <div className="updateContainer">
          <div className="updateUserName">RAVEKIDD01</div>
          <div className="updateProfilePhoto">
            <img
              src={require("../assets/icons/profilePhoto.jpg")}
              alt="Icon"
              className="userPhoto"
            />
          </div>
        </div>
        <div className="likedPosts">LIKED POSTS</div>
      </div>

      <div className="profilePost">
        <Post />
      </div>
    </div>
  );
};
