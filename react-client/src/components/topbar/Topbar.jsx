import "./topbar.css";
import { Link } from "react-router-dom";
import React, { useContext, useState } from "react";
import { AuthContext } from "../../context/AuthContext";
import { Avatar, Box, Menu, MenuItem } from "@material-ui/core";

export default function Topbar() {
  const { user } = useContext(AuthContext);
  const { isFetching, dispatch } = useContext(AuthContext);
  const [anchorEl, setAnchorEl] = React.useState(null);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    localStorage.removeItem('user');
    dispatch({ type: "LOGOUT" });
    setAnchorEl(null);
  };
  const AD = process.env.REACT_APP_AVATAR_DEFAULT;
  return (
    <div className="topbarContainer">
      <div className="topbarLeft">
        <Link to="/" style={{ textDecoration: "none" }}>
          <span className="logo">Chat App</span>
        </Link>
      </div>
      <div className="topbarRight">
        <div className="topbarUserName">
          <span>{user.fullName}</span>
        </div>
        <Box className="topbarUserAvatar" 
          aria-controls="simple-menu" aria-haspopup="true"
        >
          <Avatar src={user?.profilePicture || AD}  onClick={handleClick}/>
            <Menu id="simple-menu"
              anchorEl={anchorEl}
              keepMounted
              open={Boolean(anchorEl)}
              onClose={handleClose}>
                  <MenuItem onClick={handleClose}>Đăng xuất</MenuItem>
          </Menu>
        </Box>

      </div>
    </div>
  );
}
