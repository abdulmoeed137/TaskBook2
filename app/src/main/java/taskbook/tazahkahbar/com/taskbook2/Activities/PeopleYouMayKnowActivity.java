package taskbook.tazahkahbar.com.taskbook2.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import taskbook.tazahkahbar.com.taskbook2.Adapters.HomeAdapter;
import taskbook.tazahkahbar.com.taskbook2.Adapters.PeopleYouMayKnowAdapter;
import taskbook.tazahkahbar.com.taskbook2.Model.PeopleyoumayknowModel;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;
import taskbook.tazahkahbar.com.taskbook2.Toast.Toast;

public class PeopleYouMayKnowActivity extends AppCompatActivity {

    ListView listview;
    Context c;
    DatabaseReference ref ;
    ArrayList<PeopleyoumayknowModel> list = new ArrayList<>();
    TextView button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peopleyoumayknow);

        initialize();
        setUpComponents();
    }

    private void setUpComponents() {

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(c,DashboardActivity.class));
            }
        });

        ref.child("user_profile").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("new data", "onDataChange: "+dataSnapshot);
                list.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    if (!postSnapshot.getKey().equals(new SessionManager(c).getId())){
                    list.add( new PeopleyoumayknowModel(postSnapshot.getKey(),
                            postSnapshot.child("username").getValue().toString(),
                            "image"
                    ));}

                }
                listview.setAdapter(new PeopleYouMayKnowAdapter(c,list));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeCustomErrorToast(c,databaseError.getMessage().toString());
            }
        });

    }

    private void initialize() {
        listview = (ListView)this.findViewById(R.id.mListView);
        c= PeopleYouMayKnowActivity.this;
        ref = FirebaseDatabase.getInstance().getReference();
        button_next = (TextView)findViewById(R.id.button_next);
    }
}
