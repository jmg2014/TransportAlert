package app.jorge.mobile.com.transportalert;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import app.jorge.mobile.com.transportalert.model.BakerlooCardSelection;
import app.jorge.mobile.com.transportalert.model.CardSelection;
import app.jorge.mobile.com.transportalert.model.CentralCardSelection;
import app.jorge.mobile.com.transportalert.model.CircleCardSelection;
import app.jorge.mobile.com.transportalert.model.DistrictCardSelection;
import app.jorge.mobile.com.transportalert.model.HammersmithCardSelection;
import app.jorge.mobile.com.transportalert.model.JubileeCardSelection;
import app.jorge.mobile.com.transportalert.model.MetropolitanCardSelection;
import app.jorge.mobile.com.transportalert.model.NorthernCardSelection;
import app.jorge.mobile.com.transportalert.model.PiccadillyCardSelection;
import app.jorge.mobile.com.transportalert.model.VictoriaCardSelection;
import app.jorge.mobile.com.transportalert.model.WaterlooCardSelection;

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



        //Bakerloo
        CardView cardBakerloo =  (CardView) this.findViewById(R.id.card_Bakerloo);
        CardSelection bakerlooSelection=new BakerlooCardSelection();
        boolean isBakerlooChecked=sharedPreferences.getBoolean(getString(bakerlooSelection.getIdKeyProperty()), false);
        drawCardSelection(isBakerlooChecked, cardBakerloo, bakerlooSelection);
        addBakerlooListener(cardBakerloo);

        //Central
        CardView cardCentral =  (CardView) this.findViewById(R.id.card_Central);
        CardSelection cs=new CentralCardSelection();
        boolean isChecked=sharedPreferences.getBoolean(getString(cs.getIdKeyProperty()), false);
        drawCardSelection(isChecked,cardCentral,cs);
        addCentralListener(cardCentral);

        //Circle
        CardView cardCircle =  (CardView) this.findViewById(R.id.card_Circle);
        CardSelection circleSelection=new CircleCardSelection();
        boolean isCircleChecked=sharedPreferences.getBoolean(getString(circleSelection.getIdKeyProperty()), false);
        drawCardSelection(isCircleChecked,cardCircle,circleSelection);
        addCircleListener(cardCircle);

        //District
        CardView cardDistrict =  (CardView) this.findViewById(R.id.card_District);
        CardSelection districtSelection=new DistrictCardSelection();
        boolean isDistrictChecked=sharedPreferences.getBoolean(getString(districtSelection.getIdKeyProperty()), false);
        drawCardSelection(isDistrictChecked,cardDistrict,districtSelection);
        addDistrictListener(cardDistrict);

        //Hammersmith
        CardView cardHammersmith =  (CardView) this.findViewById(R.id.card_Hammersmith);
        CardSelection hammersmithSelection=new HammersmithCardSelection();
        boolean isHammersmithChecked=sharedPreferences.getBoolean(getString(hammersmithSelection.getIdKeyProperty()), false);
        drawCardSelection(isHammersmithChecked,cardHammersmith,hammersmithSelection);
        addHammersmithListener(cardHammersmith);

        //Jubilee
        CardView cardJubilee =  (CardView) this.findViewById(R.id.card_Jubilee);
        CardSelection jubileeSelection=new JubileeCardSelection();
        boolean isJubileeChecked=sharedPreferences.getBoolean(getString(jubileeSelection.getIdKeyProperty()), false);
        drawCardSelection(isJubileeChecked,cardJubilee,jubileeSelection);
        addJubileeListener(cardJubilee);

        //Metropolitan
        CardView cardMetropolitan =  (CardView) this.findViewById(R.id.card_Metropolitan);
        CardSelection metropolitanSelection=new MetropolitanCardSelection();
        boolean isMetropolitanChecked=sharedPreferences.getBoolean(getString(metropolitanSelection.getIdKeyProperty()), false);
        drawCardSelection(isMetropolitanChecked,cardMetropolitan,metropolitanSelection);
        addMetropolitanListener(cardMetropolitan);

        //Northern
        CardView cardNorthern =  (CardView) this.findViewById(R.id.card_Northern);
        CardSelection northernSelection=new NorthernCardSelection();
        boolean isNorthernChecked=sharedPreferences.getBoolean(getString(northernSelection.getIdKeyProperty()), false);
        drawCardSelection(isNorthernChecked,cardNorthern,northernSelection);
        addNorthernListener(cardNorthern);

        //Piccadilly
        CardView cardPiccadilly =  (CardView) this.findViewById(R.id.card_Piccadilly);
        CardSelection piccadillySelection=new PiccadillyCardSelection();
        boolean isPiccadillyChecked=sharedPreferences.getBoolean(getString(piccadillySelection.getIdKeyProperty()), false);
        drawCardSelection(isPiccadillyChecked,cardPiccadilly,piccadillySelection);
        addPiccadillyListener(cardPiccadilly);

        //Victoria
        CardView cardVictoria =  (CardView) this.findViewById(R.id.card_Victoria);
        CardSelection victoriaSelection=new VictoriaCardSelection();
        boolean isVictoriaChecked=sharedPreferences.getBoolean(getString(victoriaSelection.getIdKeyProperty()), false);
        drawCardSelection(isVictoriaChecked,cardVictoria,victoriaSelection);
        addVictoriaListener(cardVictoria);

        //Waterloo
        CardView cardWaterloo =  (CardView) this.findViewById(R.id.card_Waterloo);
        CardSelection waterlooSelection=new WaterlooCardSelection();
        boolean isWaterlooChecked=sharedPreferences.getBoolean(getString(waterlooSelection.getIdKeyProperty()), false);
        drawCardSelection(isWaterlooChecked, cardWaterloo, waterlooSelection);
        addWaterlooListener(cardWaterloo);
    }



    private void addCentralListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.central_label), false);

                drawCardSelection(!isChecked, (CardView) v, new CentralCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.central_label);

            }
        });
    }

    private void addDistrictListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.district_label), false);

                drawCardSelection(!isChecked, (CardView) v, new DistrictCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.district_label);

            }
        });
    }
    private void addBakerlooListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.bakerloo_label), false);

                drawCardSelection(!isChecked,(CardView) v,new BakerlooCardSelection());

                updateChecked(sharedPreferences, isChecked,R.string.bakerloo_label);

            }
        });
    }
    private void addHammersmithListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.hammersmith_label), false);

                drawCardSelection(!isChecked, (CardView) v, new HammersmithCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.hammersmith_label);

            }
        });
    }
    private void addJubileeListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.jubilee_label), false);

                drawCardSelection(!isChecked, (CardView) v, new JubileeCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.jubilee_label);

            }
        });
    }
    private void addMetropolitanListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.metropolitan_label), false);

                drawCardSelection(!isChecked, (CardView) v, new MetropolitanCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.metropolitan_label);

            }
        });
    }
    private void addNorthernListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.northern_label), false);

                drawCardSelection(!isChecked, (CardView) v, new NorthernCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.northern_label);

            }
        });
    }
    private void addPiccadillyListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.piccadilly_label), false);

                drawCardSelection(!isChecked, (CardView) v, new PiccadillyCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.piccadilly_label);

            }
        });
    }
    private void addCircleListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.circle_label), false);

                drawCardSelection(!isChecked, (CardView) v, new CircleCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.circle_label);

            }
        });
    }

    private void addVictoriaListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.victoria_label), false);

                drawCardSelection(!isChecked, (CardView) v, new VictoriaCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.victoria_label);

            }
        });
    }
    private void addWaterlooListener(CardView card) {
        card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                boolean isChecked = sharedPreferences.getBoolean(getString(R.string.waterloo_label), false);

                drawCardSelection(!isChecked, (CardView) v, new WaterlooCardSelection());

                updateChecked(sharedPreferences, isChecked, R.string.waterloo_label);

            }
        });
    }
    private void updateChecked(SharedPreferences sharedPreferences, boolean isChecked,int key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(key), !isChecked);
        editor.commit();
    }


    private void drawCardSelection(Boolean isChecked,CardView cardView,CardSelection cs) {

        TextView lineName=(TextView)cardView.findViewById(cs.getIdTxtLabel());
        if (isChecked) {
            cardView.setCardBackgroundColor(Color.parseColor(getString(cs.getCheckedColourCard())));
            lineName.setTextColor(Color.parseColor(getString(cs.getCkechedCoulourText())));
        }
        else{
            cardView.setCardBackgroundColor(Color.parseColor(getString(cs.getUnCheckedColourCard())));
            lineName.setTextColor(Color.parseColor(getString(cs.getUnCkechedCoulourText())));
        }

    }


}
