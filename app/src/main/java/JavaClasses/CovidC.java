package JavaClasses;

public class CovidC {
    String infection,vacc, type, dosesNumber;

    public CovidC(){}

    public CovidC( String Infection_date, String Vaccine_Type, String Doses_Number,String VaccineDate) {
        this.infection=Infection_date;
        this.vacc= VaccineDate;
        this.type = Vaccine_Type;
        this.dosesNumber = Doses_Number;
    }

    public String getInfection() {

        return infection;
    }

    public String getVacc() {

        return vacc;
    }


    public String getType() {

        return type;
    }

    public String getDosesNumber() {

        return dosesNumber;
    }
}
