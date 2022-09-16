package JavaClasses;

public class User {
    private String name,id,gender,email,num,age,refNum,DID;
    public User(){

    }

    public User(String name, String id, String gender, String email, String num, String age, String refNum) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.email = email;
        this.num = num;
        this.age = age;
        this.refNum = refNum;
    }

    public User(String name){}


    public User(String name, String id, String gender, String num, String age, String refNum) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.num = num;
        this.age = age;
        this.refNum = refNum;
    }

    public User(String name, String DID, String gender, String email, String num){
        this.name=name;
        this.DID= DID;
        this.gender=gender;
        this.email=email;
        this.num=num;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefNum() {
        return refNum;
    }

    public void setRefNum(String refNum) {
        this.refNum = refNum;
    }

    public String getDID() {
        return DID;
    }

    public void setDID(String DID) {
        this.DID = DID;
    }
}
