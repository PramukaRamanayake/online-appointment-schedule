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

public class LogoutServletTest {
    private LogoutServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() throws Exception {
        servlet = new LogoutServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("/your-context-path");
    }

    @Test
    public void testDoGet_Logout() throws Exception {
        servlet.doGet(request, response);

        verify(session).invalidate();
        verify(response).sendRedirect("/your-context-path/welcome.jsp");
    }
}
