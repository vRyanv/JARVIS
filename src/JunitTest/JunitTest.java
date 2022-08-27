package JunitTest;

import controller.AdminController;
import controller.ClassRoomController;
import controller.CourseController;
import controller.LoginController;
import library.fileProcess.FileProcess;
import model.course.Course;
import model.data.Data;
import model.user.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.Socket;
import java.util.TreeMap;

public class JunitTest {

    @BeforeEach
    public void SetUp()
    {
        Data.courseList = (TreeMap<String, Course>) FileProcess.readObject("src/model/course/courseList.dat");
    }
    @Test
    public void TestCheckInvalidEmail()
    {
        LoginController loginController = new LoginController();
        Assertions.assertFalse(loginController.checkInvalidEmail("abcdef"));
    }

    @Test
    public void TestEmailExisted()
    {
        LoginController loginController = new LoginController();
        Assertions.assertTrue(loginController.checkEmailExisted("admin@123"));
    }

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
    public void TestGetCourseDetail()
    {
        AdminController adminController = new AdminController();
        String courseId = "1819";
        Assertions.assertNotNull(adminController.getCourseDetail(courseId));
    }

    @Test
    public void TestSaveAs()
    {
        AdminController adminController = new AdminController();
        Assertions.assertTrue(adminController.SaveAs(System.getProperty("user.dir")+"\\courseSaveAs.dat"));
    }

    @Test
    public void TestUpdateCourse()
    {
        AdminController adminController = new AdminController();
        String id = "1819";
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
        String id = "1819";
        Assertions.assertTrue(adminController.DeleteCourse(id));
    }
    @Test
    public void RegisterCourse()
    {
        CourseController courseController = new CourseController();
        Assertions.assertTrue(courseController.RegisterCourse("2108", "admin@123"));
    }

    @Test
    public void TestStartServer()
    {
        AdminController adminController = new AdminController();
        Assertions.assertTrue(adminController.NewServer());
    }
    @Test
    public void TestStopServer()
    {
        AdminController adminController = new AdminController();
        Assertions.assertTrue(adminController.KillServer());
    }

    @Test
    public void TestJoinRoom()
    {
        AdminController adminController = new AdminController();
        adminController.NewServer();

        ClassRoomController classRoomController = new ClassRoomController();
        Assertions.assertNotNull(classRoomController.EnrollRoom("2108", "admin@123"));
    }

    @Test
    public void TestSendMess()
    {
        AdminController adminController = new AdminController();
        adminController.NewServer();

        ClassRoomController classRoomController = new ClassRoomController();
        Socket socket = classRoomController.EnrollRoom("2108", "ryan@123");
        if(socket != null)
        {
            Assertions.assertTrue(classRoomController.SendMess("hello"));
        }
    }

    @Test
    public void TestLoadDataFromDisk()
    {
        File file = new File("backupFile/courseList.dat");
        AdminController adminController = new AdminController();
        String expected = "success";
        Assertions.assertEquals(expected, adminController.LoadDataFromDisk(file));
    }

}

