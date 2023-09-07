<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <title>The Job</title>
    <!-- Boxicons CDN Link -->
    
    <link href="../css/style-02.css?version=1" rel="stylesheet" >
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
        <a class="nav-link" href="<%= request.getContextPath() %>/admin"><span style="color: grey;">Dashboard</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%= request.getContextPath() %>/admin/clients"><span style="color: white;">Clients</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%= request.getContextPath() %>/admin/consultants">Consultants <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%= request.getContextPath() %>/admin/reports">Reports</a>
      </li>
    </ul>
    <span class="navbar-text">
      Welcome <%= session.getAttribute("username") %> !
      <a href="<%= request.getContextPath() %>/login"><i class='bx bx-log-out' id="log_out" ></i></a>
    </span>
  </div>
</nav>
  <section class="home-section">
	<!-- Details priting -->
  <div class="column-3">
			<div class="right-column">
			  <div class="container">
			  
			  
			    <div class="table-container">
  <!-- <h2 class="header">Job Seekers' Details</h2>  -->
				  <br>
				  <ul class="responsive-table">
				  <table class="table table-striped">
  <thead>
    <tr>
      <th scope="col">Client Id</th>
      <th scope="col">Email</th>
      <th scope="col">Mobile</th>
      <th scope="col">Delete</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${jobSeekers}" var="jobSeeker">
    <tr>
        <td>user01</td>
        <td>user01@gmail.com</td>
        <td>1234567890</td>
        <td>
            <form method="post" action="<%= request.getContextPath()%>/admin/client/delete">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="jobseekeremail" value="${jobSeeker.email}">
                <button type="submit" class="delete-button">Delete</button>
            </form>
     		   </td>
   				 </tr>
   				 <tr>
        <td>user02</td>
        <td>user02@gmail.com</td>
        <td>4567812390</td>
        <td>
            <form method="post" action="<%= request.getContextPath()%>/admin/client/delete">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="jobseekeremail" value="${jobSeeker.email}">
                <button type="submit" class="delete-button">Delete</button>
            </form>
     		   </td>
   				 </tr>
   				  <tr>
        <td>user03</td>
        <td>user03@gmail.com</td>
        <td>7891212390</td>
        <td>
            <form method="post" action="<%= request.getContextPath()%>/admin/client/delete">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="jobseekeremail" value="${jobSeeker.email}">
                <button type="submit" class="delete-button">Delete</button>
            </form>
     		   </td>
   				 </tr>
				</c:forEach>
  				</tbody>
			</table>
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