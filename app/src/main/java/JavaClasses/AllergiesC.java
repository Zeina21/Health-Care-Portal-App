package JavaClasses;

public class AllergiesC {
    String atype,AName,adate,DocName;

    public AllergiesC() {
    }


    public AllergiesC(String atype, String AName, String adate, String docName) {
        this.atype = atype;
        this.AName = AName;
        this.adate = adate;
        DocName = docName;
    }


    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getAName() {
        return AName;
    }

    public void setAName(String AName) {
        this.AName = AName;
    }

    public String getAdate() {
        return adate;
    }

    public void setAdate(String adate) {
        this.adate = adate;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }
}



