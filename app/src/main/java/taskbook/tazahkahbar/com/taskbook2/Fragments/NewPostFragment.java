package taskbook.tazahkahbar.com.taskbook2.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import taskbook.tazahkahbar.com.taskbook2.R;

/**
 * Created by lenovo on 11/1/2017.
 */

public class NewPostFragment extends Fragment
{
    View rootView;

    Context c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_new_post, container, false);
        initialize();
        setUpComponents();
        return rootView;
    }

    private void setUpComponents() {
        ;

    }

    private void initialize() {

        c= getActivity();
    }


}
