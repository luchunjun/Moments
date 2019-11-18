package com.lcj.commonlib.utils.http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;


public class NetUtil {
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            if (mConnectivityManager != null) {
                NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
                if (mNetworkInfo != null) {
                    return mNetworkInfo.isAvailable();
                }
            }
        }
        return false;
    }

    public static void openNetworkSetting(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        activity.startActivity(intent);
    }


}
