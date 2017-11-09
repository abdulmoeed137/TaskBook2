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
import com.google.firebase.auth.FirebaseUser;
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
import taskbook.tazahkahbar.com.taskbook2.Model.SignUpModel;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;
import taskbook.tazahkahbar.com.taskbook2.Toast.Toast;
import taskbook.tazahkahbar.com.taskbook2.Utilities.utils;
import taskbook.tazahkahbar.com.taskbook2.Validity.Validity;

public class SignupActivity extends AppCompatActivity {

    EditText fname,lname,email,uname,password;
    Button SignupButton;
    Context c;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    DatabaseReference ref ;
    int user_validity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initialize();
        setUpComponents();
    }

    private void setUpComponents() {

        uname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String _uname = uname.getText().toString();
                if (!_uname.trim().isEmpty())
                {
                    if (_uname.length()>4)
                    {
                        if (!hasFocus){
                            utils.ProgressStart(progressBar,c);

                            ref.child("usernames").child(_uname).addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists())
                                    {
                                        utils.ProgressEnd(progressBar,c);
                                        Toast.makeCustomErrorToast(c,"Username Already Exist");
                                        user_validity = 0;

                                    }
                                    else
                                    {
                                        Toast.makeCustomToast(c,"Username Available");
                                        user_validity = 1;
                                        utils.ProgressEnd(progressBar,c);
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                    Toast.makeCustomErrorToast(c,databaseError.getMessage().toString());
                                    utils.ProgressEnd(progressBar,c);
                                }
                            });
                        }}
                    else
                        Toast.makeCustomErrorToast(c,"Username length must be grater then 4");
                }
            }
        });

        SignupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String _fname = fname.getText().toString();
                final String _lname = lname.getText().toString();
                final String _email = email.getText().toString();
                final String _uname = uname.getText().toString();
                String _password = password.getText().toString();

                if (Validity.isFirstNameTrue(_fname,c) &&  Validity.isLastNameTrue(_lname,c)  && Validity.isEmailTrue(_email,c)
                        && Validity.isUserNameTrue(_uname,c) && Validity.isPasswordTrue(_password,c))
                {

                    if (user_validity == 1){
                        utils.ProgressStart(progressBar,c);
                        firebaseAuth.createUserWithEmailAndPassword(_email,_password)
                                .addOnCompleteListener(SignupActivity.this,new OnCompleteListener<AuthResult>() {

                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful())
                                        {

                                            String user_id = task.getResult().getUser().getUid();

                                            ref.child("user_profile").child(user_id).setValue(new SignUpModel(_uname,_fname,_lname));
                                            ref.child("usernames").child(_uname).setValue("taken");
                                            utils.ProgressEnd(progressBar,c);

                                            new SessionManager(c,user_id,_fname,_lname,_uname,_email);
                                            Toast.makeCustomToast(c, "Registered Successfully");
                                            finish();
                                            startActivity(new Intent(c,PeopleYouMayKnowActivity.class));

                                        }
                                        else{
                                            Toast.makeCustomToast(c,task.getException().getMessage().toString());
                                            utils.ProgressEnd(progressBar,c);}
                                    }
                                });

                    }
                    else
                    {
                        Toast.makeCustomErrorToast(c,"Please choose correct username");
                    }
                }

            }
        });


    }

    private void initialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        fname =(EditText) this.findViewById(R.id.first_name);
        lname = (EditText)this.findViewById(R.id.last_name);
        email =(EditText) this.findViewById(R.id.email);
        uname = (EditText)this.findViewById(R.id.user_name);
        password =(EditText) this.findViewById(R.id.password);
        SignupButton = (Button)this.findViewById(R.id.signupButton);
        c = SignupActivity.this;
        progressBar =(ProgressBar) findViewById(R.id.pbar);
        progressBar.bringToFront();
        ref = FirebaseDatabase.getInstance().getReference();

    }
}