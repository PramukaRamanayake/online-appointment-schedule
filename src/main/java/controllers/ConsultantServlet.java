package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import dao.UserDaoImpl;
import models.Appointments;
import models.Consultant;
import models.User;
import services.RegistrationServiceImpl;
import services.RegistrationServices;


@WebServlet("/consultant/*")
public class ConsultantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ConsultantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String pathInfo = request.getPathInfo();
		 UserDao userDao = new UserDaoImpl();
	     RegistrationServices service = new RegistrationServiceImpl(userDao);
	     int totalAppointments = service.getTotalAppointments();
	        int totalClients = service.getTotalClients();
	        int totalConsultants = service.getTotalConsultants();
	        
	        request.setAttribute("totalAppointments", totalAppointments);
	        request.setAttribute("totalClients", totalClients);
	        request.setAttribute("totalConsultants", totalConsultants);
		 
	        if (pathInfo == null) {
	            request.getRequestDispatcher("/WEB-INF/views/consultant/consultantHome.jsp").forward(request, response);
	        } else if ("/clients".equals(pathInfo)) {
	        	List<User> jobSeekers = service.getAllJobSeekers();
            	System.out.println("jobseekers : "+jobSeekers);
            	request.setAttribute("jobSeekers", jobSeekers);
	            request.getRequestDispatcher("/WEB-INF/views/consultant/clients.jsp").forward(request, response);
	        } else if ("/appointments".equals(pathInfo)) {
                List<Appointments> appointments = service.getAllAppointments();
                request.setAttribute("appointments", appointments);
                request.getRequestDispatcher("/WEB-INF/views/consultant/appointments.jsp").forward(request, response);
	        }else {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String pathInfo = request.getPathInfo();
	    UserDao userDao = new UserDaoImpl();
	    RegistrationServices service = new RegistrationServiceImpl(userDao);

	    if ("/register".equals(pathInfo)) {
	        String username = request.getParameter("username");
	        String email = request.getParameter("email");
	        String country = request.getParameter("country");
	        String password = request.getParameter("password");
	        String password2 = request.getParameter("password2");
	        String mobile = request.getParameter("mobile");
	        boolean userExists = service.isUserExistsByEmail(email);
	        String dobString = request.getParameter("dob"); 

	        System.out.println("COnsultant email : "+email);
	        if (userExists) {
	            request.setAttribute("errorMessage", "User with this email already exists.");
	            request.getRequestDispatcher("/WEB-INF/views/admin/addnew.jsp").forward(request, response);
	            return;
	        }
	        else {
	        	// If the user does not exist, proceed with password validation and user creation
		        if (!password.equals(password2)) {
		            request.setAttribute("errorMessage", "Passwords do not match.");
		            request.getRequestDispatcher("/WEB-INF/views/admin/addnew.jsp").forward(request, response);
		            return;
		        }
		        if(password.length() <8) {
		        	request.setAttribute("errorMessage", "Invalid password length.");
		           
		            request.getRequestDispatcher("/WEB-INF/views/admin/addnew.jsp").forward(request, response);
		            return;
		        }
		        
		        if (dobString != null && !dobString.isEmpty()) {
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		            try {
		                Date dob = sdf.parse(dobString);
		                Calendar dobCalendar = Calendar.getInstance();
		                dobCalendar.setTime(dob);

		                // Calculate the current date
		                Calendar currentDate = Calendar.getInstance();

		                // Subtract 20 years from the current date
		                currentDate.add(Calendar.YEAR, -20);

		                // Compare the DOB with currentDate
		                if (dobCalendar.before(currentDate)) {
		                    // The DOB is greater than 20 years ago
		                    
		                } else {
		                    // The DOB is not greater than 20 years ago
		                	System.out.println("too young");
		                	request.setAttribute("errorMessage", "You are not older than 20");
		                	request.getRequestDispatcher("/WEB-INF/views/admin/addnew.jsp").forward(request, response);
				            return;
		                }
		            } catch (ParseException e) {
		                // Handle parsing exception
		                e.printStackTrace();
		            }
		        } else {
		            
		        }
	        	Consultant consultant = new Consultant(username, password, email, mobile, country);
		        String success = service.registerConsultant(consultant);
		        response.sendRedirect(request.getContextPath() + "/admin/consultants");
	        }
	        
	   
	        
	      
	    } else {
	        response.sendError(HttpServletResponse.SC_NOT_FOUND);
	    }
	}


}
