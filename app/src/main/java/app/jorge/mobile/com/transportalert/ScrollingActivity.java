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
package app.jorge.mobile.com.transportalert;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.okhttp.ResponseBody;
import java.util.HashMap;
import java.util.List;

import app.jorge.mobile.com.transportalert.factory.CardFactory;
import app.jorge.mobile.com.transportalert.factory.CardTube;
import app.jorge.mobile.com.transportalert.service.LineStatuses;
import app.jorge.mobile.com.transportalert.service.StatusLine;
import app.jorge.mobile.com.transportalert.service.TaskService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ScrollingActivity extends AppCompatActivity
        implements Callback<List<StatusLine>>,View.OnClickListener,SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = ScrollingActivity.class.getSimpleName();
    private FloatingActionButton fab;
    HashMap<String,LineStatuses> tubeStatus=new HashMap<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(this);

        LinearLayout item = (LinearLayout) findViewById(R.id.rv);
         addAllCards(item);


        // asynchronous
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.tfl.gov.uk/Line/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        TaskService taskService = retrofit.create(TaskService.class);


        Call<List<StatusLine>> call = taskService.login(getString(R.string.app_id),getString(R.string.app_key));


        call.enqueue(this);


        //view.setVisibility(View.GONE);
        item.setNestedScrollingEnabled(true);



        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    private void addAllCards(LinearLayout item) {


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isChecked = sharedPreferences.getBoolean(getString(R.string.bakerloo_label), false);

        //Bakerloo
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.BAKERLOO);
        }
        //Central
        isChecked = sharedPreferences.getBoolean(getString(R.string.central_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.CENTRAL);
        }

        //Circle
        isChecked = sharedPreferences.getBoolean(getString(R.string.circle_label), false);
        if (isChecked) {
           addCard(item, CardFactory.TUBE_LINE.CIRCLE);
        }
        //District
        isChecked = sharedPreferences.getBoolean(getString(R.string.district_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.DISTRICT);
        }

        //DLR
        // addCard(item,CardFactory.TUBE_LINE.DLR);

        //HC
        isChecked = sharedPreferences.getBoolean(getString(R.string.hammersmith_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.HC);
        }
        //Jubilee
        isChecked = sharedPreferences.getBoolean(getString(R.string.jubilee_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.JUBILEE);
        }
        //Metropolitan
        isChecked = sharedPreferences.getBoolean(getString(R.string.metropolitan_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.METROPOLITAN);
        }
        //Nothern
        isChecked = sharedPreferences.getBoolean(getString(R.string.northern_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.NORTHERN);
        }
        //Overground
        // addCard(item,CardFactory.TUBE_LINE.OVERGROUND);

        //Piccadilly
        isChecked = sharedPreferences.getBoolean(getString(R.string.piccadilly_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.PICCADILLY);
        }
        //Victoria
        isChecked = sharedPreferences.getBoolean(getString(R.string.victoria_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.VICTORIA);
        }
        //Waterloo & City
        isChecked = sharedPreferences.getBoolean(getString(R.string.waterloo_label), false);
        if (isChecked) {
            addCard(item, CardFactory.TUBE_LINE.WATERLOO);
        }

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(ScrollingActivity.this, SelectionActivity.class);
        startActivity(intent);

    }

    public Context getContext() {
        return this;
    }

    private void addCard(LinearLayout item,CardFactory.TUBE_LINE line) {

        CardTube card= CardFactory.getCard(line);
        View child = getLayoutInflater().inflate(R.layout.tube_line, null);

        ImageView imageView= (ImageView) child.findViewById(R.id.iconTube);
        imageView.setImageResource(card.getIcon());


        TextView lineName=(TextView)child.findViewById(R.id.tubeName);
        lineName.setText(card.getName());
        lineName.setTextColor(Color.parseColor(card.getColour()));

        TextView text=(TextView)child.findViewById(R.id.tubeStatus);
        text.setText(card.getStatus());

        item.addView(child);

        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View imageView = v.findViewById(R.id.iconTube);
                imageView.setTransitionName(getString(R.string.activity_image_trans));

                View textTubeNameView = v.findViewById(R.id.tubeName);
                textTubeNameView.setTransitionName(getString(R.string.activity_text_tube_name));

                View textStatusView = v.findViewById(R.id.tubeStatus);
                textStatusView.setTransitionName(getString(R.string.activity_text_tube_status));


                Intent intent = new Intent(ScrollingActivity.this, DetailActivity.class);
                Pair<View, String> pair1 = Pair.create(imageView, imageView.getTransitionName());
                Pair<View, String> pair2 = Pair.create(textTubeNameView, textTubeNameView.getTransitionName());
                Pair<View, String> pair3 = Pair.create(textStatusView, textStatusView.getTransitionName());


                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(ScrollingActivity.this, pair1, pair2, pair3);


                String line = ((TextView) textTubeNameView).getText().toString();
                String status = ((TextView) textStatusView).getText().toString();

                LineStatuses ls = tubeStatus.get(line);
                if ((ls != null) && (ls.getDisruption()!=null)) {
                    intent.putExtra(getString(R.string.activity_info_category), ls.getDisruption().getCategory());
                    intent.putExtra(getString(R.string.activity_info_description), ls.getDisruption().getDescription());
                    intent.putExtra(getString(R.string.activity_info_additional), ls.getDisruption().getAdditionalInfo());
                    intent.putExtra(getString(R.string.activity_info_icon), line);
                    intent.putExtra(getString(R.string.activity_info_status), status);
                    
                    
                }
                  else{
                    intent.putExtra(getString(R.string.activity_info_category), "Good");
                    intent.putExtra(getString(R.string.activity_info_description), "Good");
                    intent.putExtra(getString(R.string.activity_info_additional), "Good");
                    intent.putExtra(getString(R.string.activity_info_icon), line);
                    intent.putExtra(getString(R.string.activity_info_status), status);
                }
                startActivity(intent, options.toBundle());
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResponse(Response<List<StatusLine>> response, Retrofit retrofit) {
        if (response.isSuccess()) {

            tubeStatus=new HashMap<>();

            int size=response.body().size();
            for (int i=0;i<size;i++){

                StatusLine statusLine=response.body().get(i);

                String nameKey=statusLine.getName();
                LineStatuses lineStatuses=statusLine.getLineStatuses().get(0);

                //String status =response.body().get(i).getLineStatuses().get(0).getStatusSeverityDescription();
                tubeStatus.put(nameKey,lineStatuses);
            }
            //String name=response.body().get(0).getName();
            //String status =response.body().get(0).getLineStatuses().get(0).getStatusSeverityDescription();



           // Toast.makeText( getContext(), "You Clicked hey "+ status, Toast.LENGTH_SHORT).show();

            LinearLayout item = (LinearLayout)findViewById(R.id.rv);
            int childcount = item.getChildCount();
            for (int i=0; i < childcount; i++){
                View child = item.getChildAt(i);

                TextView lineName=(TextView)child.findViewById(R.id.tubeName);
                String line_id= lineName.getText().toString();

                String message=tubeStatus.get(line_id).getStatusSeverityDescription();
                if (message!=null) {
                    TextView text = (TextView) child.findViewById(R.id.tubeStatus);
                    text.setText(message);
                }
            }




        } else {
            int statusCode = response.code();

            // handle request errors
            ResponseBody errorBody = response.errorBody();
            Toast.makeText( getContext(), "Error code "+errorBody.toString()+" "+statusCode , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        Toast.makeText( getContext(), t.getMessage() , Toast.LENGTH_SHORT).show();

        // handle execution failures like no internet connectivity
        LinearLayout item = (LinearLayout)findViewById(R.id.rv);
        Snackbar snackbar = Snackbar
                .make(item, "No internet connection!", Snackbar.LENGTH_INDEFINITE)
                .setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        startActivity(getIntent());
                    }
                });

        // Changing message text color
        snackbar.setActionTextColor(Color.RED);

        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


    @Override
    public void onRefresh() {

        mSwipeRefreshLayout.setRefreshing(false);

        LinearLayout item = (LinearLayout) findViewById(R.id.rv);

        if(item.getChildCount() > 0) {
            item.removeAllViews();
        }

        addAllCards(item);
        // asynchronous
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.tfl.gov.uk/Line/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        TaskService taskService = retrofit.create(TaskService.class);

        Call<List<StatusLine>> call = taskService.login(getString(R.string.app_id),getString(R.string.app_key));

        call.enqueue(this);
    }
}
