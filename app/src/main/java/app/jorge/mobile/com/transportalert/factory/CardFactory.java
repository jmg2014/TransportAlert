package app.jorge.mobile.com.transportalert.factory;

import app.jorge.mobile.com.transportalert.R;

/**
 * Created by koke on 20/12/2015.
 */
public class CardFactory {

    public static enum TUBE_LINE{BAKERLOO,CENTRAL,CIRCLE,DISTRICT,DLR,HC,JUBILEE,METROPOLITAN,NORTHERN,OVERGROUND,PICCADILLY,VICTORIA,WATERLOO}


    public static CardTube getCard(TUBE_LINE line){


        if (line.equals(TUBE_LINE.BAKERLOO)) {

            return new CardTube("Bakerloo", R.drawable.bakerloo_400x400, "Checking status...", "#AE6118");

        }
        else if (line.equals(TUBE_LINE.CENTRAL)) {

            return new CardTube("Central", R.drawable.central_line_400x400, "Checking status...", "#E41F1F");

        }
        else if (line.equals(TUBE_LINE.CIRCLE)) {

            return new CardTube("Circle", R.drawable.circle_400x400, "Checking status...", "#F8D42D");

        }
        else if (line.equals(TUBE_LINE.DISTRICT)) {

            return new CardTube("District", R.drawable.district_400x400, "Checking status...", "#007229");

        }

        else if (line.equals(TUBE_LINE.DLR)) {

            return new CardTube("DLR", R.drawable.dlr_400x400, "Checking status...", "#00BBB4");

        }
        else if (line.equals(TUBE_LINE.HC)) {

            return new CardTube("Hammersmith & City", R.drawable.hc_400x400, "Checking status...", "#E899A8");

        }
        else if (line.equals(TUBE_LINE.JUBILEE)) {

            return new CardTube("Jubilee", R.drawable.jubilee_400x400, "Checking status...", "#686E72");

        }
        else if (line.equals(TUBE_LINE.METROPOLITAN)) {

            return new CardTube("Metropolitan", R.drawable.metropolitan_400x400, "Checking status...", "#893267");

        }
        else if (line.equals(TUBE_LINE.NORTHERN)) {

            return new CardTube("Northern", R.drawable.nothern_400x400, "Checking status...", "#000000");

        }
        else if (line.equals(TUBE_LINE.OVERGROUND)) {

            return new CardTube("London Overground", R.drawable.overground_400x400, "Checking status...", "#F86C00");

        }
        else if (line.equals(TUBE_LINE.PICCADILLY)) {

            return new CardTube("Piccadilly", R.drawable.picadilly_400x400, "Checking status...", "#0450A1");

        }
        else if (line.equals(TUBE_LINE.VICTORIA)) {

            return new CardTube("Victoria", R.drawable.victoria_400x400, "Checking status...", "#009FE0");

        }
        else if (line.equals(TUBE_LINE.WATERLOO)) {

            return new CardTube("Waterloo & City", R.drawable.waterloo_400x400, "Checking status...", "#70C3CE");

        }else{
            return new CardTube("UNKNOWN", R.drawable.waterloo_400x400, "Checking status...", "#70C3CE");
        }
    }
}
