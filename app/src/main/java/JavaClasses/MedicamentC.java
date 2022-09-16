package JavaClasses;

public class MedicamentC {
    String medName;
    String dose;
    String frequency,docName;

    public MedicamentC() {
    }

    public MedicamentC(String medName, String dose, String frequency, String docName) {
        this.medName = medName;
        this.dose = dose;
        this.frequency = frequency;
        this.docName = docName;
    }


    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}