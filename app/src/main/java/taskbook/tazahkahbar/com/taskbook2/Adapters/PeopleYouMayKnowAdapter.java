package taskbook.tazahkahbar.com.taskbook2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import taskbook.tazahkahbar.com.taskbook2.Activities.ProfileActivityFromPYMN;
import taskbook.tazahkahbar.com.taskbook2.Holder.Peopleyoumayknowholder;
import taskbook.tazahkahbar.com.taskbook2.Model.PeopleyoumayknowModel;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;
import taskbook.tazahkahbar.com.taskbook2.Toast.Toast;

/**
 * Created by lenovo on 10/31/2017.
 */

public class PeopleYouMayKnowAdapter extends BaseAdapter {
    Context context;
    ArrayList<PeopleyoumayknowModel> list = new ArrayList<>();
    DatabaseReference ref ;
    LayoutInflater   inflater ;
    public PeopleYouMayKnowAdapter(Context c,    ArrayList<PeopleyoumayknowModel> list)
    {
        context = c;
    inflater = LayoutInflater.from(context);
        ref = FirebaseDatabase.getInstance().getReference();
        this.list = list;
    }

    @Override
    public int getCount() {
        android.widget.Toast.makeText(context, list.size()+"", android.widget.Toast.LENGTH_SHORT).show();
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Peopleyoumayknowholder holder;
        final PeopleyoumayknowModel item = (PeopleyoumayknowModel)getItem(position);

        if (convertView == null)
        {
            holder = new Peopleyoumayknowholder();

            convertView = inflater.inflate(R.layout.profile_item_peopleyoumayknow,null,false);

            holder.name = (TextView)convertView.findViewById(R.id.username);
            holder.button_follow = (TextView)convertView.findViewById(R.id.button_follow);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.progressBar= (ProgressBar)convertView.findViewById(R.id.pbar);
            convertView.setTag(holder);

        }else
              holder=(Peopleyoumayknowholder) convertView.getTag();

        holder.name.setText(item.getUsername());

        final View finalConvertView = convertView;

        holder.button_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.button_follow.setVisibility(View.GONE);
                holder.progressBar.setVisibility(View.VISIBLE);

                ref.child("following").child(new SessionManager(context).getId()).child(item.getId()).
                        setValue("followed", new DatabaseReference.CompletionListener(){
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        holder.progressBar.setVisibility(View.GONE);
                        if (databaseError == null )
                        {

                            holder.button_follow.setBackgroundColor(finalConvertView.getResources().getColor(R.color.white));
                            holder.button_follow.setTextColor(finalConvertView.getResources().getColor(R.color.mainColor));
                            holder.button_follow.setText("Following");
                            holder.button_follow.setVisibility(View.VISIBLE);
                        }
                        else{
                            Toast.makeCustomToast(context,"Error while follow request to his user");
                            holder.button_follow.setVisibility(View.VISIBLE);}
                    }
                });



            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeCustomToast(context, "User Profile ");

                Intent intent = new Intent(context,ProfileActivityFromPYMN.class);
                intent.putExtra("id", item.getId());
                context.startActivity(intent);


            }
        });

        return convertView;
    }
}
