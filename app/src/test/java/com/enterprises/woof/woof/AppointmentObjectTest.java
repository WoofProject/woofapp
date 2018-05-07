package com.enterprises.woof.woof;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/*
INCOMPLETE as the class is not yet complete
 */

public class AppointmentObjectTest {

    @Test
    public void ObjectNotNull(){
        AppointmentObject o = new AppointmentObject("yes", "2.3.2012", "10pm");
        Assert.assertNotNull(o);
    }

    @Test
    public void titleNotNull() {
        try {
            AppointmentObject o = new AppointmentObject(null, "1", "2");
            fail();
        }catch(NullPointerException e){}

    }

    @Test
    public void dateNotNull() {
        try {
            AppointmentObject o = new AppointmentObject("meeting", null, "2");
            fail();
        }catch(NullPointerException e){}

    }

    @Test
    public void timeNotNull() {
        try {
            AppointmentObject o = new AppointmentObject("parle-g", "1", null);
            fail();
        }catch(NullPointerException e){}

    }
}