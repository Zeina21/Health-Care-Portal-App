package JavaClasses;

public class PathC {

    String dname, sdate, treatment,DocName;


    public PathC() {

    }


    public PathC(String dname, String sdate, String treatment, String docName) {
        this.dname = dname;
        this.sdate = sdate;
        this.treatment = treatment;
        DocName = docName;
    }


    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public String getDName() {
        return dname;
    }

    public void setDName(String name) {
        dname = name;
    }

    public String getSDate() {
        return sdate;
    }

    public void setSDate(String date) {
        sdate = date;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

}
