package Objects;

import junit.framework.TestCase;

import javax.ejb.BeforeCompletion;

/**
 * Created by Boris on 06.06.2016.
 */
public class SellerTest extends TestCase {


    public void testEquals() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        Seller s1 = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertTrue(s.equals( s1));
        Seller s2 = ObjectFactory.getNewSeller("username","password","email","name",0,"113",0, "image");
        assertFalse(s.equals(s2));
    }

    public void testGetEmail() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getEmail(), "email");
        s = ObjectFactory.getNewSeller("username","password","bla1","name",0,"112",0, "image");
        assertEquals(s.getEmail(), "bla1");
        assertFalse(s.getEmail().equals("email"));
    }

    public void testSetEmail() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getEmail(), "email");
        s.setEmail("bla2");
        assertFalse(s.getEmail().equals("email"));
        assertEquals(s.getEmail(), "bla2");
    }

    public void testGetUserName() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getUserName(), "username");
        s = ObjectFactory.getNewSeller("bla1","password","email","name",0,"112",0, "image");
        assertFalse(s.getUserName().equals("username"));
        assertEquals(s.getUserName(), "bla1");
    }

    public void testSetUserName() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getUserName(), "username");
        s.setUserName("bla2");
        assertFalse(s.getUserName().equals("username"));
        assertEquals(s.getUserName(), "bla2");
    }

    public void testGetName() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getName(), "name");
        assertFalse(s.getName().equals("bla"));
        s= ObjectFactory.getNewSeller("username","password","email","bla",0,"112",0, "image");
        assertEquals(s.getName(), "bla");
    }

    public void testSetName() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        s.setName("bla");
        assertEquals(s.getName(),"bla");
        assertFalse(s.getName().equals("name"));
        s.setName("name1");
        assertEquals(s.getName(), "name1");
        assertFalse(s.getName().equals("name"));
        assertFalse(s.getName().equals("bla"));
    }

    public void testGetRating() throws Exception {
        int i = 0;
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",i,"112",0, "image");
        assertEquals(s.getRating(),i);
        i++;
        s = ObjectFactory.getNewSeller("username","password","email","name",i,"112",0, "image");
        assertEquals(s.getRating(),i);
    }

    public void testSetRating() throws Exception {
        int i = 1;
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        s.setRating(i);
        assertEquals(s.getRating(),i);
        i++;
        s.setRating(i);
        assertEquals(s.getRating(), i);
    }

    public void testGetImage() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getImage(), "image");
        s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image1");
        assertEquals(s.getImage(), "image1");
    }

    public void testSetImage() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        s.setImage("image1");
        assertEquals(s.getImage(), "image1");
        s.setImage("image2");
        assertEquals(s.getImage(), "image2");
    }

    public void testGetPassword() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getPassword(),"password");
        s =ObjectFactory.getNewSeller("username","pass","email","name",0,"112",0, "image");
        assertEquals(s.getPassword(),"pass");
    }

    public void testSetPassword() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        s.setPassword("pass");
        assertEquals(s.getPassword(), "pass");
        s.setPassword("pass1");
        assertEquals(s.getPassword(), "pass1");
    }

    public void testGetMobileNumber() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        assertEquals(s.getMobileNumber(), "112");
        s=ObjectFactory.getNewSeller("username","password","email","name",0,"123",0, "image");
        assertEquals(s.getMobileNumber(), "123");
        s=ObjectFactory.getNewSeller("username","password","email","name",0,"1234",0, "image");
        assertEquals(s.getMobileNumber(), "1234");
    }

    public void testSetMobileNumber() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        s.setMobileNumber("123");
        assertEquals(s.getMobileNumber(), "123");
        s.setMobileNumber("1234");
        assertEquals(s.getMobileNumber(), "1234");
    }

    public void testGetVoters() throws Exception {
        int i = 0;
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",i, "image");
        assertEquals(s.getVoters(), i);
        i++;
        s= ObjectFactory.getNewSeller("username","password","email","name",0,"112",i, "image");
        assertEquals(s.getVoters(), i);
        i++;
        s= ObjectFactory.getNewSeller("username","password","email","name",0,"112",i, "image");;
        assertEquals(s.getVoters(), i);
    }

    public void testSetVoters() throws Exception {
        int i = 0;
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",i, "image");
        i++;
        s.setVoters(i);
        assertEquals(s.getVoters(), i);
        i++;
        s.setVoters(i);
        assertEquals(s.getVoters(), i);
    }

    public void testIncreaseRating() throws Exception {
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,"112",0, "image");
        s.increaseRating(5);
        assertTrue(s.getRating()==5&&s.getVoters()==1);
        s.increaseRating(4);
        assertTrue(s.getRating()==9&&s.getVoters()==2);
        s.increaseRating(10);
        assertTrue(s.getRating()==19&&s.getVoters()==3);

    }

    public void testGetID() throws Exception {
        int i = 0;
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,0,"112", "image", i, true,true);
        assertEquals(s.getID(), i);
        i++;
        s=ObjectFactory.getNewSeller("username","password","email","name",0,0,"112", "image", i, true,true);
        assertEquals(s.getID(), i);
        i++;
        s=ObjectFactory.getNewSeller("username","password","email","name",0,0,"112", "image", i, true,true);
        assertEquals(s.getID(), i);
    }

    public void testSetID() throws Exception {
        int i = 0;
        Seller s = ObjectFactory.getNewSeller("username","password","email","name",0,0,"112", "image", i, true,true);
        i++;
        s.setID(i);
        assertEquals(s.getID(), i);
        i++;
        s.setID(i);
        assertEquals(s.getID(), i);
    }

}