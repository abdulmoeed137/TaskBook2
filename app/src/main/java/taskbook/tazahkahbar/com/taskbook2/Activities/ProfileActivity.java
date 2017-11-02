package taskbook.tazahkahbar.com.taskbook2.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import taskbook.tazahkahbar.com.taskbook2.Fragments.CheckedFragment;
import taskbook.tazahkahbar.com.taskbook2.Fragments.UncheckedFragment;
import taskbook.tazahkahbar.com.taskbook2.R;

public class ProfileActivity extends AppCompatActivity {

    TextView button_checked, button_unchecked;
    LinearLayout linear_checked, linear_unchecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initialize();
        setUpComponents();
    }

    private void setUpComponents() {

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

        button_checked= (TextView)this.findViewById(R.id.button_checked);

        button_unchecked = (TextView)this.findViewById(R.id.button_unchecked);

        linear_checked = (LinearLayout)this.findViewById(R.id.linear_checked);

        linear_unchecked = (LinearLayout)this.findViewById(R.id.linear_unchecked);

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
