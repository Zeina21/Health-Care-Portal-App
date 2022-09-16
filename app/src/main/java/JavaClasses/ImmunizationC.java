package JavaClasses;

public class ImmunizationC {
   String name,date,facility,docName;


   public ImmunizationC(){

   }

    public ImmunizationC(String name, String date, String facility, String docName) {
        this.name = name;
        this.date = date;
        this.facility = facility;
        this.docName = docName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}
