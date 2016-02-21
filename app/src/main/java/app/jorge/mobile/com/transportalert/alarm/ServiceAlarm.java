/*
 * Copyright 2016 Jorge Manrique
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.jorge.mobile.com.transportalert.alarm;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;


import java.util.List;

import app.jorge.mobile.com.transportalert.R;
import app.jorge.mobile.com.transportalert.service.LineStatuses;
import app.jorge.mobile.com.transportalert.service.StatusLine;
import app.jorge.mobile.com.transportalert.service.TaskService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class ServiceAlarm extends IntentService implements Callback<List<StatusLine>> {

    private static String TAG=ServiceAlarm.class.getSimpleName();

    NotificationManager notificationManager;

    public static final int NOTIFICATION_BAKERLOO = 1;
    public static final int NOTIFICATION_CENTRAL = 2;
    public static final int NOTIFICATION_CIRCLE = 3;
    public static final int NOTIFICATION_DISTRICT = 4;
    public static final int NOTIFICATION_HAMMERSMITH = 5;
    public static final int NOTIFICATION_JUBILEE = 6;
    public static final int NOTIFICATION_METROPOLITAN = 7;
    public static final int NOTIFICATION_NORTHERN = 8;
    public static final int NOTIFICATION_PICCADILLY = 9;
    public static final int NOTIFICATION_VICTORIA = 10;
    public static final int NOTIFICATION_WATERLOO = 11;
    public static final int NOTIFICATION_OVERGROUND = 12;
    public static final int NOTIFICATION_TFLRAIL = 13;
    public static final int NOTIFICATION_DLR = 14;

    public ServiceAlarm() {
        super("ServiceAlarm");

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Do the task here
        Log.i(TAG, "Service running");
        notificationManager=(NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_tfl_api))
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        TaskService taskService = retrofit.create(TaskService.class);


        Call<List<StatusLine>> call = taskService.login(getString(R.string.app_id),getString(R.string.app_key));


        call.enqueue(this);
    }


    @Override
    public void onResponse(Response<List<StatusLine>> response, Retrofit retrofit) {
        if (response.isSuccess()) {



            int size = response.body().size();
            for (int i = 0; i < size; i++) {

                StatusLine statusLine = response.body().get(i);

                String nameKey = statusLine.getName();
                LineStatuses lineStatuses = statusLine.getLineStatuses().get(0);//iterate


                String message=lineStatuses.getStatusSeverityDescription();
                if (message!=null) {


                    if (!message.equals(getString(R.string.status_good_service))){
                        StringBuilder sb=new StringBuilder();
                        sb.append(message);
                        sb.append("\n");


                        if (nameKey.equals(getString(R.string.bakerloo_label))) {
                            createHeadsUpNotification(getString(R.string.bakerloo_label),sb,NOTIFICATION_BAKERLOO);
                        }
                        else if (nameKey.equals(getString(R.string.central_label))) {
                            createHeadsUpNotification(getString(R.string.central_label),sb,NOTIFICATION_CENTRAL);
                        }
                        else if (nameKey.equals(getString(R.string.circle_label))) {
                            createHeadsUpNotification(getString(R.string.circle_label),sb,NOTIFICATION_CIRCLE);
                        }
                        else if (nameKey.equals(getString(R.string.district_label))) {
                            createHeadsUpNotification(getString(R.string.district_label),sb,NOTIFICATION_DISTRICT);
                        }
                        else if (nameKey.equals(getString(R.string.hammersmith_label))) {
                            createHeadsUpNotification(getString(R.string.hammersmith_label),sb,NOTIFICATION_HAMMERSMITH);
                        }
                        else if (nameKey.equals(getString(R.string.jubilee_label))) {
                            createHeadsUpNotification(getString(R.string.jubilee_label),sb,NOTIFICATION_JUBILEE);
                        }
                        else if (nameKey.equals(getString(R.string.metropolitan_label))) {
                            createHeadsUpNotification(getString(R.string.metropolitan_label),sb,NOTIFICATION_METROPOLITAN);
                        }
                        else if (nameKey.equals(getString(R.string.northern_label))) {
                            createHeadsUpNotification(getString(R.string.northern_label),sb,NOTIFICATION_NORTHERN);
                        }
                        else if (nameKey.equals(getString(R.string.piccadilly_label))) {
                            createHeadsUpNotification(getString(R.string.piccadilly_label),sb,NOTIFICATION_PICCADILLY);
                        }
                        else if (nameKey.equals(getString(R.string.victoria_label))) {
                            createHeadsUpNotification(getString(R.string.victoria_label),sb,NOTIFICATION_VICTORIA);
                        }
                        else if (nameKey.equals(getString(R.string.waterloo_label))) {
                            createHeadsUpNotification(getString(R.string.waterloo_label),sb,NOTIFICATION_WATERLOO);
                        }
                        else if (nameKey.equals(getString(R.string.london_overground_label))) {
                            createHeadsUpNotification(getString(R.string.london_overground_label),sb,NOTIFICATION_OVERGROUND);
                        }
                        else if (nameKey.equals(getString(R.string.tfl_rail_label))) {
                            createHeadsUpNotification(getString(R.string.tfl_rail_label),sb,NOTIFICATION_TFLRAIL);
                        }
                        else{
                            createHeadsUpNotification(getString(R.string.dlr_label),sb,NOTIFICATION_DLR);
                        }
                    }


                }
            }


        }
    }

    private void createHeadsUpNotification(String label,StringBuilder text,int notificationID) {


        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification);

        remoteViews.setTextViewText(R.id.titleNotification,label);
        remoteViews.setTextViewText(R.id.messageNotification,text.toString());



        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.notification_256x256)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[]{1, 1, 1})
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContent(remoteViews);


        notificationManager.notify(notificationID, notificationBuilder.build());
    }
    @Override
    public void onFailure(Throwable t) {

    }
}