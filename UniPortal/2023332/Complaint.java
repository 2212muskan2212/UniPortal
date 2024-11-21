// import java.util.*;

class Complaint {
    private String description;
    private String status; 
    private String resolutionDetails;

    public Complaint(String description) {
        this.description = description;
        this.status = "Pending";
        this.resolutionDetails = "";
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResolutionDetails() {
        return resolutionDetails;
    }

    public void setResolutionDetails(String resolutionDetails) {
        this.resolutionDetails = resolutionDetails;
    }

    public void displayComplaint() {
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        System.out.println("Resolution Details: " + resolutionDetails);
    }

    @Override
    public String toString() {
        return "Complaint: " + description + " [Status: " + status + "]";
    }
}
