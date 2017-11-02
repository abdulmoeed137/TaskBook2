package taskbook.tazahkahbar.com.taskbook2.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import taskbook.tazahkahbar.com.taskbook2.Adapters.DashboardAdapter;
import taskbook.tazahkahbar.com.taskbook2.R;

public class DashboardActivity extends AppCompatActivity {
    ListView listview;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initialize();
        setUpComponents();
    }

    private void setUpComponents() {
        listview.setAdapter(new DashboardAdapter(c));
        findViewById(R.id.shaded_circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(c,ProfileActivity.class));
            }
        });
    }

    private void initialize() {
        listview = (ListView)this.findViewById(R.id.mListView);
        c= DashboardActivity.this;
    }
}
