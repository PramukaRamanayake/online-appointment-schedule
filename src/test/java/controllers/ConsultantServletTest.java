package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDao;
import models.Appointments;
import models.Consultant;
import models.User;
import services.RegistrationServices;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDao;
import models.Appointments;
import models.Consultant;
import models.User;
import services.RegistrationServices;

public class ConsultantServletTest {
    private ConsultantServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RegistrationServices service;

    @Before
    public void setUp() throws Exception {
        servlet = new ConsultantServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        service = mock(RegistrationServices.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoGet_ConsultantHome() throws Exception {
        when(request.getPathInfo()).thenReturn(null);
        when(service.getTotalAppointments()).thenReturn(10);
        when(service.getTotalClients()).thenReturn(20);
        when(service.getTotalConsultants()).thenReturn(5);

        servlet.doGet(request, response);

        verify(request).setAttribute("totalAppointments", 10);
        verify(request).setAttribute("totalClients", 20);
        verify(request).setAttribute("totalConsultants", 5);
        verify(request).getRequestDispatcher("/WEB-INF/views/consultant/consultantHome.jsp").forward(request, response);
    }

    @Test
    public void testDoGet_Clients() throws Exception {
        when(request.getPathInfo()).thenReturn("/clients");
        List<User> jobSeekers = new ArrayList<>();
        when(service.getAllJobSeekers()).thenReturn(jobSeekers);

        servlet.doGet(request, response);

        verify(request).setAttribute("jobSeekers", jobSeekers);
        verify(request).getRequestDispatcher("/WEB-INF/views/consultant/clients.jsp").forward(request, response);
    }

    @Test
    public void testDoGet_Appointments() throws Exception {
        when(request.getPathInfo()).thenReturn("/appointments");
        List<Appointments> appointments = new ArrayList<>();
        when(service.getAllAppointments()).thenReturn(appointments);

        servlet.doGet(request, response);

        verify(request).setAttribute("appointments", appointments);
        verify(request).getRequestDispatcher("/WEB-INF/views/consultant/appointments.jsp").forward(request, response);
    }

    // Write similar test cases for other scenarios in the doGet method

    @Test
    public void testDoPost_Register() throws Exception {
        when(request.getPathInfo()).thenReturn("/register");
        when(request.getParameter("username")).thenReturn("testUser");
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("country")).thenReturn("USA");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password2")).thenReturn("password");
        when(request.getParameter("mobile")).thenReturn("1234567890");
        when(service.isUserExistsByEmail("test@example.com")).thenReturn(false);
        when(service.getUserMobileByUsername("testUser")).thenReturn("9876543210");
        when(service.getUserEmaileByUsername("testUser")).thenReturn("user@example.com");
        when(service.registerConsultant(any(Consultant.class))).thenReturn("success");

        servlet.doPost(request, response);

        verify(service).registerConsultant(any(Consultant.class));
        verify(response).sendRedirect(request.getContextPath() + "/admin/consultants");
    }

    // Write test cases for other scenarios in the doPost method
}
