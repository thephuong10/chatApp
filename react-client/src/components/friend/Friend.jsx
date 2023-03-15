
import "./friend.css";


export default function Friend({ user }) {
  const AD = process.env.REACT_APP_ADATAR_DEFAULT;
  return (
    <div className="friend">
      <img
        className="friendImg"
        src={
          user?.profilePicture || AD
        }
        alt=""
      />
      <span className="friendName">{user?.fullName}</span>
    </div>
  );
}
