package JunitTest;

import controller.AdminController;
import controller.LoginController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JunitTest {
    @Test
    public void TestRegister()
    {
        LoginController loginController = new LoginController();
        String email = "ryan@123";
        String pass = "12345";
        String passConfirm = "12345";
        String role = "user";
        String expected = "success";
        Assertions.assertEquals(expected, loginController.registerProcess(email, pass.toCharArray(), passConfirm.toCharArray(), role));
    }

    @Test
    public void TestLogin()
    {
        String email = "ryan@123";
        String pass = "12345";
        LoginController loginController = new LoginController();
        Assertions.assertTrue(loginController.loginProcess(email, pass.toCharArray()));
    }

    @Test
    public void TestAddCourse()
    {
        AdminController adminController = new AdminController();
        String id = "1819";
        String name = "Networking";
        int numOfLessons = 10;
        String description = "something, bla...bla...";
        String expected = "success";
        Assertions.assertEquals(expected, adminController.AddNewCourse(id, name, numOfLessons, description));
    }

    @Test
    public void TestSaveCourse()
    {
        AdminController adminController = new AdminController();
        String id = "2002";
        String name = "Java";
        int numOfLessons = 10;
        String description = "something, bla...bla...";
        String type = "newCourse";
        Assertions.assertTrue(adminController.saveCourse(id, name, numOfLessons, description, type));
    }

    @Test
    public void TestUpdateCourse()
    {
        AdminController adminController = new AdminController();
        String id = "2002";
        String name = "JavaScript";
        int numOfLessons = 20;
        String description = "something, bla...bla...";
        String type = "updateCourse";
        Assertions.assertTrue(adminController.saveCourse(id, name, numOfLessons, description, type));
    }

    @Test
    public void TestDeleteCourse()
    {
        AdminController adminController = new AdminController();
        String id = "2002";
        Assertions.assertTrue(adminController.DeleteCourse(id));
    }


}

//    public void testAddCan(){
////        CandidateManager m = new CandidateManager("");
//        List<Candidate> lstOld = (List<Candidate>)
//                XFile.readObject("candidate.dat");
//        int s = lstOld.size();
//        Candidate c = new Candidate(
//                "GCC01", "abc",
//                Double.parseDouble("4.5"),
//                Double.parseDouble("4.5"),
//                Double.parseDouble("4.5"),
//                true,
//                "1990-02-02");
//        lstOld.add(c);
//        int expected = s + 1;
//        Assertions.assertEquals(expected, lstOld.size());
//
//    }
