
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    boolean visitedWelcomePage = (boolean) session.getAttribute("visitedWelcomePage");
    if (visitedWelcomePage==false) {
        response.sendRedirect(request.getContextPath() + "/welcome");
        
    }

    session.setAttribute("visitedWelcomePage", false);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/style-01.css">
<style>
@import url("https://fonts.googleapis.com/css?family=Montserrat&display=swap");
body {
  width: 99%;
  height: 80vh;
  display: flex;
  justify-content: center;
  align-items: center; 
  flex-direction: column;
  background: rgb(89,116,255);
  background: radial-gradient(circle, rgba(89,116,255,1) 0%, rgba(254,115,143,1) 100%);
  }
  #login-box {
  position: relative;
  margin: 5% auto;
  width: 700px;
  height: 460px;
  background: white;
  border-radius: 2px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
}
  .left {
  position: absolute;
  top: 0;
  left: 0;
  box-sizing: border-box;
  padding: 40px;
  width: 300px;
  height: 400px;
}
input[type="text"],
input[type="password"] {
  display: block;
  box-sizing: border-box;
  margin-bottom: 20px;
  padding: 4px;
  width: 220px;
  height: 32px;
  border: none;
  border-bottom: 1px solid #AAA;
  font-family: 'Roboto', sans-serif;
  font-weight: 400;
  font-size: 15px;
  transition: 0.2s ease;
  background: transparent;
}

input[type="text"]:focus,
input[type="password"]:focus {
  border-bottom: none;
  color:#58006c;
  transition: 0.2s ease;
}

input[type="submit"] {
  margin-top: 28px;
  width: 120px;
  height: 32px;
  background: #58006c;
  border: none;
  border-radius: 3px;
  color: #FFF;
  font-family: 'Roboto', sans-serif;
  font-weight: 500;
  text-transform: uppercase;
  transition: 0.1s ease;
  cursor: pointer;
}

input[type="submit"]:hover,
input[type="submit"]:focus {
  opacity: 0.8;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
  transition: 0.1s ease;
}

input[type="submit"]:active {
  opacity: 1;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.4);
  transition: 0.1s ease;
}

</style>
</head>
<body>
<div id="login-box">
<div class="left">
    <h1 style="color:#58006c;">
        <% 
            String userType = request.getParameter("userType"); 
            session.setAttribute("userType", userType);  
            out.println(userType); // This will display the userType value on the page
        %>
    </h1>

    <form action="<%= request.getContextPath() %>/login" method="POST">
        <br><br><br>
        <input type="text" name="email" placeholder="E-mail" required />
        <input type="password" name="password" placeholder="Password" required />
        <input type="submit" name="signup_submit" value="Log In" /><br><br>

        <%-- Check userType and display the link only if it's "job hunter" --%>
        <% if ("Job Hunter".equals(userType)) { %>
            <span class="subtext">Join With Us <a href="<%= request.getContextPath() %>/register">Register Here</a></span>
        <% } %>
    </form>
</div>

<div class="right">
    <div>
        <figure>
            <img src="<%=request.getContextPath()%>/images/login-img.jpg" alt="sing up image">
        </figure>
    </div>
</div>
</div>


 
 
</body>
</html>