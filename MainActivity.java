package com.checking.testapp6;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button open = (Button) findViewById(R.id.open);
        open.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openWifiSettings();
            }
        });
    }

    public void openWifiSettings() {

        final Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings");
        intent.setComponent(cn);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


        if (mWifi.isConnected()) {
            // Do whatever
            showAlertDialog(MainActivity.this, "Internet Connection",
                    "You have internet connection", true);
        }
        else {

            showAlertDialog(MainActivity.this, "No Internet Connection",
                    "You don't have internet connection.", false);
        }
    }

    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }





}


