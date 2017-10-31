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

import org.json.JSONArray;
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

public class LoginActivity extends AppCompatActivity {

    EditText email,password;
    Button loginButton;
    TextView signupButton;
    Context c;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        setUpComponents();
    }

    private void setUpComponents() {


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                startActivity(new Intent(c, SignupActivity.class));
                finish();

            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _email = email.getText().toString();
                String _password = password.getText().toString();
                if (Validity.isEmailTrue(_email,c) && Validity.isPasswordTrue(_password,c))
                {

                    final HashMap<String, String> hashMap = new HashMap<String, String>();

                    hashMap.put("email", _email);
                    hashMap.put("password", _password);

                    Executor executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        public void run() {

                            JSONObject response = HttpRequest.SyncHttpRequest(c, Constants.login, hashMap, progressBar);

                            if (response != null) {
                                Log.d("response",response+"");
                                try {

                                    if (response.names().get(0).equals("success")) {

                                        JSONObject row = response.getJSONObject("success");
                                        //JSONObject row = data.getJSONObject(0);

                                        String id = row.getString("user_id");
                                        String fname = row.getString("first_name");
                                        String lname = row.getString("last_name");
                                        String username = row.getString("user_name");
                                        String email = row.getString("email");


                                        Toast.makeCustomToast(c,"Login Success");


                                        new SessionManager(c,id,fname,lname,username,email);

                                        startActivity(new Intent(c, DashboardActivity.class));
                                        finish();

                                    } else if (response.names().get(0).equals("failed")) {
                                        Toast.makeCustomErrorToast(c,"Invalid Number or Password");

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
        email = this.findViewById(R.id.email);
        password = this.findViewById(R.id.password);
        loginButton =this.findViewById(R.id.loginButton);
        signupButton =this.findViewById(R.id.signupButton);
        c = LoginActivity.this;
        progressBar = findViewById(R.id.pbar);
        progressBar.bringToFront();
    }
}
