package taskbook.tazahkahbar.com.taskbook2.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import taskbook.tazahkahbar.com.taskbook2.Adapters.PeopleYouMayKnowAdapter;
import taskbook.tazahkahbar.com.taskbook2.Fragments.CheckedFragment;
import taskbook.tazahkahbar.com.taskbook2.Fragments.UncheckedFragment;
import taskbook.tazahkahbar.com.taskbook2.Model.PeopleyoumayknowModel;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;
import taskbook.tazahkahbar.com.taskbook2.Toast.Toast;

import static android.R.id.list;

public class ProfileActivity extends AppCompatActivity
{

    Context c;

    TextView button_checked, button_unchecked;
    LinearLayout linear_checked, linear_unchecked;
    DatabaseReference ref ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        initialize();
        setUpComponents();
    }

    private void setUpComponents() {

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        String name = bundle.getString("name");

     /*   TextView txtView = (TextView) findViewById(R.id.name);
        txtView.setText(id);*/


        ref.child("user_profile").child(id).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("new data", "onDataChange: "+dataSnapshot);

                String uname =   dataSnapshot.child("username").getValue().toString();
                String fname = dataSnapshot.child("firstname").getValue().toString();
                String lname = dataSnapshot.child("lastname").getValue().toString();

                TextView txtView1 = (TextView) findViewById(R.id.name);
                txtView1.setText(fname);

                TextView txtView2 = (TextView) findViewById(R.id.username);
                txtView2.setText(uname);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeCustomErrorToast(c,databaseError.getMessage().toString());
            }
        });




        loadFragment(new CheckedFragment());

        button_unchecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(new UncheckedFragment());

                linear_unchecked.setBackground(getResources().getDrawable(R.drawable.rounded_bg_yellow_right_rounded));
                button_unchecked.setTextColor(getResources().getColor(R.color.white));

                linear_checked.setBackground(getResources().getDrawable(R.drawable.rounded_bg_white_left_rounded));
                button_checked.setTextColor(getResources().getColor(R.color.mainColor));

            }
        });

        button_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(new CheckedFragment());

                linear_checked.setBackground(getResources().getDrawable(R.drawable.rounded_bg_yellow_left_rounded));
                button_checked.setTextColor(getResources().getColor(R.color.white));

                linear_unchecked.setBackground(getResources().getDrawable(R.drawable.rounded_bg_white_right_rounded));
                button_unchecked.setTextColor(getResources().getColor(R.color.mainColor));


            }
        });

    }

    private void initialize() {

        c = ProfileActivity.this;

        ref = FirebaseDatabase.getInstance().getReference();

        button_checked= (TextView)findViewById(R.id.button_checked);

        button_unchecked = (TextView)findViewById(R.id.button_unchecked);

        linear_checked = (LinearLayout)findViewById(R.id.linear_checked);

        linear_unchecked = (LinearLayout)findViewById(R.id.linear_unchecked);
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}
