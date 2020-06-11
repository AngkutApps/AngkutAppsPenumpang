package id.co.myproject.angkutapps_penumpang.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

import id.co.myproject.angkutapps_penumpang.R;

public class progress_bar_custom {

    Activity activity;
    AlertDialog alertDialog;

    public progress_bar_custom(Activity activity) {
        this.activity = activity;
    }

    public void startLoadingDialog(){
        Log.i("Tracking6", "sss");
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_bar_custom, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();

    }

    public void dismissDialog(){
        alertDialog.dismiss();
    }
}
