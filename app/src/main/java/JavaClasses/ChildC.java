package JavaClasses;

public class ChildC {

    private String Name, age, Gender, ID, RefNum, Num;



    public ChildC() {

    }

    public ChildC(String Name) {
        Name = Name;
    }



    public ChildC(String Name, String age, String Gender, String ID, String RefNum, String Num) {
        this.Name = Name;
        this.age = age;
        this.Gender = Gender;
        this.ID = ID;
        this.RefNum = RefNum;
        this.Num = Num;
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRefNum() {
        return RefNum;
    }

    public void setRefNum(String RefNum) {
        this.RefNum = RefNum;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String Num) {
        this.Num = Num;
    }
}
