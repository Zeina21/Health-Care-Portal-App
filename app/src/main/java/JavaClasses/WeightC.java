package JavaClasses;

public class WeightC {

    String weight,height,BMI,status,wdate;


    public WeightC(){

    }

    public WeightC(String weight, String height, String BMI, String status, String wdate) {
        this.weight = weight;
        this.height = height;
        this.BMI = BMI;
        this.status = status;
        this.wdate = wdate;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWdate() {
        return wdate;
    }

    public void setWdate(String wdate) {
        this.wdate = wdate;
    }
}






