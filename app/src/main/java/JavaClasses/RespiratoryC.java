package JavaClasses;

public class RespiratoryC {
    String DateRR, TimeRR, BreathRR;

    public  RespiratoryC(){

    }

    public RespiratoryC(String dateRR, String timeRR, String breathRR) {
        DateRR = dateRR;
        TimeRR = timeRR;
        BreathRR = breathRR;
    }

    public String getDateRR() {
        return DateRR;
    }

    public void setDateRR(String dateRR) {
        DateRR = dateRR;
    }

    public String getTimeRR() {
        return TimeRR;
    }

    public void setTimeRR(String timeRR) {
        TimeRR = timeRR;
    }

    public String getBreathRR() {
        return BreathRR;
    }

    public void setBreathRR(String breathRR) {
        BreathRR = breathRR;
    }
}
