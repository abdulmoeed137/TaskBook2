package taskbook.tazahkahbar.com.taskbook2.Utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;


import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


/**
 * Created by lenovo on 8/27/2017.
 */

public class utils {
    static public String URLwithParams (String url , HashMap<String,String> hashMap)
    {

        StringBuilder sb = new StringBuilder();
        for(HashMap.Entry<String, String> e : hashMap.entrySet()){
            if(sb.length() > 0){
                sb.append('&');
            }
            else
                sb.append('?');
            try {
                sb.append(URLEncoder.encode(e.getKey(), "UTF-8")).append('=').append(URLEncoder.encode(e.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        }
        String s = url+(sb.toString());
        return s;
    }

//    static  public void LoadImageFromURL(Context mContext, String mImageUrlString, ImageView mImageView){
//        mImageUrlString = Constants.IMAGE_FOLDER+mImageUrlString;
//        Glide.with(mContext)
//                //.load(mImageUri) // Load image from assets
//                .load(mImageUrlString) // Image URL
//                .centerCrop() // Image scale type
//                .crossFade()
//                .override(100,100) // Resize image
////                .placeholder(R.drawable.ic_photo_black_36dp) // Place holder image
////                .error(R.drawable.ic_error_black_36dp) // On error image
//                .into(mImageView); // ImageView to display image
//    }
//    static  public Double SumwithPercent (String s)
//    {
//
//        Double percent = (Double)((Double.parseDouble(s.toString())*10)/100);
//        percent = percent+ Double.parseDouble(s);
//        return percent;
//    }
//    static  public Double double2decimal (Double d)
//    {
//        return Double.parseDouble(String.format("%.2f", d));
//    }

    static public String id, name, contact,email;

    static public String scan_flow="";

    public static void hideSoftKeyboard(Activity activity) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(
                            Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
        catch ( Exception e)
        {

        }
    }

//    public static Dialog TransactionDialog(final Context context) {
//
//        final boolean[] ret = {false};
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        dialog.setContentView(R.layout.transaction_password_dialog);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(((Activity) context).getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.gravity = Gravity.BOTTOM;
//        lp.dimAmount = 0.3f;
//        dialog.show();
//        return dialog;
//    }
//    public static Dialog ImageDialog(final Context context, Bitmap bmp) {
//
//        final boolean[] ret = {false};
//        final Dialog dialog = new Dialog(context);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        dialog.setContentView(R.layout.image_dialog);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(((Activity) context).getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.gravity = Gravity.BOTTOM;
//        lp.dimAmount = 0.3f;
//        dialog.show();
//        ImageView img = (ImageView)dialog.findViewById(R.id.qr);
//        img.setImageBitmap(bmp);
//        return dialog;
//    }
    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }

        public static void ProgressStart(ProgressBar progressBar, Context context)
        {
            progressBar.setVisibility(View.VISIBLE);
            try {
                ((Activity)context).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
            catch ( Exception e ) {}
        }
    public static void ProgressEnd(ProgressBar progressBar, Context context)
    {
        progressBar.setVisibility(View.GONE);
        try {
            ((Activity)context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
        catch ( Exception e ) {}
    }

}