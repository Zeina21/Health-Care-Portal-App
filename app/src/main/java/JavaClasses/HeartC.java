package JavaClasses;

public class HeartC {

    String Date,Time,BPM;

    public HeartC(){

    }

    public HeartC(String date, String time, String BPM) {
        Date = date;
        Time = time;
        this.BPM = BPM;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getBPM() {
        return BPM;
    }

    public void setBPM(String BPM) {
        this.BPM = BPM;
    }
}
