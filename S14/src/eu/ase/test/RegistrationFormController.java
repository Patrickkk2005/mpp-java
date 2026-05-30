package eu.ase.test;

import eu.ase.iojson.User;
import eu.ase.sqlDAO.SqlDao;
import eu.ase.sqlDAO.UserSubscriberReactStream;
import eu.ase.test.AlertHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.SQLException;
import java.util.concurrent.SubmissionPublisher;

public class RegistrationFormController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Button submitMThButton;
    @FXML
    private Button submitReactStreamsButton;
    @FXML
    private Button displayButton;

    private static SqlDao sqlDAO;
    private static int objectRegisteredUsersCount = 0;

    public RegistrationFormController() throws SQLException, ClassNotFoundException {
        sqlDAO = SqlDao.getInstance();
    }

    private boolean doValidationGUI(Window owner) {
        if(nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your name");
            return false;
        }
        if(emailField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your email");
            return false;
        }
        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your password");
            return false;
        }
        return true;
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws SQLException, InterruptedException {
        Window owner = submitButton.getScene().getWindow();
        if(!doValidationGUI(owner)) {
            return;
        }
        objectRegisteredUsersCount++;
        System.out.println("Registered User " + nameField.getText());
        sqlDAO.InsertIntoDBTable(objectRegisteredUsersCount, nameField.getText(), emailField.getText(), passwordField.getText());
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration successful - standard!", "Welcome " + nameField.getText());
    }

    @FXML
    protected void handleSubmitMThButtonAction(ActionEvent event) {
        Window window = submitButton.getScene().getWindow();
        doValidationGUI(window);
        objectRegisteredUsersCount++;
        System.out.println("Registered User " + nameField.getText());
        Runnable rth = () -> {
            try {
                sqlDAO.InsertIntoDBTable(objectRegisteredUsersCount, nameField.getText(), emailField.getText(), passwordField.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread th = new Thread(rth);
        th.start();
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, window, "Registration successful - multithreading!", "Welcome " + nameField.getText());
    }

    @FXML
    protected void handleSubmitReactStreamsButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Window window = submitButton.getScene().getWindow();
        doValidationGUI(window);
        objectRegisteredUsersCount++;
        System.out.println("Registered User " + nameField.getText());

        try(SubmissionPublisher<User> userSubmissionPublisher = new SubmissionPublisher<>()) {
            User user = new User(objectRegisteredUsersCount, nameField.getText(), emailField.getText(), passwordField.getText());
            UserSubscriberReactStream usersSubscriberReactStream = new UserSubscriberReactStream();
            userSubmissionPublisher.subscribe(usersSubscriberReactStream);
            userSubmissionPublisher.submit(user);
        }
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, window, "Registration successful - react streams!", "Welcome " + nameField.getText());
    }

    @FXML
    protected void handleDisplayButtonAction(ActionEvent event) throws SQLException {
        sqlDAO.DisplayDB();
    }

}