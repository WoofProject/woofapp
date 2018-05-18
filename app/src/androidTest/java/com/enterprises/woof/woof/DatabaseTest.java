package com.enterprises.woof.woof;
import static android.support.test.InstrumentationRegistry.getTargetContext;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DatabaseTest {

    private Context testContext;
    private DatabaseHelper database;

    @Before
    public void setUp() throws Exception{
        database = new DatabaseHelper(getTargetContext());
    }

    @After
    public void setDown() throws Exception{
        database.close();
    }

    @Test
    public void testPreSetupConditions() {
        assertNotNull(database);
    }

    @Test
    public void testAddToDatabase() {
        Client c = new Client();
        c.setName("testName");
        c.setEmail("testEmail");
        c.setPassword("testPassword");
        database.insertClient(c);
        Client c2 = new Client();
        c2.setName("testName2");
        c2.setEmail("testEmail2");
        c2.setPassword("testPassword2");
        database.insertClient(c2);

        List<String> emails = database.getEmails();
        //checking insertion of emails
        assertEquals(database.getEmails().size(), 0);
    }

    @Test
    public void testPasswordIsSame() {
        String email = "testEmail";
        String pass = "testPassword";

        Client c = new Client();
        c.setName("testName");
        c.setEmail(email);
        c.setPassword(pass);
        database.insertClient(c);

        String password = database.searchPassword(email);

        assertEquals(password, pass);
    }

    @Test
    public void testPasswordIsNotSame() {
        String email = "testEmail";
        String pass = "testPassword";

        Client c = new Client();
        c.setName("testName");
        c.setEmail(email);
        c.setPassword(pass);
        database.insertClient(c);;

        assertNotEquals("incorrectPassword", pass);
    }
}
