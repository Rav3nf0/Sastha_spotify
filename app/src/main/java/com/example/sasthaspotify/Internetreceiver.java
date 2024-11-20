package com.example.sasthaspotify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Internetreceiver extends BroadcastReceiver {
    private static boolean isLoginActivityRunning = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        String status = CheckInternet.getnetworkInfo(context);
        if (status.equals("connected") && isLoginActivityRunning) {
            Intent loginIntent = new Intent(context, Login.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(loginIntent);
            isLoginActivityRunning = false;
        }
        else if(status.equals("disconnected")) {
            isLoginActivityRunning = true;
            Intent noInternetIntent = new Intent(context, NoInternet.class);
            noInternetIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(noInternetIntent);
        }

    }
}
