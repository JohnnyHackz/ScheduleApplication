package model;

public class DivisionData {
    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    private String divisionName;
    private int totalCustomers;

    public DivisionData(String divisionName, int totalCustomers) {
        this.divisionName = divisionName;
        this.totalCustomers = totalCustomers;
    }
}

