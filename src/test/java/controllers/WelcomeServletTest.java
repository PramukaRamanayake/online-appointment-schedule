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

public class WelcomeServletTest {
    private WelcomeServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        servlet = new WelcomeServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoGet() throws Exception {
        when(session.getAttribute("userType")).thenReturn("Job Hunter");

        servlet.doGet(request, response);

        verify(request).getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(request, response);
    }

    // Add more test cases for different user types and scenarios as needed
}
