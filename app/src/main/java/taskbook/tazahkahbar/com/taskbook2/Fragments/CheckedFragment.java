package taskbook.tazahkahbar.com.taskbook2.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import taskbook.tazahkahbar.com.taskbook2.Adapters.CheckedAdapter;
import taskbook.tazahkahbar.com.taskbook2.Model.PostModel;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 11/1/2017.
 */

public class CheckedFragment extends Fragment
{
    View rootView;
    ListView listview;
    Context c;
    ArrayList<PostModel> list = new ArrayList<>();
    DatabaseReference ref ;
    CheckedAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.checked_fragment, container, false);
        initialize();
        setUpComponents();
        return rootView;
    }

    private void setUpComponents() {

        listview.setAdapter(adapter);

        ref.child("posts").child(new SessionManager(c).getId()).orderByValue().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                list.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    String post = postSnapshot.child("post").getValue().toString();
                    String username = new SessionManager(c).getUsername();
                    String post_id = dataSnapshot.getKey().toString();
                    String status = postSnapshot.child("status").getValue().toString();
                    Log.d(TAG, "onDataChange: "+status);
                    if (status.equals("1"))
                        list.add(new PostModel(username,post,post_id,"",""));


                }
                Collections.reverse(list);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initialize() {
        listview = (ListView)rootView.findViewById(R.id.mListView);
        c= getActivity();
        ref = FirebaseDatabase.getInstance().getReference();
        adapter = new CheckedAdapter(c,list);
    }
}
