package model;

/**
 * Represents a report with attributes: date, type, and total.
 * This class encapsulates the details of a specific report, capturing
 * the date of the report, the type of the report, and the total associated with the report.
 */
public class Reports {
    private String date;
    private String type;
    private int total;

    /**
     * Gets the date of the report.
     *
     * @return The date of the report.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the report.
     *
     * @param date The date to set.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the type of the report.
     *
     * @return The type of the report.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the report.
     *
     * @param type The type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the total associated with the report.
     *
     * @return The total figure or number.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the total associated with the report.
     *
     * @param total The total figure or number to set.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Returns a string representation of the report.
     *
     * @return A string containing the date, type, and total of the report.
     */
    @Override
    public String toString() {
        return ("Reports: " + date + " " + type + " " + Integer.toString(total));
    }

    /**
     * Constructs a Reports object with the specified values.
     *
     * @param date  The date of the report.
     * @param type  The type of the report.
     * @param total The total figure or number associated with the report.
     */
    public Reports(String date, String type, int total) {
        this.date = date;
        this.type = type;
        this.total = total;
    }
}
