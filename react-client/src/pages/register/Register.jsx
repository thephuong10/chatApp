import axios from "axios";
import { useRef } from "react";
import "./register.css";
import { useHistory } from "react-router";

const BASE_URL = process.env.REACT_APP_BASE_URL;


export default function Register() {
  const fullName = useRef();
  const email = useRef();
  const password = useRef();
  const passwordAgain = useRef();
  const history = useHistory();

  const handleClick = async (e) => {
    e.preventDefault();
    if (passwordAgain.current.value !== password.current.value) {
      passwordAgain.current.setCustomValidity("Passwords don't match!");
    } else {
      const user = {
        fullName: fullName.current.value,
        email: email.current.value,
        password: password.current.value,
      };
      try {
        await axios.post(`${BASE_URL}/api/user/signup`, user,{
          headers: {
            'Content-Type': 'application/json'
            }
        });
        history.push("/login");
      } catch (err) {
        console.log(err);
      }
    }
  };

  return (
    <div className="login">
      <div className="loginWrapper">
        <div className="loginLeft">
          <h3 className="loginLogo">ChatApp</h3>
          <span className="loginDesc">
            Connect with friends and the world around you on Lamasocial.
          </span>
        </div>
        <div className="loginRight">
          <form className="loginBox" onSubmit={handleClick}>
            <input
              placeholder="fullName"
              required
              ref={fullName}
              className="loginInput"
            />
            <input
              placeholder="Email"
              required
              ref={email}
              className="loginInput"
              type="email"
            />
            <input
              placeholder="Password"
              required
              ref={password}
              className="loginInput"
              type="password"
              minLength="6"
            />
            <input
              placeholder="Password Again"
              required
              ref={passwordAgain}
              className="loginInput"
              type="password"
            />
            <button className="loginButton" type="submit">
              Sign Up
            </button>
            <button className="loginRegisterButton" onClick={()=>history.push("/login")}>Log into Account</button>
          </form>
        </div>
      </div>
    </div>
  );
}
