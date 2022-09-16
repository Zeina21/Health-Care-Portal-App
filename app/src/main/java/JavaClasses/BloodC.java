package JavaClasses;

public class BloodC {

    String systolic, diastolic, bpdate;

    public BloodC(){

    }

    public BloodC(String systolic, String diastolic, String bpdate) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.bpdate = bpdate;



    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }


    public String getBPDate() {
        return bpdate;
    }

    public void setDate(String date) {
        this.bpdate = date;
    }


}
