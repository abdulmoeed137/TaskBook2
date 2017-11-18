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

public class ProfileFragment extends Fragment
{
    View rootView;

    Context c;

    TextView button_checked, button_unchecked;
    LinearLayout linear_checked, linear_unchecked;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        initialize();
        setUpComponents();
        return rootView;
    }

    private void setUpComponents() {

   autofragment();

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

        c= getActivity();
        button_checked= (TextView)rootView.findViewById(R.id.button_checked);

        button_unchecked = (TextView)rootView.findViewById(R.id.button_unchecked);

        linear_checked = (LinearLayout)rootView.findViewById(R.id.linear_checked);

        linear_unchecked = (LinearLayout)rootView.findViewById(R.id.linear_unchecked);
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

void autofragment()
{

    loadFragment(new UncheckedFragment());

    linear_unchecked.setBackground(getResources().getDrawable(R.drawable.rounded_bg_yellow_right_rounded));
    button_unchecked.setTextColor(getResources().getColor(R.color.white));

    linear_checked.setBackground(getResources().getDrawable(R.drawable.rounded_bg_white_left_rounded));
    button_checked.setTextColor(getResources().getColor(R.color.mainColor));

}


}
