package taskbook.tazahkahbar.com.taskbook2.Toast;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import taskbook.tazahkahbar.com.taskbook2.R;

/**
 * Created by lenovo on 9/13/2017.
 */

public class Toast {
    public static void makeCustomToast(final Context context, final String msg){
        final Activity activity = ((Activity)context) ;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater = activity.getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,null);

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText(msg);

                android.widget.Toast toast = new android.widget.Toast(context);
                toast.setGravity(Gravity.BOTTOM, 0, 100);
                toast.setDuration(android.widget.Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });

    }
    public static void makeCustomErrorToast(final Context context, final String msg){
        final Activity activity = ((Activity)context) ;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LayoutInflater inflater = activity.getLayoutInflater();
                View layout = inflater.inflate(R.layout.error_toast,null);

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText(msg);

                android.widget.Toast toast = new android.widget.Toast(context);
                toast.setGravity(Gravity.BOTTOM, 0, 100);
                toast.setDuration(android.widget.Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
            }
        });

    }
}
