package app.jorge.mobile.com.transportalert.service;

/**
 * Created by koke on 11/01/2016.
 */
public class LineStatuses {

    private String statusSeverity;

    private String statusSeverityDescription;

    public String getStatusSeverityDescription() {
        return statusSeverityDescription;
    }

    public void setStatusSeverityDescription(String statusSeverityDescription) {
        this.statusSeverityDescription = statusSeverityDescription;
    }

    public String getStatusSeverity() {
        return statusSeverity;
    }

    public void setStatusSeverity(String statusSeverity) {
        this.statusSeverity = statusSeverity;
    }
}
