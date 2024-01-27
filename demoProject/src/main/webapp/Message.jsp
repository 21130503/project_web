<%@ page import="nhom26.User" %><%--
  Created by IntelliJ IDEA.
  User: Hp
  Date: 1/23/2024
  Time: 1:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message</title>
    <link rel="stylesheet" href="./css/message.scss">
    <script src="https://cdn.socket.io/4.1.3/socket.io.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="./css/logo.css">


</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    int id = user.getId();
%>
<div class="messages__wrapper flex">
    <div class="left-chat">
        <header class="header">
            <div class="flex a-center">
                <a href="./index" class="text-decoration-none">
                    <h1 class="logo">Nhóm 26</h1>
                </a>
                <span>

                        </span>
            </div>
        </header>
        <div class="user-list">
            <div class="chat-item">
            </div>
        </div>
    </div>
    <div class="right-chat">
        <% if ("s".equals("sd")) { %>
        <div className="intro-wrapper flex a-center j-center ">
            <div className="inner-chat">
                <div className="icon flex j-center a-center">
                    <svg
                            aria-label=""
                            class="_ab6-"
                            color="rgb(0, 0, 0)"
                            fill="rgb(0, 0, 0)"
                            height="96"
                            role="img"
                            viewBox="0 0 96 96"
                            width="96"
                    >
                        <path d="M48 0C21.532 0 0 21.533 0 48s21.532 48 48 48 48-21.532 48-48S74.468 0 48 0Zm0 94C22.636 94 2 73.364 2 48S22.636 2 48 2s46 20.636 46 46-20.636 46-46 46Zm12.227-53.284-7.257 5.507c-.49.37-1.166.375-1.661.005l-5.373-4.031a3.453 3.453 0 0 0-4.989.921l-6.756 10.718c-.653 1.027.615 2.189 1.582 1.453l7.257-5.507a1.382 1.382 0 0 1 1.661-.005l5.373 4.031a3.453 3.453 0 0 0 4.989-.92l6.756-10.719c.653-1.027-.615-2.189-1.582-1.453ZM48 25c-12.958 0-23 9.492-23 22.31 0 6.706 2.749 12.5 7.224 16.503.375.338.602.806.62 1.31l.125 4.091a1.845 1.845 0 0 0 2.582 1.629l4.563-2.013a1.844 1.844 0 0 1 1.227-.093c2.096.579 4.331.884 6.659.884 12.958 0 23-9.491 23-22.31S60.958 25 48 25Zm0 42.621c-2.114 0-4.175-.273-6.133-.813a3.834 3.834 0 0 0-2.56.192l-4.346 1.917-.118-3.867a3.833 3.833 0 0 0-1.286-2.727C29.33 58.54 27 53.209 27 47.31 27 35.73 36.028 27 48 27s21 8.73 21 20.31-9.028 20.31-21 20.31Z"></path>
                    </svg>
                </div>
                <div className="content-inner flex j-center a-center flex-column">
                    <h4 className="title">Tin nhắn của bạn</h4>
                    <p className="description">Gửi ảnh và tin nhắn riêng tư cho bạn bè hoặc nhóm</p>
                    <button className="br-8 btn">Gửi tin nhắn</button>
                </div>
            </div>
        </div>
        <%} else {%>
        <div
                class="chat-container__wrapper flex
        flex-column"
        >
            <header class="header-chat flex align-items-center justify-content-between">
                <div class="information-user-chat flex a-center">
                    <div class="avatar">
                        <img style="height: 100%;" src="img/avatarAdmin.jpg" alt="">
                    </div>
                    <h3>Admin</h3>
                </div>
            </header>
            <div class="chat-container">
                <div class="detail-account flex flex-column a-center">
                    <div class="avatar">
                        <img src="img/avatarAdmin.jpg" alt="">
                    </div>
                    <div class="information">
                        <h3>Admin</h3>
                        <p>Xin chào. Bạn cần giúp gì ?</p>
                    </div>
                </div>
                <div class="messages flex flex-column">

                </div>
                <div class="chat-input__wrapper flex a-center">
                    <form class="input-container" id="form-mess">
                        <input id="input-mess"
                               style="width: 85%; padding: 10px 12px; border-radius: 20px ; outline: none;border: 1px solid"
                               type="text"
                               placeholder="Type your messenger here"
                               className="input"
                        />
                        <button id="submit-mess" style="padding: 10px 12px;" type="submit" class="submit-btn">
                            Gửi
                        </button>
                    </form>
                </div>
            </div>
        </div>
        <%}%>
    </div>
</div>
<script>

    const socket = io('http://localhost:5000')
    socket.emit('add-user', <%=id%>)

    // nhận dl
    const form = document.querySelector("#form-mess")
    const inputMess = document.querySelector("#input-mess")
    const btnMess = document.querySelector("#submit-mess")

    form.addEventListener("submit", (e) => {
        e.preventDefault();
        let value = inputMess.value;
        console.log(value)
        hanleSendMess(value)
        inputMess.value = ""

    })
    let messages = []
    async function getMessages(){
        const {data} = await axios.post('http://localhost:5000/api/message/getmsg', {
            from: <%=id%>,
            to: 1,
        });
        messages = data;
        handleMessage(data)
    }
    messages = getMessages()
    const hanleSendMess = async (msg) => {
        try {
            const { data } = await axios.post('http://localhost:5000/api/message/addmsg', {
                from: <%=id%>,
                to: 1,
                message: msg,
            });
            console.log(data);
        } catch (error) {
            console.error('Error:', error);
        }
        socket.emit('send-msg', {
            to: 1,
            from: <%=id%>,
            msg,
        });
        const msgs = [...messages];
        msgs.push({fromSelf: true, message: msg});
        messages = msgs;
        handleMessage(messages)

    }
    // nhận dl
    socket.on('msg-recieve', (msg) => {
        console.log('anc')
        messages.push({fromSelf: false, message: msg});
        handleMessage(messages)
    });
    // render
    const handleMessage=(messages)=>{
        console.log(messages);
        const message = document.querySelector(".messages")
        const map = messages.map((item)=>{
            return `
            <div class="message ${item.fromSelf ? "sended" : "recieved"}">
                <div class="content-mess">
                    <p>${item.message}</p>
                    </div>
                </div>
            `
        })
        message.innerHTML = map.join('')
    }

</script>
</body>
</html>