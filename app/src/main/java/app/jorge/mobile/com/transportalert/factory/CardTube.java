package app.jorge.mobile.com.transportalert.factory;

/**
 * Created by koke on 20/12/2015.
 */
public class CardTube {

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public String getStatus() {
        return status;
    }

    public String getColour() {
        return colour;
    }

    private String name;

    private int icon;

    private String status;

    private String colour;


    public CardTube(String name,int icon,String status,String colour){

        this.name=name;
        this.icon=icon;
        this.status=status;
        this.colour=colour;


    }


}
