package taskbook.tazahkahbar.com.taskbook2.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    DatabaseReference ref ;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        setUpComponents();
    }

    private void setUpComponents() {

        if (new SessionManager(c).CheckIfSessionExist())
            {
                finish();
                startActivity(new Intent(c,DashboardActivity
                .class));
            }

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                startActivity(new Intent(c, SignupActivity.class));


            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String _email = email.getText().toString();
                String _password = password.getText().toString();
                if (Validity.isEmailTrue(_email,c) && Validity.isPasswordTrue(_password,c))
                {
                    progressBar.setVisibility(View.VISIBLE);


                    firebaseAuth.signInWithEmailAndPassword(_email,_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()){

                                    final String user_id = task.getResult().getUser().getUid();

                                    DatabaseReference user = ref.child("user_profile").child(user_id);

                                    user.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                          String username=   dataSnapshot.child("username").getValue().toString();
                                            String firstname = dataSnapshot.child("first_name").getValue().toString();
                                            String lastname = dataSnapshot.child("last_name").getValue().toString();
                                            new SessionManager(c,user_id,firstname,lastname,username,_email);
                                            Toast.makeCustomToast(c, "Login Success");
                                            finish();
                                            startActivity(new Intent(c,DashboardActivity.class));
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                            Toast.makeCustomErrorToast(c,databaseError.getMessage());
                                        }
                                    });
                                }
                                else
                                {
                                  Toast.makeCustomErrorToast(c,task.getException().getMessage().toString());
                                }}
                            });

                }
            }
        });

    }

    private void initialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText)this.findViewById(R.id.email);
        password = (EditText)this.findViewById(R.id.password);
        loginButton =(Button) this.findViewById(R.id.loginButton);
        signupButton =(TextView)this.findViewById(R.id.signupButton);
        c = LoginActivity.this;
        progressBar=(ProgressBar) findViewById(R.id.pbar);
        progressBar.bringToFront();
        ref = FirebaseDatabase.getInstance().getReference();
    }
}
