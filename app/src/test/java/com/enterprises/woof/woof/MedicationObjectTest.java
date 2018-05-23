package com.enterprises.woof.woof;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/*
INCOMPLETE as the class is not yet complete
 */

public class MedicationObjectTest {

    @Test
    public void ObjectNotNull(){
        MedicationObject o = new MedicationObject("ibuprofen", "10", "20");
        Assert.assertNotNull(o);
    }

    @Test
    public void titleNotNull() {
        try {
            MedicationObject o = new MedicationObject(null, "1", "2");
            fail();
        }catch(NullPointerException e){}

    }

    @Test
    public void dateNotNull() {
        try {
            MedicationObject o = new MedicationObject("meeting", null, "2");
            fail();
        }catch(NullPointerException e){}

    }

    @Test
    public void timeNotNull() {
        try {
            MedicationObject o = new MedicationObject("unlucky", "1", null);
            fail();
        }catch(NullPointerException e){}

    }
}