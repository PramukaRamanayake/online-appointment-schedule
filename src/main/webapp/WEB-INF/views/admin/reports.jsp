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
        <a class="nav-link" href="<%= request.getContextPath() %>/admin/clients">Clients</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%= request.getContextPath() %>/admin/consultants">Consultants</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<%= request.getContextPath() %>/admin/reports"><span style="color: white;">Reports </span></a>
      </li>
    </ul>
    <span class="navbar-text">
      Welcome <%= session.getAttribute("username") %> !
      <a href="<%= request.getContextPath() %>/login"><i class='bx bx-log-out' id="log_out" ></i></a>
    </span>
  </div>
</nav> 
  
  <section class="home-section">
      
      <div class="column-3">
			<div class="right-column">
			  <div class="container">
			    <div class="table-container">
			    <form action="<%= request.getContextPath() %>/admin/generate-report" method="POST">
    					<input type="hidden" name="action" value="generate-report">
					    <label for="monthSelect">Select Month:</label>
					    <select id="monthSelect" name="selectedMonth">
						    <option value="January">January</option>
						    <option value="February">February</option>
						    <option value="March">March</option>
						    <option value="April">April</option>
						    <option value="May">May</option>
						    <option value="June">June</option>
						    <option value="July">July</option>
						    <option value="August">August</option>
						    <option value="September">September</option>
						    <option value="October">October</option>
						    <option value="November">November</option>
						    <option value="December">December</option>
						</select>

					    <button  value="Generate Report" class=add-button>Generate Report</button>
				</form>

			    
			    </div>
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
</body>
</html>