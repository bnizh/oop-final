package Objects;

import junit.framework.TestCase;

import javax.ejb.BeforeCompletion;

/**
 * Created by Boris on 06.06.2016.
 */
public class SellerTest extends TestCase {



    public void testGetGallery() throws Exception {

    }

    public void testSetGallery() throws Exception {

    }

    public void testEquals() throws Exception {
        Seller s = new Seller("username","password","email","name",0,"112",0, "image");
        Seller s1 = new Seller("username","password","email","name",0,"112",0, "image");
        assertTrue(s.equals( s1));
        Seller s2 = new Seller("username","password","email","name",0,"113",0, "image");
        assertFalse(s.equals(s2));
    }

    public void testGetEmail() throws Exception {
        Seller s = new Seller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getEmail(), "email");
        s = new Seller("username","password","bla1","name",0,"112",0, "image");
        assertEquals(s.getEmail(), "bla1");
        assertFalse(s.getEmail().equals("email"));
    }

    public void testSetEmail() throws Exception {
        Seller s = new Seller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getEmail(), "email");
        s.setEmail("bla2");
        assertFalse(s.getEmail().equals("email"));
        assertEquals(s.getEmail(), "bla2");
    }

    public void testGetUserName() throws Exception {
        Seller s = new Seller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getUserName(), "username");
        s = new Seller("bla1","password","email","name",0,"112",0, "image");
        assertFalse(s.getUserName().equals("username"));
        assertEquals(s.getUserName(), "bla1");
    }

    public void testSetUserName() throws Exception {
        Seller s = new Seller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getUserName(), "username");
        s.setUserName("bla2");
        assertFalse(s.getUserName().equals("username"));
        assertEquals(s.getUserName(), "bla2");
    }

    public void testGetName() throws Exception {
        Seller s = new Seller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getName(), "name");
        assertFalse(s.getName().equals("bla"));
        s= new Seller("username","password","email","bla",0,"112",0, "image");
        assertEquals(s.getName(), "bla");
    }

    public void testSetName() throws Exception {
        Seller s = new Seller("username","password","email","name",0,"112",0, "image");
        s.setName("bla");
        assertEquals(s.getName(),"bla");
        assertFalse(s.getName().equals("name"));
        s.setName("name1");
        assertEquals(s.getName(), "name1");
        assertFalse(s.getName().equals("name"));
        assertFalse(s.getName().equals("bla"));
    }

    public void testGetRating() throws Exception {

    }

    public void testSetRating() throws Exception {

    }

    public void testGetImage() throws Exception {

    }

    public void testSetImage() throws Exception {

    }

    public void testGetPassword() throws Exception {

    }

    public void testSetPassword() throws Exception {

    }

    public void testGetMobileNumber() throws Exception {

    }

    public void testSetMobileNumber() throws Exception {

    }

    public void testGetVoters() throws Exception {

    }

    public void testSetVoters() throws Exception {

    }

    public void testInceaseRating() throws Exception {

    }

    public void testGetID() throws Exception {

    }

    public void testSetID() throws Exception {

    }

}