import axios from "axios";

const BASE_URL = process.env.REACT_APP_BASE_URL;

export const loginCall = async (userCredential, dispatch) => {
  dispatch({ type: "LOGIN_START" });
  try {
    const res = await axios.post(`${BASE_URL}/api/user/login`, null,{
      params:{
        email:userCredential.email,
        password:userCredential.password
      }
    });
    dispatch({ type: "LOGIN_SUCCESS", payload: res.data });
  } catch (err) {
    dispatch({ type: "LOGIN_FAILURE", payload: err });
  }
};

