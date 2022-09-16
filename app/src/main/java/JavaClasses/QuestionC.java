package JavaClasses;

public class QuestionC {
    String Blood,Marital, Smoking, Alcohol, Disability, Pain, Optical, Period,Pregnancy;
    public QuestionC(){

    }

    public QuestionC(String blood, String marital, String smoking, String alcohol, String disability, String pain, String optical, String period, String pregnancy) {
        Blood = blood;
        Marital = marital;
        Smoking = smoking;
        Alcohol = alcohol;
        Disability = disability;
        Pain = pain;
        Optical = optical;
        Period = period;
        Pregnancy = pregnancy;
    }

    public QuestionC(String blood, String marital, String smoking, String alcohol, String disability, String pain, String optical) {
        Blood = blood;
        Marital = marital;
        Smoking = smoking;
        Alcohol = alcohol;
        Disability = disability;
        Pain = pain;
        Optical = optical;

    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getMarital() {
        return Marital;
    }

    public void setMarital(String marital) {
        Marital = marital;
    }

    public String getSmoking() {
        return Smoking;
    }

    public void setSmoking(String smoking) {
        Smoking = smoking;
    }

    public String getAlcohol() {
        return Alcohol;
    }

    public void setAlcohol(String alcohol) {
        Alcohol = alcohol;
    }

    public String getDisability() {
        return Disability;
    }

    public void setDisability(String disability) {
        Disability = disability;
    }

    public String getPain() {
        return Pain;
    }

    public void setPain(String pain) {
        Pain = pain;
    }

    public String getOptical() {
        return Optical;
    }

    public void setOptical(String optical) {
        Optical = optical;
    }


    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public String getPregnancy() {
        return Pregnancy;
    }

    public void setPregnancy(String pregnancy) {
        Pregnancy = pregnancy;
    }
}
