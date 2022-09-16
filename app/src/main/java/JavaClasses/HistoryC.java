package JavaClasses;

public class HistoryC {

String Operation,Body,OpDate,Anesthesia,OpFacility,DocName;

public HistoryC(){

}


    public HistoryC(String operation, String body, String opDate, String anesthesia, String opFacility, String docName) {
        Operation = operation;
        Body = body;
        OpDate = opDate;
        Anesthesia = anesthesia;
        OpFacility = opFacility;
        DocName = docName;
    }


    public String getOperation() {
        return Operation;
    }

    public void setOperation(String operation) {
        Operation = operation;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public String getOpDate() {
        return OpDate;
    }

    public void setOpDate(String opDate) {
        OpDate = opDate;
    }

    public String getAnesthesia() {
        return Anesthesia;
    }

    public void setAnesthesia(String anesthesia) {
        Anesthesia = anesthesia;
    }

    public String getOpFacility() {
        return OpFacility;
    }

    public void setOpFacility(String opFacility) {
        OpFacility = opFacility;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }
}
