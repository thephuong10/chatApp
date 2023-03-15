import "./message.css";
import { format } from "timeago.js";
const AD = process.env.REACT_APP_AVATAR_DEFAULT;
export default function Message({ message, own , friendImg }) {
  return (
    <div className={own ? "message own" : "message"}>
      <div className="messageTop">
       {!own ?  <img
          className="messageImg"
          src={friendImg || AD}
          alt=""
        /> : <></>}
        <p className="messageText">{message.text}</p>
      </div>
      <div className="messageBottom">{format(message.date)}</div>
    </div>
  );
}
