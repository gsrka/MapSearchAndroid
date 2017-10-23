package com.example.tanut.mapsearch.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.tanut.mapsearch.R;

/**
 * Created by tanut on 10/18/2017.
 */

public final class Utils {

    private static final String TAG = "UTILS";
    public static final String LOGTAG1 = "lOGGINGSUCESS";
    public static final String LOGTAG2 = "lOGGINGFAILURE";
    public static final String KEY = "AIzaSyAM0iywwIic7W4LcRS7609Rh6vK9gBaCoA";
  //  AIzaSyAQklkI8p-pzRSmdyY7PSR5OGnsrZLcSQM

    public static final String BASE_URL = "https://maps.googleapis.com/maps/";
    public static final String QUERY = "BBVA COMPASS";
    public static final String RADIUS = "100000";

    public static ProgressDialog showLoadingDialog(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
