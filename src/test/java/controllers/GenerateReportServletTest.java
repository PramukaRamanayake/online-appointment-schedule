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
import models.Appointments;
import services.RegistrationServices;
import util.PDFReportGenerator;

public class GenerateReportServletTest {
    private GenerateReportServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RegistrationServices service;

    @Before
    public void setUp() throws Exception {
        servlet = new GenerateReportServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        service = mock(RegistrationServices.class);
    }

    @Test
    public void testDoPost_GenerateReport() throws Exception {
        when(request.getParameter("action")).thenReturn("generate-report");
        when(request.getParameter("selectedMonth")).thenReturn("2023-09"); // Replace with your desired month

        List<Appointments> appointments = new ArrayList<>();
        // Populate the appointments list with sample data
        // You can use Mockito to mock the service call to get the data
        when(service.getAppointmentDataForMonth("2023-09")).thenReturn(appointments);

        PDFReportGenerator reportGenerator = mock(PDFReportGenerator.class);
        doNothing().when(reportGenerator).generatePDFReport(appointments, "2023-09");

        servlet.doPost(request, response);

        verify(reportGenerator).generatePDFReport(appointments, "2023-09");
        verify(request).setAttribute("reportMessage", "Report generated successfully!");
        // Verify any other expected behavior based on your servlet logic
    }

    @Test
    public void testDoPost_InvalidAction() throws Exception {
        when(request.getParameter("action")).thenReturn("invalid-action");

        servlet.doPost(request, response);

        // Verify that the response sends a not found error
        verify(response).sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
