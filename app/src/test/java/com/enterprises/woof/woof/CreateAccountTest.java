package com.enterprises.woof.woof;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CreateAccountTest {

    @Test
    public void getTypedString() {
        //Creating a new client whose name is tester.
        Client c = new Client();
        c.setName("tester");

        assertEquals("tester", c.getName());
    }

    @Test
    public void getTypedPassword() {
        //Password should match what is typed
        Client c = new Client();
        c.setPassword("testPassword");

        assertEquals("testPassword", c.getPassword());
    }
}
