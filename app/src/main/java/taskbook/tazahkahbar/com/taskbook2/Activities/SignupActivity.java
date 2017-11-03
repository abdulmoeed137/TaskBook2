package taskbook.tazahkahbar.com.taskbook2.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import taskbook.tazahkahbar.com.taskbook2.Constants.Constants;
import taskbook.tazahkahbar.com.taskbook2.HttpRequest.HttpRequest;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;
import taskbook.tazahkahbar.com.taskbook2.Toast.Toast;
import taskbook.tazahkahbar.com.taskbook2.Validity.Validity;

public class SignupActivity extends AppCompatActivity {

    EditText fname,lname,email,uname,password;
    Button SignupButton;
    Context c;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initialize();
        setUpComponents();
    }

    private void setUpComponents() {


   email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
       @Override
       public void onFocusChange(View v, boolean hasFocus)
       {
           String _email = email.getText().toString();

           if (!_email.isEmpty())
           {
               if (Validity.isEmailTrue(_email,c))
               {

                   final HashMap<String, String> hashMap = new HashMap<String, String>();
                   hashMap.put("email", _email);


                   Executor executor = Executors.newSingleThreadExecutor();
                   executor.execute(new Runnable() {
                       public void run() {

                           JSONObject response = HttpRequest.SyncHttpRequest(c, Constants.emailcheck, hashMap, progressBar);

                           if (response != null) {
                               Log.d("response",response+"");
                               try {

                                   if (response.names().get(0).equals("success")) {



                                       Toast.makeCustomErrorToast(c,response.getString("success"));




                                   } else if (response.names().get(0).equals("failed")) {
                                       Toast.makeCustomErrorToast(c,response.getString("failed"));

                                   } else {
                                       Toast.makeCustomErrorToast(c,"Server Maintenance is on Progress");
                                   }
                               } catch (JSONException e) {
                                   Toast.makeCustomErrorToast(c,"Server Maintenance is on Progress");

                               }
                           }

                       }
                   });


               }
           }


       }
   });





        uname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String _uname = uname.getText().toString();

                if (!_uname.isEmpty())
                {
                    if (Validity.isUserNameTrue(_uname,c))
                    {

                        final HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("user_name", _uname);


                        Executor executor = Executors.newSingleThreadExecutor();
                        executor.execute(new Runnable() {
                            public void run() {

                                JSONObject response = HttpRequest.SyncHttpRequest(c, Constants.usernamecheck, hashMap, progressBar);

                                if (response != null) {
                                    Log.d("response",response+"");
                                    try {

                                        if (response.names().get(0).equals("success")) {



                                            Toast.makeCustomErrorToast(c,response.getString("success"));




                                        } else if (response.names().get(0).equals("failed")) {
                                            Toast.makeCustomErrorToast(c,response.getString("failed"));

                                        } else {
                                            Toast.makeCustomErrorToast(c,"Server Maintenance is on Progress");
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeCustomErrorToast(c,"Server Maintenance is on Progress");

                                    }
                                }

                            }
                        });


                    }
                }


            }
        });






        SignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _fname = fname.getText().toString();
                String _lname = lname.getText().toString();
                String _email = email.getText().toString();
                String _uname = uname.getText().toString();
                String _password = password.getText().toString();

                if (Validity.isFirstNameTrue(_fname,c) &&  Validity.isLastNameTrue(_lname,c)  && Validity.isEmailTrue(_email,c)
                        && Validity.isUserNameTrue(_uname,c) && Validity.isPasswordTrue(_password,c))
                {

                    final HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("first_name", _fname);
                    hashMap.put("last_name", _lname);
                    hashMap.put("email", _email);
                    hashMap.put("user_name", _uname);
                    hashMap.put("password", _password);

                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        public void run() {

                            JSONObject response = HttpRequest.SyncHttpRequest(c, Constants.signup, hashMap, progressBar);

                            if (response != null) {
                                Log.d("response",response+"");
                                try {

                                    if (response.names().get(0).equals("success")) {



                                        Toast.makeCustomToast(c,"SignUp Successfully");


                                        startActivity(new Intent(c, LoginActivity.class));
                                        finish();

                                    } else if (response.names().get(0).equals("failed")) {
                                        Toast.makeCustomErrorToast(c,response.getString("failed"));

                                    } else {
                                        Toast.makeCustomErrorToast(c,"Server Maintenance is on Progress");
                                    }
                                } catch (JSONException e) {
                                    Toast.makeCustomErrorToast(c,"Server Maintenance is on Progress");

                                }
                            }

                        }
                    });

                }
            }
        });


    }

    private void initialize() {
        fname =(EditText) this.findViewById(R.id.first_name);
        lname = (EditText)this.findViewById(R.id.last_name);
        email =(EditText) this.findViewById(R.id.email);
        uname = (EditText)this.findViewById(R.id.user_name);
        password =(EditText) this.findViewById(R.id.password);
        SignupButton = (Button)this.findViewById(R.id.signupButton);
        c = SignupActivity.this;
        progressBar =(ProgressBar) findViewById(R.id.pbar);
        progressBar.bringToFront();
    }
}
