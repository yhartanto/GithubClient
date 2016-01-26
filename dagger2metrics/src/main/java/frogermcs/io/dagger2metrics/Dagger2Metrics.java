package frogermcs.io.dagger2metrics;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import frogermcs.io.dagger2metrics.internal.MetricsActivity;

/**
 * Created by Miroslaw Stanek on 25.01.2016.
 */
public class Dagger2Metrics {
    public static void enableCapturing(Application application) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(application)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Dagger2Metrics")
                .setContentText("Click to see current metrics")
                .setAutoCancel(false);

        Intent resultIntent = new Intent(application, MetricsActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(application, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify("Dagger2Metrics".hashCode(), mBuilder.build());
    }
}
