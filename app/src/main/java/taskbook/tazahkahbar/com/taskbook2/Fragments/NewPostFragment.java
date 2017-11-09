package taskbook.tazahkahbar.com.taskbook2.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import taskbook.tazahkahbar.com.taskbook2.Model.PostModel;
import taskbook.tazahkahbar.com.taskbook2.Model.SignUpModel;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;
import taskbook.tazahkahbar.com.taskbook2.Toast.Toast;
import taskbook.tazahkahbar.com.taskbook2.Validity.Validity;

/**
 * Created by lenovo on 11/1/2017.
 */

public class NewPostFragment extends Fragment
{
    View rootView;

    Context c;

    EditText post_text ;

    LinearLayout submit;
    DatabaseReference ref ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new_post, container, false);
        initialize();
        setUpComponents();
        return rootView;
    }

    private void setUpComponents() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post = post_text.getText().toString();
                if (Validity.isPostTrue(post,c))
                {
                    ref.child("posts").child(new SessionManager(c).getId()).push().setValue(new PostModel(
                            new SessionManager(c).getUsername(),post
                    ), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null)
                            {
                                Toast.makeCustomToast(c,"Success");
                            }
                            else
                            {
                                Toast.makeCustomErrorToast(c,"Error");
                            }
                        }
                    });
                }
            }
        });


    }

    private void initialize() {

        c= getActivity();
        post_text = (EditText)rootView.findViewById(R.id.post_text);
        submit= (LinearLayout)rootView.findViewById(R.id.submit);
        ref = FirebaseDatabase.getInstance().getReference();
    }


}
