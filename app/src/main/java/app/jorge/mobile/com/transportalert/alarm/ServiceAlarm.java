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
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
        super("app.jorge.mobile.com.transportalert.alarm.ServiceAlarm");

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

                    String previous_status=getLastStatus(nameKey);

                    if (!previous_status.equals(message)){

                        saveCurrentStaus(nameKey,message);

                        Log.i(TAG, "Update status: " + nameKey + " , previous:"+previous_status + " current: "+ message);

                        if (!message.equals(getString(R.string.status_good_service))){
                            noGoodService(nameKey, message);
                        }

                    }
                    else{
                        Log.i(TAG, "Same status: " + nameKey + " , previous:"+previous_status + " current: "+ message);
                    }



                }
            }


        }
    }

    private void saveCurrentStaus(String tubeLine, String status) {

        //Update status
         SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         SharedPreferences.Editor editor = sharedPreferences.edit();

        String nameKey="";
        if (tubeLine.equals(getString(R.string.bakerloo_label))){
            nameKey=getString(R.string.bakerloo_status);
        }
        else if (tubeLine.equals(getString(R.string.central_label))){
            nameKey=getString(R.string.central_status);
        }
        else if (tubeLine.equals(getString(R.string.circle_label))){
            nameKey=getString(R.string.circle_status);
        }
        else if (tubeLine.equals(getString(R.string.district_label))){
            nameKey=getString(R.string.district_status);
        }
        else if (tubeLine.equals(getString(R.string.hammersmith_label))){
            nameKey=getString(R.string.hammersmith_status);
        }
        else if (tubeLine.equals(getString(R.string.jubilee_label))){
            nameKey=getString(R.string.jubilee_status);
        }
        else if (tubeLine.equals(getString(R.string.metropolitan_label))){
            nameKey=getString(R.string.metropolitan_status);
        }
        else if (tubeLine.equals(getString(R.string.northern_label))){
            nameKey=getString(R.string.northern_status);
        }
        else if (tubeLine.equals(getString(R.string.piccadilly_label))){
            nameKey=getString(R.string.piccadilly_status);
        }
        else if (tubeLine.equals(getString(R.string.victoria_label))){
            nameKey=getString(R.string.victoria_status);
        }
        else if (tubeLine.equals(getString(R.string.waterloo_label))){
            nameKey=getString(R.string.waterloo_status);
        }
        else if (tubeLine.equals(getString(R.string.london_overground_label))){
            nameKey=getString(R.string.london_overground_status);
        }
        else if (tubeLine.equals(getString(R.string.tfl_rail_label))){
            nameKey=getString(R.string.tfl_rail_status);
        }
        else if (tubeLine.equals(getString(R.string.dlr_label))){
            nameKey=getString(R.string.dlr_status);
        }


         editor.putString(nameKey, status);
         editor.commit();
    }


    private String getLastStatus(String lineKey){

        String last_status="";

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (lineKey.equals(getString(R.string.bakerloo_label))){
            last_status = sharedPreferences.getString(getString(R.string.bakerloo_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.central_label))){
            last_status = sharedPreferences.getString(getString(R.string.central_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.circle_label))){
            last_status = sharedPreferences.getString(getString(R.string.circle_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.district_label))){
            last_status = sharedPreferences.getString(getString(R.string.district_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.hammersmith_label))){
            last_status = sharedPreferences.getString(getString(R.string.hammersmith_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.jubilee_label))){
            last_status = sharedPreferences.getString(getString(R.string.jubilee_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.metropolitan_label))){
            last_status = sharedPreferences.getString(getString(R.string.metropolitan_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.northern_label))){
            last_status = sharedPreferences.getString(getString(R.string.northern_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.piccadilly_label))){
            last_status = sharedPreferences.getString(getString(R.string.piccadilly_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.victoria_label))){
            last_status = sharedPreferences.getString(getString(R.string.victoria_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.waterloo_label))){
            last_status = sharedPreferences.getString(getString(R.string.waterloo_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.london_overground_label))){
            last_status = sharedPreferences.getString(getString(R.string.london_overground_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.tfl_rail_label))){
            last_status = sharedPreferences.getString(getString(R.string.tfl_rail_status), getString(R.string.status_good_service));
        }
        else if (lineKey.equals(getString(R.string.dlr_label))){
            last_status = sharedPreferences.getString(getString(R.string.dlr_status), getString(R.string.status_good_service));
        }


        return last_status;

    }


    private boolean getSelected(String lineKey){

        boolean isSelected=false;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (lineKey.equals(getString(R.string.bakerloo_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.bakerloo_selected), false);
        }
        else if (lineKey.equals(getString(R.string.central_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.central_selected), false);
        }
        else if (lineKey.equals(getString(R.string.circle_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.circle_selected), false);
        }
        else if (lineKey.equals(getString(R.string.district_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.district_selected), false);
        }
        else if (lineKey.equals(getString(R.string.hammersmith_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.hammersmith_selected), false);
        }
        else if (lineKey.equals(getString(R.string.jubilee_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.jubilee_selected), false);
        }
        else if (lineKey.equals(getString(R.string.metropolitan_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.metropolitan_selected), false);
        }
        else if (lineKey.equals(getString(R.string.northern_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.northern_selected), false);
        }
        else if (lineKey.equals(getString(R.string.piccadilly_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.piccadilly_selected), false);
        }
        else if (lineKey.equals(getString(R.string.victoria_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.victoria_selected), false);
        }
        else if (lineKey.equals(getString(R.string.waterloo_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.waterloo_selected), false);
        }
        else if (lineKey.equals(getString(R.string.london_overground_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.london_overground_selected), false);
        }
        else if (lineKey.equals(getString(R.string.tfl_rail_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.tfl_rail_selected), false);
        }
        else if (lineKey.equals(getString(R.string.dlr_label))){
            isSelected = sharedPreferences.getBoolean(getString(R.string.dlr_selected), false);
        }

        return isSelected;

    }
    private void noGoodService(String nameKey, String message) {
        StringBuilder sb=new StringBuilder();
        sb.append(message);
        sb.append("\n");


        boolean isChecked = getSelected(nameKey);

        if(isChecked){

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
            Log.i(TAG,"Checked switchCompact: "+nameKey);
        }//isChecked
        else{
            Log.i(TAG,"No Checked switchCompact: "+nameKey);
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

    @Override
    public void onDestroy(){

    }
}