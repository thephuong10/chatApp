import "./messenger.css";
import Topbar from "../../components/topbar/Topbar";
import Message from "../../components/message/Message";
import { useContext, useEffect, useRef, useState } from "react";
import { AuthContext } from "../../context/AuthContext";
import axios from "axios";
import {over} from 'stompjs';
import SockJS from 'sockjs-client';
import Friend from "../../components/friend/Friend";


const BASE_URL = process.env.REACT_APP_BASE_URL;

export default function Messenger() {
  const [friends, setFriends] = useState([]);
  const [currentChat, setCurrentChat] = useState(null);
  const [messages, setMessages] = useState([]);
  const [newMessage, setNewMessage] = useState("");
  const stompClient = useRef();
  const { user } = useContext(AuthContext);
  const scrollRef = useRef();

  console.log('messages',messages);

  useEffect(() => {
    stompClient.current = over(new SockJS('http://localhost:9000/ws'));
    stompClient.current.connect({},()=>{
      console.log("connect success");
    }, ()=>{
      console.log("connect fail");
    });
    window.onbeforeunload = function(event)
    {
        localStorage.removeItem('channels');
       
    };
  }, []);



  useEffect(() => {
    const getFriends = async () => {
      try {
        const res = await axios.get(`${BASE_URL}/api/user/friends/${user.id}`);
        setFriends(res.data);
      } catch (err) {
        console.log(err);
      }
    };
    getFriends();
  }, [user.id]);

  useEffect(() => {
    if(currentChat){
      const getMessages = async () => {
        try {
          const res = await axios(`${BASE_URL}/api/message/conversation/${currentChat.conversationId}`);
          setMessages(res.data);
        } catch (err) {
          console.log(err);
        }
      };
      getMessages();
    }
    console.log('useEffect');
  }, [currentChat?.conversationId]);

  const handleSubmit = async (e) => {
    // e.preventDefault();
    const message = {
      senderId: user.id,
      text: newMessage,
      conversationId: currentChat.conversationId,
    };


    stompClient.current.send("/app/message/private", {}, JSON.stringify(message));

    try {
      const res = await axios.post(`${BASE_URL}/api/message`,JSON.stringify(message),{
        headers: {
          'Content-Type': 'application/json'
          }
      });
      const newMessages = [...messages, res.data];
      setMessages(()=>newMessages)
      setNewMessage("");
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    scrollRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const handleSubscribeChat = (f)=> ()=> 
  {
    (async ()=> {
      try {
        const res = await axios.get(`${BASE_URL}/api/conversation/everTalked`,{
          params:{
            userId_1 : user.id,
            userId_2 : f.id
          }
        });
        if(res.data){
          const channel = `/chat/room/${res.data}/private`;
          const channels = JSON.parse(localStorage.getItem('channels')) || [];
        
          if(channels.findIndex(c => c === channel ) === -1) {
            stompClient.current.subscribe(`/chat/room/${res.data}/private`,(payload)=>{      
              setMessages(prevMessages => [...prevMessages, JSON.parse(payload.body)])
            })
            channels.push(channel);
            localStorage.setItem('channels',JSON.stringify(channels));    
          }
          setCurrentChat(()=>({
            conversationId:res.data,
            friend:f
          }));
        }
      } catch (err) {
        console.log(err);
      }
    })();
  }

  return (
    <>
      <Topbar />
      <div className="messenger">
        <div className="chatMenu">
          <div className="chatMenuWrapper">
            {friends.map((f) => (
                  <div onClick={handleSubscribeChat(f)}>
                    <Friend user={f}/>
                  </div>
                ))}
          </div>
        </div>
        <div className="chatBox">
          <div className="chatBoxWrapper">
            {currentChat ? (
              <>
                <div className="chatBoxTop">
                  {!Array.isArray(messages) || !messages.length ? (
                    <span className="noConversationText">
                      Open a conversation to start a chat.
                  </span>
                  ) : messages.map((m) => (
                    <div ref={scrollRef}>
                      <Message message={m} own={m.senderId === user.id} friendImg={currentChat?.friend?.profilePicture} />
                    </div>
                  ))}
                </div>
                <div className="chatBoxBottom">
                  <textarea
                    className="chatMessageInput"
                    placeholder="write something..."
                    onChange={(e) => setNewMessage(e.target.value)}
                    value={newMessage}
                  ></textarea>
                  <button className="chatSubmitButton" onClick={handleSubmit}>
                    Send
                  </button>
                </div>
              </>
            ) : (
              <span className="noConversationText">
                Open a conversation to start a chat.
              </span>
            )}
          </div>
        </div>

      </div>
    </>
  );
}
