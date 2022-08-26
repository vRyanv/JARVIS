package controller;

import library.fileProcess.FileProcess;
import model.data.Data;
import model.user.User;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController{
    private TreeMap<String, User> userTreeMap;
    public boolean LoadData()
    {
        this.userTreeMap = (TreeMap<String, User>) FileProcess.readObject(Data.pathUserList);
        return this.userTreeMap == null;
    }

    public boolean loginProcess(String email, char[] pass)
    {
        if(this.userTreeMap.containsKey(email))
        {
            return Arrays.equals(this.userTreeMap.get(email).getPassword(), pass);
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
                if(saveAccount(email, pass,role))
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
        return userTreeMap == null ? false : userTreeMap.containsKey(email);

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
        this.userTreeMap.put(email, new User(email, pass, role));
        return FileProcess.writeObject(Data.pathUserList ,this.userTreeMap);
    }


}
