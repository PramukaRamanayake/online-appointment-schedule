package services;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import dao.UserDao;
import models.Admin;
import models.Appointments;
import models.Consultant;
import models.User;
import services.RegistrationServiceImpl;
import java.util.ArrayList;
import java.util.List;

public class RegistrationServiceImplTest {
    private RegistrationServices service;
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        userDao = mock(UserDao.class);
        service = new RegistrationServiceImpl(userDao);
    }

    @Test
    public void testRegisterUser() {
        User user = new User("username", "user@example.com", "password", "1234567890");
        when(userDao.registerUser(user)).thenReturn("Success");

        String result = service.registerUser(user);

        assert(result.equals("Success"));
    }

    @Test
    public void testRegisterConsultant() {
        Consultant consultant = new Consultant("username", "password", "consultant@example.com", "1234567890", "Country");
        when(userDao.registerConsultant(consultant)).thenReturn("Success");

        String result = service.registerConsultant(consultant);

        assert(result.equals("Success"));
    }

    @Test
    public void testRegisterAdmin() {
        Admin admin = new Admin("admin", "password", "admin@example.com", null);
        when(userDao.registerAdmin(admin)).thenReturn("Success");

        String result = service.registerAdmin(admin);

        assert(result.equals("Success"));
    }

    @Test
    public void testGetUserByEmailAndPassword() {
        String email = "user@example.com";
        String password = "password";
        User user = new User("username", email, password, "1234567890");
        when(userDao.getUserByEmailAndPassword(email, password)).thenReturn(user);

        User result = service.getUserByEmailAndPassword(email, password);

        assert(result != null);
        assert(result.getEmail().equals(email));
        assert(result.getPassword().equals(password));
    }

    @Test
    public void testGetConsultantByEmailAndPassword() {
        String email = "consultant@example.com";
        String password = "password";
        Consultant consultant = new Consultant("username", password, email, "1234567890", "Country");
        when(userDao.getConsultantByEmailAndPassword(email, password)).thenReturn(consultant);

        Consultant result = service.getConsultantByEmailAndPassword(email, password);

        assert(result != null);
        assert(result.getEmail().equals(email));
        assert(result.getPassword().equals(password));
    }

    @Test
    public void testGetAdminByEmailAndPassword() {
        String email = "admin@example.com";
        String password = "password";
        Admin admin = new Admin("admin", password, email, password);
        when(userDao.getAdminByEmailAndPassword(email, password)).thenReturn(admin);

        Admin result = service.getAdminByEmailAndPassword(email, password);

        assert(result != null);
        assert(result.getEmail().equals(email));
        assert(result.getPassword().equals(password));
    }

    @Test
    public void testGetAllJobSeekers() {
        List<User> jobSeekers = new ArrayList<>();
        when(userDao.getAllJobSeekers()).thenReturn(jobSeekers);

        List<User> result = service.getAllJobSeekers();

        assert(result != null);
        assert(result == jobSeekers);
    }

    @Test
    public void testGetAllConsultants() {
        List<Consultant> consultants = new ArrayList<>();
        when(userDao.getAllConsultants()).thenReturn(consultants);

        List<Consultant> result = service.getAllConsultants();

        assert(result != null);
        assert(result == consultants);
    }

    // Write similar test cases for other methods as needed
}
