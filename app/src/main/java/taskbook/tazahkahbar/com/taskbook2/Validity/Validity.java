package taskbook.tazahkahbar.com.taskbook2.Validity;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;

import taskbook.tazahkahbar.com.taskbook2.Toast.Toast;
import taskbook.tazahkahbar.com.taskbook2.Utilities.utils;

/**
 * Created by lenovo on 8/26/2017.
 */

public class Validity {

    static public boolean isPasswordTrue(String s, Context context)
    {

        if (s.isEmpty()) {

            setStatus("Password Is Empty!",context);
            return false;
        }
        if (s.length()<4)
        {
            setStatus("Password too short!",context);
            return false;
        }
        else return true;
    }
    static public boolean isTransactionPasswordTrue(String s, Context context)
    {

        if (s.isEmpty()) {

            setStatus("Transaction Password Is Empty!",context);
            return false;
        }
        if (s.length()!= 4)
        {
            setStatus("Transaction Password must have 4 digits!",context);
            return false;
        }
        else return true;
    }
    static public boolean isAmountTrue(String s, Context context)
    {

        if (s.isEmpty()) {

            setStatus("Amount Is Empty!",context);
            return false;
        }
        if (Double.parseDouble(s)<=0)
        {
            setStatus("Amount must be more than 0",context);
            return false;
        }
        else return true;
    }
    static public boolean isNameTrue(String name, Context context)
    {

        if (name.isEmpty()) {

            setStatus("Name Is Empty!",context);
            return false;
        }
        if (name.length()<=4)
        {
            setStatus("Name too short!",context);
            return false;
        }
        else return true;
    }

    static public boolean isFirstNameTrue(String fname, Context context)
    {

        if (fname.isEmpty()) {

            setStatus("First Name Is Empty!",context);
            return false;
        }
        if (fname.length()<=4)
        {
            setStatus("First Name too short!",context);
            return false;
        }
        else return true;
    }

    static public boolean isLastNameTrue(String lname, Context context)
    {

        if (lname.isEmpty()) {

            setStatus("Last Name Is Empty!",context);
            return false;
        }
        if (lname.length()<=4)
        {
            setStatus("Last Name too short!",context);
            return false;
        }
        else return true;
    }

    static public boolean isUserNameTrue(String uname, Context context)
    {

        if (uname.isEmpty()) {

            setStatus("User Name Is Empty!",context);
            return false;
        }
        if (uname.length()<=4)
        {
            setStatus("User Name too short!",context);
            return false;
        }
        else return true;
    }

    static public boolean isBankNameTrue(String name, Context context)
    {

        if (name.isEmpty()) {

            setStatus("Bank Name Is Empty!",context);
            return false;
        }
        if (name.length()<=4)
        {
            setStatus("Bank Name too short!",context);
            return false;
        }
        else return true;
    }
    static public boolean isTitleTrue(String name, Context context)
    {

        if (name.isEmpty()) {

            setStatus("Title Is Empty!",context);
            return false;
        }
        if (name.length()<=4)
        {
            setStatus("Title too short!",context);
            return false;
        }
        else return true;
    }
    static public boolean isAccNumberTrue(String name, Context context)
    {

        if (name.isEmpty()) {

            setStatus("Account Number Is Empty!",context);
            return false;
        }
        if (name.length()<=4)
        {
            setStatus("Account Number too short!",context);
            return false;
        }
        else return true;
    }
    static public boolean isContactTrue(String s, Context context)
    {

        if (s.isEmpty()) {

            setStatus("Oops! You forgot to enter Number :)",context);
            return false;
        }
        if ( !s.startsWith("3")) {
            setStatus("Oops! Contact should start with 3xx :)",context);
            return false;
        }
        if (s.length()!=10)
        {
            setStatus("Oops! Incorrect Contact Number :)",context);
            return false;
        }
        if ( !s.startsWith("3")) {
            setStatus("Oops! Contact should start with 3xx :)",context);
            return false;
        }
        else return true;
    }
    static public boolean isStringTrue(String name, Context context)
    {

        if (name.isEmpty()) {

            setStatus("Title Is Empty!",context);
            return false;
        }
        if (name.length()<=4)
        {
            setStatus("Title too short!",context);
            return false;
        }
        else return true;
    }
    static public boolean isOption1True(String o1, Context context)
    {

        if (o1.isEmpty()) {

            setStatus("Option1 is Empty!",context);
            return false;
        }
        if (o1.length()<=2)
        {
            setStatus("Option1 is too short!",context);
            return false;
        }
        else return true;
    }
    static public   boolean isIdTrue(String id, Context context)
    {
        if (id.isEmpty()) {
            setStatus("Id Is Empty!",context);
            return false;
        }
        if (Integer.parseInt(id)<1)
        {
            setStatus("Id too short!",context);
            return false;
        }
        else return true;
    }
    static public   boolean isEmailTrue(String email, Context context)
    {
        if (email.isEmpty()) {
            setStatus("Email Is Empty!",context);
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            setStatus("Email not Correct!",context);
            return false;
        }
        else return true;
    }

    static public   boolean isCodeTrue(String Code, Context context)
    {
        if (Code.isEmpty()) {
            setStatus("Verification Code Is Empty!",context);
            return false;
        }
        if (Code.length()<=4)
        {
            setStatus("Incorrect Verification Code!",context);
            return false;
        }

        else return true;
    }

    static  public void setStatus (String msg, Context context)
    {


        Toast.makeCustomErrorToast(context,msg);
        utils.hideSoftKeyboard(((Activity)context));

    }
}
