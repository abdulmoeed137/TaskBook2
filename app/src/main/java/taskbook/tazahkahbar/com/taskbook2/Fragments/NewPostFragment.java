package taskbook.tazahkahbar.com.taskbook2.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import taskbook.tazahkahbar.com.taskbook2.Model.PostModel;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;
import taskbook.tazahkahbar.com.taskbook2.Toast.Toast;
import taskbook.tazahkahbar.com.taskbook2.Utilities.utils;
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
    TextView name ;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new_post, container, false);
        initialize();
        setUpComponents();
        return rootView;
    }

    private void setUpComponents() {

        name . setText(new SessionManager(c).getFname()+" "+new SessionManager(c).getLname());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                utils.ProgressStart(progressBar,c);

                String post = post_text.getText().toString();

                if (Validity.isPostTrue(post,c))
                {
                    ref.child("posts").child(new SessionManager(c).getId()).push().setValue(new PostModel(
                            new SessionManager(c).getUsername(),post,
                            "0"), new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            utils.ProgressEnd(progressBar,c);
                            if (databaseError == null)
                            {
                                Toast.makeCustomToast(c,"Success");
                                post_text.setText("");
                            }
                            else
                            {
                                Toast.makeCustomErrorToast(c,"Error");
                            }
                        }
                    });

                }
                else   utils.ProgressEnd(progressBar,c);
            }
        });


    }

    private void initialize() {

        c= getActivity();
        post_text = (EditText)rootView.findViewById(R.id.post_text);
        submit= (LinearLayout)rootView.findViewById(R.id.submit);
        name = (TextView)rootView.findViewById(R.id.name);
        ref = FirebaseDatabase.getInstance().getReference();
        progressBar = (ProgressBar)rootView.findViewById(R.id.pbar);
        progressBar.bringToFront();
    }


}
