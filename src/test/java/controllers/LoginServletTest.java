package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.UserDao;
import models.Admin;
import models.Consultant;
import models.User;
import services.RegistrationServices;

public class LoginServletTest {
    private LoginServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserDao userDao;
    private RegistrationServices service;

    @Before
    public void setUp() throws Exception {
        servlet = new LoginServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userDao = mock(UserDao.class);
        service = mock(RegistrationServices.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoGet() throws Exception {
        servlet.doGet(request, response);

        verify(request).getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Test
    public void testDoPost_JobHunterLogin() throws Exception {
        when(request.getParameter("email")).thenReturn("jobhunter@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(session.getAttribute("userType")).thenReturn("Job Hunter");

        User user = new User("username", "jobhunter@example.com", "password", null);
        when(service.getUserByEmailAndPassword("jobhunter@example.com", "password")).thenReturn(user);

        servlet.doPost(request, response);

        verify(session).setAttribute("username", user.getUsername());
        verify(session).setAttribute("loggedIn", true);
        verify(response).sendRedirect(request.getContextPath() + "/jobseeker");
    }

    @Test
    public void testDoPost_ConsultantLogin() throws Exception {
        when(request.getParameter("email")).thenReturn("consultant@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(session.getAttribute("userType")).thenReturn("Consultant");

        Consultant consultant = new Consultant("username", "password", "consultant@example.com", "mobile", "country");
        when(service.getConsultantByEmailAndPassword("consultant@example.com", "password")).thenReturn(consultant);

        servlet.doPost(request, response);

        verify(session).setAttribute("username", consultant.getUsername());
        verify(session).setAttribute("loggedIn", true);
        verify(response).sendRedirect(request.getContextPath() + "/consultant");
    }

    @Test
    public void testDoPost_AdminLogin() throws Exception {
        when(request.getParameter("email")).thenReturn("admin@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(session.getAttribute("userType")).thenReturn("Administrator");

        Admin admin = new Admin("username", "password", "admin@example.com", null);
        when(service.getAdminByEmailAndPassword("admin@example.com", "password")).thenReturn(admin);

        servlet.doPost(request, response);

        verify(session).setAttribute("username", admin.getUsername());
        verify(session).setAttribute("loggedIn", true);
        verify(response).sendRedirect(request.getContextPath() + "/admin");
    }

    @Test
    public void testDoPost_InvalidLogin() throws Exception {
        when(request.getParameter("email")).thenReturn("invalid@example.com");
        when(request.getParameter("password")).thenReturn("invalidpassword");
        when(session.getAttribute("userType")).thenReturn("Job Hunter");

        when(service.getUserByEmailAndPassword("invalid@example.com", "invalidpassword")).thenReturn(null);

        servlet.doPost(request, response);

        verify(request).setAttribute("error", "Invalid username or password");
        verify(request).getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

   
}
