package id.co.myproject.angkutapps_penumpang.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import id.co.myproject.angkutapps_penumpang.R;
import id.co.myproject.angkutapps_penumpang.helper.NotificationHelper;
import id.co.myproject.angkutapps_penumpang.helper.Utils;

public class MyFirebaseMessaging extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData() != null) {
            Map<String, String> data = remoteMessage.getData();
            String title = data.get("title");
            String message = data.get("message");
            if (title.equals("Cancel")) {
                LocalBroadcastManager.getInstance(MyFirebaseMessaging.this)
                        .sendBroadcast(new Intent(Utils.CANCEL_BROADCAST_STRING));
            } else if (title.equals("Arrived")) {
                LocalBroadcastManager.getInstance(MyFirebaseMessaging.this)
                        .sendBroadcast(new Intent(Utils.ARRIVED_BRADCAST));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                    showArrivedNotificationAPI26(message);
                else
                    showArrivedNotification(message);
            } else if (title.equals("Accept")) {
                String kodeDriver = data.get("kode_driver");
                String driverToken = data.get("driver_token");
                Intent intent = new Intent(Utils.ACCEPT_BROADCAST_STRING);
                intent.putExtra("kode_driver", kodeDriver);
                intent.putExtra("driver_token", driverToken);
                LocalBroadcastManager.getInstance(MyFirebaseMessaging.this)
                        .sendBroadcast(intent);
                Log.e("DITERIMA", "onMessageReceived: KODE DRIVER : " + kodeDriver);
            } else if (title.equals("Angkut")) {
                String driverToken = data.get("driver_token");
                String kodeDriver = data.get("kode_driver");
                String idList = data.get("id_list");
                Intent intent = new Intent(Utils.ANGKUT_BRADCAST);
                intent.putExtra("driver_token", driverToken);
                intent.putExtra("kode_driver", kodeDriver);
                intent.putExtra("id_list", idList);
                LocalBroadcastManager.getInstance(MyFirebaseMessaging.this)
                        .sendBroadcast(intent);

            } else if (title.equals("CancelAngkut")) {
                String driverToken = data.get("driver_token");
                Intent intent = new Intent(Utils.CANCEL_ANGKUT_BRADCAST);
                intent.putExtra("driver_token", driverToken);
                LocalBroadcastManager.getInstance(MyFirebaseMessaging.this)
                        .sendBroadcast(intent);
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showArrivedNotificationAPI26(String body) {
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),
                0, new Intent(), PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationHelper notificationHelper = new NotificationHelper(getBaseContext());
        Notification.Builder builder = notificationHelper.getNotification("Arrived", body, contentIntent, defaultSound);

        notificationHelper.getManager().notify(1, builder.build());
    }


    private void showArrivedNotification(String body) {
        PendingIntent contentIntent = PendingIntent.getActivity(getBaseContext(),
                0, new Intent(), PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_car)
                .setContentTitle("Arrived")
                .setContentText(body)
                .setContentIntent(contentIntent);
        NotificationManager manager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());

    }
}
