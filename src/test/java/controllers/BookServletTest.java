package controllers;

import static org.junit.Assert.*;

import org.junit.Test;

import models.Appointments;
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

public class BookServletTest {
    private BookServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private RegistrationServices userService;

    @Before
    public void setUp() throws Exception {
        servlet = new BookServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        userService = mock(RegistrationServices.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("username")).thenReturn("testUser"); // Set the username for the session
    }

    @Test
    public void testDoGet() throws Exception {
        List<Appointments> appointments = new ArrayList<>();
        when(userService.getAllAppointments()).thenReturn(appointments);

        servlet.doGet(request, response);

        verify(request).setAttribute("appointments", appointments);
        verify(request.getRequestDispatcher("/WEB-INF/views/jobseeker/appointments.jsp")).forward(request, response);
    }

    @Test
    public void testDoPost() throws Exception {
        when(request.getParameter("appointmentDate")).thenReturn("2023-12-31");
        when(request.getParameter("appointmentTime")).thenReturn("10:00 AM");
        when(request.getParameter("consultantUsername")).thenReturn("consultantUser");
        when(request.getParameter("consultantEmail")).thenReturn("consultant@example.com");
        when(request.getParameter("consultantMobile")).thenReturn("1234567890");

        List<Appointments> appointments = new ArrayList<>();
        when(userService.getAllAppointments()).thenReturn(appointments);
        when(userService.getUserMobileByUsername("testUser")).thenReturn("9876543210");
        when(userService.getUserEmaileByUsername("testUser")).thenReturn("user@example.com");
        when(userService.makeAppointment(any(Appointments.class))).thenReturn(null);

        servlet.doPost(request, response);

        verify(userService).makeAppointment(any(Appointments.class));
        verify(request).setAttribute("appointments", appointments);
        verify(request.getRequestDispatcher("/WEB-INF/views/jobseeker/appointments.jsp")).forward(request, response);
    }
}
