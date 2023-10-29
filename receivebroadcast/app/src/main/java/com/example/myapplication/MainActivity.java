package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Notification;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;




public class MainActivity extends AppCompatActivity {
TextView TXT;
     static String X;int i=0 ;
    static PendingIntent pendingIntent;
   // Notification.Builder builder;
   // Notification builder;
    //Notification builder;
    NotificationCompat.Builder builder;
    NotificationManagerCompat notificationManager;
    private static final String CHANNEL_ID = "my_channel_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TXT= findViewById(R.id.txt);

      //  IntentFilter intentFilter=new IntentFilter("android.intent.action.BATTERY_LOW");
        IntentFilter intentFilter=new IntentFilter( "com.example.service,MYservice");
   MyReceiver myReceiver=new MyReceiver();
   registerReceiver(myReceiver,intentFilter);
   //handler
   AcceptThread t =new AcceptThread(handler);
   t.start();
        Intent intent = new Intent(getApplicationContext(), MainActivity .class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
       pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        Log.d("TAG", "onMessageRecei:"+X);
        Toast.makeText(getApplicationContext(),"Settign is"+X,Toast.LENGTH_SHORT).show();
        //, channelId
       // NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          //  builder = new Builder(getApplicationContext())
            builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID)
                     .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
                     .setContentTitle("My Notification")
                     .setContentText("This is a notification"+X)
                     .setContentIntent(pendingIntent)
                     .build();
        }
        //.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(channel);
        }*/
       //NOTIFICATION_ID
    //    notificationManager.notify(1, builder.build() );*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the notification channel
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Channel name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Channel description");

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        // Create the notification builder
        builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
                .setContentTitle("My notification"+ i)
                .setContentText("Hello World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

// Show the notification
    notificationManager = NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(1, builder.build());
        i++;

// Later, update the notification text
      //  builder.setContentText("New notification text");
     //   notificationManager.notify(1, builder.build());


    }
    public static void createNotification(Context context, String title, String message) {
        Notification builder = new Notification.Builder(context)
                .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .build();
        //.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(channel);
        }*/
        //NOTIFICATION_ID
         notificationManager.notify(1, builder );
    }

    Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            switch (msg.what) {
                case 1:
                    X = msg.getData().getString("data");
                    TXT.setText("1: ".concat(X));
                    break;
                case 2:
                    X = msg.getData().getString("data");
                    TXT.setText("2: ".concat(X));
                    break;
                default:
                    return false;

            }i ++;
            builder.setContentText("msg"+ X);
            notificationManager.notify(1, builder.build());
            // createNotification(getApplication(), "My Title"+i, "My Message "+ X);
            return true;
        }
    };

    Handler handler = new Handler(Looper.getMainLooper(), hc);
 //Log.d("TAG", "onMessageRecei:"+X);

     /*{
        @Override
        public void handleMessage(Message msg) {
            //msg.what
            String X = msg.getData().getString("data");
            TXT.setText(X);
        }
    };*/

//    @Override
//    public boolean handleMessage(@NonNull Message msg) {
//        String X;
//
//        switch (msg.what) {
//            case 1:
//                X = msg.getData().getString("data");
//                TXT.setText("1: ".concat(X));
//                break;
//            case 2:
//                X = msg.getData().getString("data");
//                TXT.setText("2: ".concat(X));
//                break;
//            default:
//                return false;
//        }
//
//        return true;
//    }
}