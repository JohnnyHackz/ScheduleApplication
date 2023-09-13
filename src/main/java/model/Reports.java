package model;

public class Reports {
    private String date;
    private String type;
    private int total;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString(){
        return ("Reports: " + date + " " + type + " " + Integer.toString(total));
    }

    public Reports (String date, String type, int total){
        this.date = date;
        this.type = type;
        this.total = total;
    }


}
