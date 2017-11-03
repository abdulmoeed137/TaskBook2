package taskbook.tazahkahbar.com.taskbook2.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import taskbook.tazahkahbar.com.taskbook2.Adapters.HomeAdapter;
import taskbook.tazahkahbar.com.taskbook2.Fragments.CheckedFragment;
import taskbook.tazahkahbar.com.taskbook2.Fragments.HomeFragment;
import taskbook.tazahkahbar.com.taskbook2.Fragments.NewPostFragment;
import taskbook.tazahkahbar.com.taskbook2.Fragments.ProfileFragment;
import taskbook.tazahkahbar.com.taskbook2.R;

public class DashboardActivity extends AppCompatActivity {

    Context c;
    LinearLayout footer_tick, footer_bee,footer_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
        setUpComponents();
    }

    private void setUpComponents() {
        loadFragment(new HomeFragment());

        footer_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new HomeFragment());
            }
        });

        footer_bee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ProfileFragment());
            }
        });

        footer_tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new NewPostFragment());
            }
        });
    }

    private void initialize() {

        c= DashboardActivity.this;

        footer_bee = (LinearLayout)this.findViewById(R.id.footer_bee_button);
        footer_home = (LinearLayout)this.findViewById(R.id.footer_home_button);
        footer_tick= (LinearLayout)this.findViewById(R.id.footer_tick_button);
    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
