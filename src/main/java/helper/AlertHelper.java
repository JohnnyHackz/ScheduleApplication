package helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * A utility class providing helper methods to display various alerts to the user.
 * <p>
 * This class contains methods to show error and information alerts,
 * print exception stack traces, and display localized login error messages.
 * </p>
 */
public class AlertHelper {

    /**
     * Displays an error alert with the specified title and message.
     *
     * @param title   The title of the error alert.
     * @param message The content message of the error alert.
     */
        public static void showError(String title, String message) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

    /**
     * Displays an informational alert with the specified message.
     *
     * @param message The content message of the informational alert.
     */
        public static void showInfo(String message) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle(message);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

    /**
     * Displays an error alert with the specified error message.
     * Additionally, the exception's stack trace is printed to the console.
     *
     * @param errorMessage The content message of the error alert.
     * @param e            The exception associated with the error.
     */
        public static void displayError(String errorMessage, Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(errorMessage);
            alert.showAndWait();

            // Print the stack trace to the console
            e.printStackTrace();
        }

    /**
     * Displays a localized login error message to the user.
     * <p>
     * This method attempts to retrieve the appropriate localized error message
     * based on the system's default locale. If the resource bundle is missing,
     * a message is printed to the console.
     * </p>
     */
    public static void showLocalizedLoginError() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("/language/loginPropPage", Locale.getDefault());

            String errorMessage = rb.getString("LoginFailed") + ". " +
                    rb.getString("Invalid") + " " +
                    rb.getString("username") + " " +
                    rb.getString("or") + " " +
                    rb.getString("password") + ". " +
                    rb.getString("Please") + " " +
                    rb.getString("try") + " " +
                    rb.getString("again");

            AlertHelper.Alert(rb.getString("Login"), errorMessage);

        } catch (MissingResourceException e) {
            System.out.println("Resource bundle file is missing: " + e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Utility method for showing a confirmation alert dialog and capturing the user's decision.
     * <p>
     * This method displays a confirmation dialog box with the given title and content message.
     * It then waits for the user to either confirm or cancel the action. If the user confirms,
     * the method returns {@code true}. Otherwise, it returns {@code false}.
     * </p>
     *
     * @param titleSet    The title of the alert dialog.
     * @param contentSet  The content message to be displayed in the dialog.
     * @return true if the user confirms the action by clicking the OK button;
     *         false otherwise.
     */
    public static boolean Alert(String titleSet, String contentSet) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titleSet);
        alert.setHeaderText("Confirm");
        alert.setContentText(contentSet);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }
}
