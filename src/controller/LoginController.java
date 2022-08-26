package controller;

import library.fileProcess.FileProcess;
import model.data.Data;
import model.user.User;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController{

    public boolean LoadData()
    {
        TreeMap<String, User> userTreeMap = (TreeMap<String, User>) FileProcess.readObject(Data.pathUserList);
        return userTreeMap == null;
    }

    public boolean loginProcess(String email, char[] pass)
    {
        if(Data.userList.containsKey(email))
        {
            return Arrays.equals(Data.userList.get(email).getPassword(), pass);
        }
        return false;
    }

    public String registerProcess(String email, char[] pass,  char[] passConfirm, String role)
    {
        if(email.isBlank() || pass.length == 0 || passConfirm.length == 0)
        {
            return "blank";
        }
        else
        {
            if(!checkInvalidEmail(email))
            {
                return "emailInvalid";
            }
            else if(checkEmailExisted(email))
            {
                return "emailExisted";
            }
            else if (!checkPasswordRegister(pass))
            {
                return "passInvalid";
            }
            else if (!checkConfirmPass(pass, passConfirm))
            {
                return "confirmPassInvalid";
            }
            else
            {
                if(saveAccount(email, pass, role))
                {
                    return "success";
                }
                else
                {
                    return "fail";
                }
            }
        }
    }

    private boolean checkInvalidEmail(String email)
    {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean checkEmailExisted(String email)
    {
        return Data.userList == null ? false : Data.userList.containsKey(email);

    }

    private boolean checkPasswordRegister(char[] pass)
    {
      return pass.length >= 5;
    }

    private boolean checkConfirmPass(char[] pass, char[] confirmPass)
    {
        return Arrays.equals(pass, confirmPass);
    }

    private boolean saveAccount(String email, char[] pass, String role)
    {
        Data.userList.put(email, new User(email, pass, role));
        return FileProcess.writeObject(Data.pathUserList , Data.userList);
    }


}
