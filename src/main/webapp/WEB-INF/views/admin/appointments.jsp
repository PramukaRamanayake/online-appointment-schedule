<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <title>The Job</title>
    <link rel="stylesheet" href="../css/style-02.css?version=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Boxicons CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
<body>
  
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="<%= request.getContextPath() %>/admin">The Job</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="<%= request.getContextPath() %>/admin">Dashboard</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%= request.getContextPath() %>/admin/clients">Clients </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%= request.getContextPath() %>/admin/consultants">Consultants <span class="sr-only">(current)</span></a>
      </li>
    </ul>
    <span class="navbar-text">
      Welcome <%= session.getAttribute("username") %> !
      <a href="<%= request.getContextPath() %>/login"><i class='bx bx-log-out' id="log_out" ></i></a>
    </span>
  </div>
</nav>
  
  <section class="home-section">
         <!--  admin's appointments displayed here -->
       <div class="column-3">
			<div class="right-column">
			  <div class="container">
			    <div class="table-container">
  <!-- <h2 class="header">Job Seekers' Details</h2>  -->
				  <br>
				  <ul class="responsive-table">
				    <li class="table-header">
				      <div class="col col-1">Consultant</div>
				      <div class="col col-2">Mobile</div>
				      <div class="col col-3">Client</div>
				      <div class="col col-4">Appointment Date</div>
				      <div class="col col-5">Appointment Time</div>
				      <div class="col col-6">Action</div>
				    </li>
				    
				    <c:forEach items="${appointments}" var="appointment">
				       <li class="table-row">
        <div class="col col-1" data-label="Username">Consultan01</div>
        <div class="col col-2" data-label="Phone">1234567890</div>
        <div class="col col-3" data-label="uname">user01</div>
        <div class="col col-4" data-label="date">2023-09-15</div>
        <div class="col col-5" data-label="time">10:00 AM</div>
        <div class="col col-6" data-label="cancel"><button class="delete-button">Cancel</button></div>
    </li>
    <li class="table-row">
        <div class="col col-1" data-label="Username">Consultan02</div>
        <div class="col col-2" data-label="Phone">4567812390</div>
        <div class="col col-3" data-label="uname">user01</div>
        <div class="col col-4" data-label="date">2023-08-23</div>
        <div class="col col-5" data-label="time">12.00 PM</div>
        <div class="col col-6" data-label="cancel"><button class="delete-button">Cancel</button></div>
    </li>
    <li class="table-row">
        <div class="col col-1" data-label="Username">Consultan03</div>
        <div class="col col-2" data-label="Phone">1234567890</div>
        <div class="col col-3" data-label="uname">user03</div>
        <div class="col col-4" data-label="date">2023-08-31</div>
        <div class="col col-5" data-label="time">10:00 AM</div>
        <div class="col col-6" data-label="cancel"><button class="delete-button">Cancel</button></div>
    </li>
				     </c:forEach>
				  </ul>
				</div> 
			 </div>
		</div>  
	</div>
  </section>
  <script>
  let sidebar = document.querySelector(".sidebar");
  let closeBtn = document.querySelector("#btn");
  let searchBtn = document.querySelector(".bx-search");

  closeBtn.addEventListener("click", ()=>{
    sidebar.classList.toggle("open");
    menuBtnChange();//calling the function(optional)
  });

  searchBtn.addEventListener("click", ()=>{ // Sidebar open when you click on the search iocn
    sidebar.classList.toggle("open");
    menuBtnChange(); //calling the function(optional)
  });

  // following are the code to change sidebar button(optional)
  function menuBtnChange() {
   if(sidebar.classList.contains("open")){
     closeBtn.classList.replace("bx-menu", "bx-menu-alt-right");//replacing the iocns class
   }else {
     closeBtn.classList.replace("bx-menu-alt-right","bx-menu");//replacing the iocns class
   }
  }
  </script>
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>