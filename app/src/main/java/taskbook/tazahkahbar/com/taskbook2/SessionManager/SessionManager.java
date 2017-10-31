package taskbook.tazahkahbar.com.taskbook2.SessionManager;

import android.content.Context;
import android.content.SharedPreferences;

import taskbook.tazahkahbar.com.taskbook2.Constants.Constants;

/**
 * Created by lenovo on 8/27/2017.
 */

public class SessionManager {
    private String id, fname ,lname,username, email , password;
    SharedPreferences session ;
    public SessionManager(){}
    public SessionManager(Context c)
    {
        session= c.getSharedPreferences(Constants.SESSION, Context.MODE_PRIVATE);
        this. id = session.getString("id",null);
        this. fname = session.getString("fname",null);
        this. lname = session.getString("lname",null);
        this. username = session.getString("username",null);
        this.email= session.getString("email",null);

    }


    public SessionManager(Context c , String id , String fname, String lname, String username, String email)
        {
            session= c.getSharedPreferences(Constants.SESSION, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = session.edit();
            editor.putString("id", id);
            editor.putString("fname", fname);
            editor.putString("lname", lname);
            editor.putString("username", username);
            editor.putString("email",email);
            editor.commit();

            new SessionManager(c);

        }

public boolean CheckIfSessionExist(){
    if (session.contains("id"))
        return true;
    else
        return  false;

}
    public void Logout (){
        SharedPreferences.Editor editor = session.edit();
        editor.clear();
        editor.commit();

        this.id=null;
        this.fname=null;
        this.lname=null;
        this.username= null;
        this.email=null;

    }

    public String getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
