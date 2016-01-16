package app.jorge.mobile.com.transportalert.service;

import java.util.List;

/**
 * Created by koke on 10/01/2016.
 */
public class StatusLine {

    private String id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<LineStatuses> lineStatuses;

    public List<LineStatuses> getLineStatuses() {
        return lineStatuses;
    }

    public void setLineStatuses(List<LineStatuses> lineStatuses) {
        this.lineStatuses = lineStatuses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
