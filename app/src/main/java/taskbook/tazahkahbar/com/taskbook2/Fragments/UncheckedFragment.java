package taskbook.tazahkahbar.com.taskbook2.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import taskbook.tazahkahbar.com.taskbook2.Adapters.CheckedAdapter;
import taskbook.tazahkahbar.com.taskbook2.Adapters.UncheckedAdapter;
import taskbook.tazahkahbar.com.taskbook2.R;

/**
 * Created by lenovo on 11/1/2017.
 */

public class UncheckedFragment extends Fragment
{
    View rootView;
    ListView listview;
    Context c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.unchecked_fragment, container, false);
        initialize();
        setUpComponents();
        return rootView;
    }

    private void setUpComponents() {
        listview.setAdapter(new UncheckedAdapter(c));
    }

    private void initialize() {
        listview = (ListView)rootView.findViewById(R.id.mListView);
        c= getActivity();
    }
}
