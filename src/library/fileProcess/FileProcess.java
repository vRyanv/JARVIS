package library.fileProcess;

import com.sun.tools.javac.Main;
import model.course.Course;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeMap;

public class FileProcess {
    public static boolean writeObject(String path,  Object object)
    {
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            return true;
        }catch (Exception ex){
            return false;
        }

    }

    public static Object readObject(String path)
    {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            Object object =  objectInputStream.readObject();
            objectInputStream.close();
            return object;
        }catch (Exception ex){
            return null;
        }
    }

}
