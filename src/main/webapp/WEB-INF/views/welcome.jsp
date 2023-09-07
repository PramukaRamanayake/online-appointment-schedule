 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
 <link rel="stylesheet" href="css/style-01.css?version=1">
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
  .button {
    width: 300px;
    height: 50px;
    background: #539BFC;
    position: relative;
    background: #B1B1B1;
    margin-bottom: 25px;
    border-radius: 32px;
    text-align: center;
    cursor: pointer;
    transition: all 0.1s ease-in-out;
     box-shadow: -6px -6px 10px rgba(255, 255, 255, 0.8),
        6px 6px 10px rgba(0, 0, 0, 0.2);
      color: #000000;
      line-height: 50px;
      font-family: "Montserrat", sans-serif;
      font-size: 18px;
      font-weight: bold;
    	padding-top: 10px;
    .span {
      line-height: 100px;
      font-family: "Montserrat", sans-serif;
      font-size: 12px;
      font-weight: bold;
    }&:hover{
        opacity: 0.4;
        box-shadow: -6px -6px 10px rgba(255, 255, 255, 0.8)}
    } }
}

</style>
</head>
<body>

<div id="login-box">
  <div class="left">
    <h1 style="color:black;">Choose A Role...</h1><br>
    <div class="button" onclick="redirectToLogin('Job Hunter')"><span class="text">Job Seeker</span></div>
    <div class="button" onclick="redirectToLogin('Consultant')"><span>Consultant</span></div>
    <div class="button" onclick="redirectToLogin('Administrator')"><span>Administrator</span></div>
  </div>
  
  <div class="right">
    <div >
		
	</div>
	
	<div class="right">
    <div>
        
            <img src="<%=request.getContextPath()%>/images/welcome-img.png" alt="home image">
        
    </div>
</div>
  </div>
  <script>
  function redirectToLogin(userType) {
	  <% 
	  session.setAttribute("visitedWelcomePage", true); %>
	   var contextPath = '<%= request.getContextPath() %>';
	   
	    window.location.href = contextPath + '/login?userType=' + encodeURIComponent(userType);
  }
</script>
</div>
</body>
</html>