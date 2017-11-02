package taskbook.tazahkahbar.com.taskbook2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import taskbook.tazahkahbar.com.taskbook2.R;

/**
 * Created by lenovo on 10/31/2017.
 */

public class CheckedAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    public CheckedAdapter(Context c)
    {
        context = c;
        inflater = LayoutInflater.from(c);
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.completed_post_item,null,false);
        return v;
    }
}
