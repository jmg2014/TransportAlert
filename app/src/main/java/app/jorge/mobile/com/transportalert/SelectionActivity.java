package app.jorge.mobile.com.transportalert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SharedPerferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent = new Intent(SelectionActivity.this, ScrollingActivity.class);
                startActivity(intent);
            }
        });


        ///
        CardView cardBakerloo =  (CardView) this.findViewById(R.id.card_Bakerloo);
        drawBakerlooCard(sharedPreferences, cardBakerloo);
        addListener(cardBakerloo);

        ////Central  line
        CardView cardCentral =  (CardView) this.findViewById(R.id.card_Central);
        drawCard(sharedPreferences,
                cardCentral,
                getString(R.string.central_label),
                getString(R.string.central_colour),
                getString(R.string.white),
                getString(R.string.white),
                getString(R.string.black));

        addCentralListener(cardCentral);


    }

    private void addListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.bakerloo_label), false);
                CardView card = (CardView) v;
                if (!isChecked) {

                    card.setCardBackgroundColor(Color.parseColor(getString(R.string.bakerloo_colour)));

                    TextView lineName = (TextView) card.findViewById(R.id.txtBakerloo);
                    lineName.setTextColor(Color.parseColor(getString(R.string.white)));


                } else {
                    card.setCardBackgroundColor(Color.parseColor(getString(R.string.white)));
                    TextView lineName = (TextView) card.findViewById(R.id.txtBakerloo);
                    lineName.setTextColor(Color.parseColor(getString(R.string.black)));
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.bakerloo_label), !isChecked);
                editor.commit();

            }
        });
    }

    private void addCentralListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.central_label), false);
                CardView card = (CardView) v;
                TextView lineName = (TextView) card.findViewById(R.id.txtCentral);

                if (!isChecked) {

                    card.setCardBackgroundColor(Color.parseColor(getString(R.string.central_colour)));
                    lineName.setTextColor(Color.parseColor(getString(R.string.white)));

                } else {
                    card.setCardBackgroundColor(Color.parseColor(getString(R.string.white)));
                    lineName.setTextColor(Color.parseColor(getString(R.string.black)));
                }
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.central_label), !isChecked);
                editor.commit();

            }
        });
    }
    @NonNull
    private void drawBakerlooCard(SharedPreferences sharedPreferences,CardView card ) {
        boolean isChecked=sharedPreferences.getBoolean(getString(R.string.bakerloo_label), false);

        if (isChecked) {

            card.setCardBackgroundColor(Color.parseColor(getString(R.string.bakerloo_colour)));

            TextView lineName=(TextView)card.findViewById(R.id.txtBakerloo);
            lineName.setTextColor(Color.parseColor(getString(R.string.white)));


        }
        else{
            card.setCardBackgroundColor(Color.parseColor(getString(R.string.white)));
            TextView lineName=(TextView)card.findViewById(R.id.txtBakerloo);
            lineName.setTextColor(Color.parseColor(getString(R.string.black)));
        }

    }
    private void drawCentralCard(SharedPreferences sharedPreferences,CardView card ) {
        boolean isChecked=sharedPreferences.getBoolean(getString(R.string.central_label), false);

        TextView lineName=(TextView)card.findViewById(R.id.txtCentral);
        if (isChecked) {
            card.setCardBackgroundColor(Color.parseColor(getString(R.string.central_colour)));
            lineName.setTextColor(Color.parseColor(getString(R.string.white)));
        }
        else{
            card.setCardBackgroundColor(Color.parseColor(getString(R.string.white)));
            lineName.setTextColor(Color.parseColor(getString(R.string.black)));
        }

    }

    private void drawCard(SharedPreferences sharedPreferences,
                                 CardView card,
                                 String propertyKey,
                                 String checkColourBackgroud,
                                 String checkClolourText,
                                 String unCheckColourBackgroud,
                                 String unCheckColourText) {

        boolean isChecked=sharedPreferences.getBoolean(propertyKey, false);

        TextView lineName=(TextView)card.findViewById(R.id.txtCentral);
        if (isChecked) {
            card.setCardBackgroundColor(Color.parseColor(checkColourBackgroud));
            lineName.setTextColor(Color.parseColor(checkClolourText));
        }
        else{
            card.setCardBackgroundColor(Color.parseColor(unCheckColourBackgroud));
            lineName.setTextColor(Color.parseColor(unCheckColourText));
        }

    }


}
