package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UserDao;
import models.Consultant;
import services.RegistrationServices;

public class JobServletTest {
    private JobServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RegistrationServices service;

    @Before
    public void setUp() throws Exception {
        servlet = new JobServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        service = mock(RegistrationServices.class);
    }

    @Test
    public void testDoGet_JobHome() throws Exception {
        when(request.getPathInfo()).thenReturn(null);
        when(service.getTotalAppointments()).thenReturn(10);
        when(service.getTotalClients()).thenReturn(20);
        when(service.getTotalConsultants()).thenReturn(5);

        servlet.doGet(request, response);

        verify(request).setAttribute("totalAppointments", 10);
        verify(request).setAttribute("totalClients", 20);
        verify(request).setAttribute("totalConsultants", 5);
        verify(request).getRequestDispatcher("/WEB-INF/views/jobseeker/jobhome.jsp").forward(request, response);
    }

    @Test
    public void testDoGet_Consultants() throws Exception {
        when(request.getPathInfo()).thenReturn("/consultants");
        List<Consultant> consultants = new ArrayList<>();
        when(service.getAllConsultants()).thenReturn(consultants);

        servlet.doGet(request, response);

        verify(request).setAttribute("consultants", consultants);
        verify(request).getRequestDispatcher("/WEB-INF/views/jobseeker/consultants.jsp").forward(request, response);
    }

    @Test
    public void testDoGet_Appointments() throws Exception {
        when(request.getPathInfo()).thenReturn("/appointments");

        servlet.doGet(request, response);

        verify(request).getRequestDispatcher("/WEB-INF/views/jobseeker/appointments.jsp").forward(request, response);
    }

    @Test
    public void testDoGet_InvalidPathInfo() throws Exception {
        when(request.getPathInfo()).thenReturn("/invalid-path");

        servlet.doGet(request, response);

        // Verify that the response sends a not found error
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
