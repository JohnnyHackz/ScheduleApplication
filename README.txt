Title: Appointment Management System || Software II - Advanced Java Concepts - C195

Purpose: Purpose of this application is to create a graphical user interface (GUI) for an appointment scheduling management system.

Author: Jakob Berentsen

Contact Information: Student ID - 001068769

Application Version: 1.0      Date: 10/1/23

IDE Version - IntelliJ Community 2023.2.1
JDk Version - Oracle OpenJDK Version 17.0.1
JavaFX Version - 17.0.6
MySQL Connector - mysql-connector-java-8.0.26

How to run the program: The program will start and a login screen will appear prompting the user to enter a username and password.The program will
check the database and verify that the username and password are correct. Once a correct username and password have been verified an alert will
indicate whether there is an upcoming appointment in 15 or less or no upcoming appointments at all. The user can then view, add, modify or delete
a user or appointment as well as view a variety of available report-- all navigable to from the mainAppointment Screen.

Additional Report Description: The reportsDivisionTotalController class showcases a table that breaks
down divisions and their related customer totals. This table is divided into two chief columns: "Division Name," highlighted by reportsDivisionInformationDivNameCol,
which displays each division's title, and "Total Customers," marked by reportsDivisionInformationTotalCustomersCol, that shows the customer count for each division.
This controller also integrates a back button for easy navigation to the main reports page. Initialization of this interface
involves pulling data from a database via JDBC to fill the table, and once the data is up and visible, the database connection is promptly shut down.


Lambda expression #1: The lambda expression in question listens for changes to the selected item of the reportsContactInformationContactsComboBox ComboBox. Specifically,
when the selected item in the ComboBox changes, the lambda expression is executed. Inside this expression, a check is made to ensure that the new selection (newValue) is
not null. If there is a valid new selection, the fillTable() method is called to update the table based on the contact that's been selected. Utilizing a lambda expression
in this context is both efficient and readable. It provides a succinct way to define the behavior we want (updating the table) in response to a specific event (a change in
the ComboBox's selection). Without the lambda, I would need to create a full anonymous inner class, which would be more verbose for such a straightforward operation.
The lambda expression offers a more concise and maintainable approach to handle this    behavior.

Lambda expression #2: In the addAppointmentController, I've incorporated lambda expressions to manage certain UI interactions, especially the synchronization between start
and end times/dates. Using a lambda expression in this context offers a concise way to represent event-handling logic. Instead of using more verbose structures, lambda expressions
give a compact and clear expression of intent. This makes the code easier to read and understand. Moreover, this lambda approach ensures that the end date and time are always logically
set in relation to the start values. By using this functional programming feature, I've not only made the code more readable but also provided a more user-friendly experience by preemptively
handling potential scheduling issues."





