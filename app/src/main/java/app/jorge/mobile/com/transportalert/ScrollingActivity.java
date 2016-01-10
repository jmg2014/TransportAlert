package app.jorge.mobile.com.transportalert;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import app.jorge.mobile.com.transportalert.factory.CardFactory;
import app.jorge.mobile.com.transportalert.factory.CardTube;

public class ScrollingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        LinearLayout item = (LinearLayout)findViewById(R.id.rv);


        //Bakerloo
        addCard(item, CardFactory.TUBE_LINE.BAKERLOO);

        //Central
        addCard(item,CardFactory.TUBE_LINE.CENTRAL);

        //Circle
        addCard(item,CardFactory.TUBE_LINE.CIRCLE);

        //District
        addCard(item,CardFactory.TUBE_LINE.DISTRICT);

        //District
        addCard(item,CardFactory.TUBE_LINE.DLR);

        //HC
        addCard(item,CardFactory.TUBE_LINE.HC);

        //Jubilee
        addCard(item,CardFactory.TUBE_LINE.JUBILEE);

        //Nothern
        addCard(item,CardFactory.TUBE_LINE.NOTHERN);

        //Overground
        addCard(item,CardFactory.TUBE_LINE.OVERGROUND);

        //Picadilly
        addCard(item,CardFactory.TUBE_LINE.PICADILLY);

        //Victoria
        addCard(item,CardFactory.TUBE_LINE.VICTORIA);

        //Waterloo & City
        addCard(item, CardFactory.TUBE_LINE.WATERLOO);




        //view.setVisibility(View.GONE);


    }
    private void savePreferences(String key, boolean value) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(key, value);

        editor.commit();

    }
/*
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    boolean checkBoxValue = sharedPreferences.getBoolean("CheckBox_Value", false);
*/


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
}
