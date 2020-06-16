package id.co.myproject.angkutapps_penumpang.helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import id.co.myproject.angkutapps_penumpang.R;

public class NotificationHelper extends ContextWrapper {
    public static final String RAIHAN_CHANNEL_ID = "id.co.myproject.ubercoyrider.RAIHAN";
    public static final String RAIHAN_CHANNEL_NAME = "Raihan uber";

    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChennale();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChennale() {
        NotificationChannel raihanChannel = new NotificationChannel(RAIHAN_CHANNEL_ID, RAIHAN_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT);
        raihanChannel.enableLights(true);
        raihanChannel.enableVibration(true);
        raihanChannel.setLightColor(Color.GRAY);
        raihanChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(raihanChannel);
    }

    public NotificationManager getManager() {
        if (manager == null){
            manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getNotification(String title, String content, PendingIntent contentIntent, Uri soudUri){
        return new Notification.Builder(getApplicationContext(), RAIHAN_CHANNEL_ID)
                .setContentText(content)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(soudUri)
                .setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ic_car);
    }
}
