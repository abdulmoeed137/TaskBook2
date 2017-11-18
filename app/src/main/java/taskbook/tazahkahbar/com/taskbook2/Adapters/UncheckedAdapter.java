package taskbook.tazahkahbar.com.taskbook2.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import taskbook.tazahkahbar.com.taskbook2.Holder.Peopleyoumayknowholder;
import taskbook.tazahkahbar.com.taskbook2.Holder.PostHolder;
import taskbook.tazahkahbar.com.taskbook2.Model.PeopleyoumayknowModel;
import taskbook.tazahkahbar.com.taskbook2.Model.PostModel;
import taskbook.tazahkahbar.com.taskbook2.R;
import taskbook.tazahkahbar.com.taskbook2.SessionManager.SessionManager;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 10/31/2017.
 */

public class UncheckedAdapter extends BaseAdapter {
    Animation animFadein;
    Context context;
    LayoutInflater inflater;
    ArrayList<PostModel> list = new ArrayList<>();
    DatabaseReference ref ;
    public UncheckedAdapter(Context c, ArrayList<PostModel> list)
    {
        ref = FirebaseDatabase.getInstance().getReference();
        context = c;
        inflater = LayoutInflater.from(c);
        this.list = list;
    }
    @Override
    public int getCount() {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        final PostHolder holder;
        final PostModel item = (PostModel) getItem(position);

        if (convertView == null) {
            holder = new PostHolder();
            convertView = inflater.inflate(R.layout.uncomplete_post_item, null, false);

            holder.username= (TextView)convertView.findViewById(R.id.username);
            holder.post= (TextView)convertView.findViewById(R.id.post);
            holder.like = (TextView) convertView.findViewById(R.id.like_text);
            holder.comment = (TextView) convertView.findViewById(R.id.comment_text);
            holder.like_icon = (ImageView)convertView.findViewById(R.id.like_icon);
            convertView.setTag(holder);

        }else
            holder=(PostHolder) convertView.getTag();
        holder.like_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.like_ico));
        holder.username.setText(item.getUsername().trim().toString());
        holder.post.setText(item.getPost().toString());
        holder.like.setText(item.getLike());
        holder.comment.setText(item.getComment());

        ref.child("post_like").child(item.getPost_id()).child(new SessionManager(context).getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                if (dataSnapshot.exists())
                {
                    holder.like_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.like_yes));
                    item.setIsLike(true);
                }
                else item.setIsLike(false);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        holder.like_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if (!item.getIsLike()){
                ref.child("post_like").child(item.getPost_id()).child(new SessionManager(context).getId()).setValue("like", new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null ){
                            //kkkk
                            animFadein = AnimationUtils.loadAnimation(context.getApplicationContext(),
                                    R.anim.fade_in);

                            holder.like_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.like_yes));
                        item.setIsLike(true);}
                    }
                });
            }
            else
            {
                ref.child("post_like").child(item.getPost_id()).child(new SessionManager(context).getId()).removeValue( new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null ){
                            //kkkk
                            ((Activity)context).overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                            holder.like_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.like_ico));
                        item.setIsLike(false);}
                    }
                });
            }
            }
        });

        return convertView;
    }
}
