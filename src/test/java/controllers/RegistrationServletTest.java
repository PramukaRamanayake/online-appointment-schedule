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
import models.User;
import services.RegistrationServices;

public class RegistrationServletTest {
    private RegistrationServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private UserDao userDao;
    private RegistrationServices service;

    @Before
    public void setUp() throws Exception {
        servlet = new RegistrationServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userDao = mock(UserDao.class);
        service = mock(RegistrationServices.class);

        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/your-context-path");
    }

    @Test
    public void testDoGet() throws Exception {
        servlet.doGet(request, response);

        verify(request).getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

    @Test
    public void testDoPost_UserRegistration() throws Exception {
        when(request.getParameter("username")).thenReturn("user1");
        when(request.getParameter("email")).thenReturn("user1@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("password2")).thenReturn("password");
        when(request.getParameter("mobile")).thenReturn("1234567890");
        when(session.getAttribute("userType")).thenReturn("Job Hunter");

        when(service.isUserExistsByEmail("user1@example.com")).thenReturn(false);

        User user = new User("user1", "user1@example.com", "password", "1234567890");
        when(service.registerUser(user)).thenReturn("Success");

        servlet.doPost(request, response);

        verify(response).sendRedirect("/your-context-path/login");
    }

    @Test
    public void testDoPost_UserAlreadyExists() throws Exception {
        when(request.getParameter("email")).thenReturn("existing@example.com");
        when(session.getAttribute("userType")).thenReturn("Job Hunter");

        when(service.isUserExistsByEmail("existing@example.com")).thenReturn(true);

        servlet.doPost(request, response);

        verify(request).setAttribute("errorMessage", "User with this email already exists.");
        verify(session).setAttribute("visistedWelcomePage", true);
        verify(request).getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    // Add more test cases for password mismatch and invalid password length

    // Write similar test cases for other user types and edge cases as needed
}
