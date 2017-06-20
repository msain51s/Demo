package com.goldshop.utility;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.goldshop.LoginActivity;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;

/**
 * Created by bhanwar on 16/06/2017.
 */

public class Utils {

    public static SimpleArcDialog mDialog;
    public static AlertDialog alert11;
    public static void showSnakeBar(View layout_view, String msg, int color)
    {
        Snackbar snackbar = Snackbar.make(layout_view, msg, Snackbar.LENGTH_LONG);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);

        snackbar.show();
    }


    public static boolean ChechInternetAvalebleOrNot(Activity act) {

        ConnectivityManager connectivityManager = (ConnectivityManager) act
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void showLoader(Activity ctx)
    {
        mDialog = new SimpleArcDialog(ctx);
        mDialog.setConfiguration(new ArcConfiguration(ctx));
        mDialog.setCancelable(false);
        mDialog.show();
    }

    public static void dissmissLoader()
    {
        mDialog.dismiss();
    }

}
