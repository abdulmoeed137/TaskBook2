package taskbook.tazahkahbar.com.taskbook2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import taskbook.tazahkahbar.com.taskbook2.Holder.PostHolder;
import taskbook.tazahkahbar.com.taskbook2.Model.PostModel;
import taskbook.tazahkahbar.com.taskbook2.R;

/**
 * Created by lenovo on 10/31/2017.
 */

public class CheckedAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<PostModel> list = new ArrayList<>();
    public CheckedAdapter(Context c, ArrayList<PostModel> list)
    {
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
            convertView = inflater.inflate(R.layout.completed_post_item, null, false);

            holder.username= (TextView)convertView.findViewById(R.id.username);
            holder.post= (TextView)convertView.findViewById(R.id.post);
            holder.like = (TextView) convertView.findViewById(R.id.like_text);
            holder.comment = (TextView) convertView.findViewById(R.id.comment_text);
            convertView.setTag(holder);

        }else
            holder=(PostHolder) convertView.getTag();

        holder.username.setText(item.getUsername().trim().toString());
        holder.post.setText(item.getPost().toString());
        holder.like.setText(item.getLike());
        holder.comment.setText(item.getComment());

        return convertView;
    }
}
