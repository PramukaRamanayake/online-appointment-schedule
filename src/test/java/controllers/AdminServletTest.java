package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import models.User;
import services.RegistrationServices;

import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

public class AdminServletTest {
    private AdminServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private StringWriter stringWriter;
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
        servlet = new AdminServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        stringWriter = new StringWriter();
        requestDispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
    }

    @Test
    public void testDoGet_AdminHome() throws Exception {
        when(request.getPathInfo()).thenReturn(null);
        when(session.getAttribute("loggedIn")).thenReturn(true);

        servlet.doGet(request, response);

        // Verify that the request dispatcher forwards to the expected view
        verify(request.getRequestDispatcher("/WEB-INF/views/admin/adminHome.jsp")).forward(request, response);
    }

    @Test
    public void testDoGet_Clients() throws Exception {
        when(request.getPathInfo()).thenReturn("/clients");
        when(session.getAttribute("loggedIn")).thenReturn(true);

        // Mock data to return when service methods are called
        RegistrationServices service = mock(RegistrationServices.class);
        List<User> jobSeekers = new ArrayList<>();
        when(service.getAllJobSeekers()).thenReturn(jobSeekers);


        // Verify that the request dispatcher forwards to the expected view
        verify(request.getRequestDispatcher("/WEB-INF/views/admin/clients.jsp")).forward(request, response);

        // Verify that the jobSeekers attribute is set in the request
        verify(request).setAttribute("jobSeekers", jobSeekers);
    }

    // Similarly, write test cases for other scenarios in the doGet method

    @Test
    public void testDoPost_ConsultantDelete() throws Exception {
        when(request.getPathInfo()).thenReturn("/consultant/delete");

        // Mock data to return when service methods are called
        RegistrationServices service = mock(RegistrationServices.class);
        when(service.deleteByConsultantEmail(anyString())).thenReturn(null);

        servlet.doPost(request, response);

        // Verify that the response is redirected to the expected URL
        verify(response).sendRedirect(request.getContextPath() + "/admin/consultants");
    }

    @Test
    public void testDoPost_ClientDelete() throws Exception {
        when(request.getPathInfo()).thenReturn("/client/delete");

        // Mock data to return when service methods are called
        RegistrationServices service = mock(RegistrationServices.class);
        when(service.deleteByUserEmail(anyString())).thenReturn(null);

        servlet.doPost(request, response);

        // Verify that the response is redirected to the expected URL
        verify(response).sendRedirect(request.getContextPath() + "/admin/clients");
    }

    // Similarly, write test cases for other scenarios in the doPost method
}
