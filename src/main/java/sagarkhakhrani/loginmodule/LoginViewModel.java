package sagarkhakhrani.loginmodule;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sagar.khakhrani on 05-04-2017.
 */

public class LoginViewModel extends BaseObservable {

 //All the hints of edittexts .

    public String passwordHint;
    private String emailHint;

     //Data of edittexts you require for data saving.
    private String userEmail;
    private String userPassword;


     // Setting texts of forgot password link and

    public String forgotPasswordText;
    public String loginButtonText;


     // Variables holding Errors for Runtime Validation

    private String errorPassword;
    private String errorEmail;
//Their Getters are also Necessary To get RunTime Validation Errors



    //  Constructor

    public LoginViewModel(String emailHint, String passwordHint, String forgotPasswordText, String loginButtonText) {
        this.emailHint = emailHint;
        this.passwordHint = passwordHint;
        this.forgotPasswordText = forgotPasswordText;
        this.loginButtonText = loginButtonText;
    }

    //getters and setters are necessary
//
    public String getEmailHint() {
        return emailHint;
    }

    public void setEmailHint(String emailHint) {
        this.emailHint = emailHint;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        /** To get value of edittext enterd by user, This Updates the value of userEmail on Every LEtter Entered by User*/
        notifyPropertyChanged(R.id.EmaileditText);
        /**to check Email for validation on every character inserted by user*/
      //Similarly This
        notifyPropertyChanged(BR.errorEmail);
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }
    @Bindable
    public String getUserPassword() {
        return userPassword;

    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        // Same for pass word
        notifyPropertyChanged(R.id.PasswordeditText);
        //this line will notify new value of password every time
        notifyPropertyChanged(sagarkhakhrani.loginmodule.BR.errorPassword);

    }

    public String getForgotPasswordText() {
        return forgotPasswordText;
    }

    public void setForgotPasswordText(String forgotPasswordText) {
        this.forgotPasswordText = forgotPasswordText;
    }

    public String getLoginButtonText() {
        return loginButtonText;
    }

    public void setLoginButtonText(String loginButtonText) {
        this.loginButtonText = loginButtonText;
    }
// Now we have data , But is not validated, Lets do that

    /**
     *
     *
     * VALIDATIONS All Four Methods Required
     *
     */


    //These Methods Check For Validation Every Time user enters a character
    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        pattern = Pattern.compile(PASSWORD_PATTERN);

        matcher = pattern.matcher(password);
        return matcher.matches();
    }


    @Bindable
    public String getErrorPassword() {
        if(userPassword==null){
            return "Please Enter";
        }
        else if(userPassword.length()<8){
            return "Too Short..!!";
        }else if(!isValidPassword(userPassword)){
            return "Your Password Must be combination of \n Small,Captital & Special Characters";
        }else {
            return null;
        }


    }

    //These Methods Check For Validation Every Time user enters a character


    public static boolean isValidEmail(final String userEmail) {
        Pattern pattern;
        Matcher matcher;
        // final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern= android.util.Patterns.EMAIL_ADDRESS;
        matcher = pattern.matcher(userEmail);
        return matcher.matches();
    }


    // If you Dont Bind Here You Wont get BR values
    @Bindable
    public String getErrorEmail() {
        if(userEmail == null)
        {
            return "Please Enter";
        }else if(!isValidEmail(userEmail))
        {
            return "Enter valid Id";
        }
        else
        {
            return null;
        }
    }
/**
 * Now Your XML is bound with ViewModel
 * Lets Check now
 *
 * Before , we need click listener
 */



}
