package Controller;

import Model.Model;
import Model.Objects.User;
import Model.Objects.Vacation;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //tabs
    public TabPane tabPane;
    public Tab signTab;
    public Tab homeTab;
    public Tab createTab;
    public Tab readTab;
    public Tab updateTab;
    public Tab vacationsTab;
    public Tab searchTab;
    public Tab publishTab;
    //Sign in tab
    public TextField usernameSign;
    public PasswordField passwordSign;
    public Button submit;
    //Home tab
    public Label usernameHome;
    public Label firstHome;
    public Label lastHome;
    public Label birthHome;
    public Label cityHome;
    public Label countryHome;
    public Button delete;
    public Button signOut;
    //Create tab
    public TextField usernameCreate;
    public TextField passwordCreate;
    public TextField confirmCreate;
    public TextField firstCreate;
    public TextField lastCreate;
    public DatePicker birthCreate;
    public TextField cityCreate;
    public TextField countryCreate;
    public Button create;
    //Read tab
    public TextField usernameRead;
    public Button show;
    public Label firstRead;
    public Label lastRead;
    public Label birthRead;
    public Label cityRead;
    public Label countryRead;
    //update tab
    public TextField usernameUpdate;
    public TextField passwordUpdate;
    public TextField confirmUpdate;
    public TextField firstUpdate;
    public TextField lastUpdate;
    public DatePicker birthUpdate;
    public TextField cityUpdate;
    public TextField countryUpdate;
    public Button update;

    //vacations tab
            //publish tab
    public TextField sourcePublish;
    public TextField destinationPublish;
    public TextField ticketsNumPublish;
    public TextField pricePublish;
    public TextField baggageLimitPublish;
    public TextField hospitalityRankPublish;
    public DatePicker fromDatePublish;
    public DatePicker toDatePublish;
    public ChoiceBox<Vacation.Tickets_Type> ticketsClassPublish;
    public CheckBox partTicketsPublish;
    public ChoiceBox<Vacation.Vacation_Type> vacationTypePublish;
    public ChoiceBox<Vacation.Flight_Type> flightTypePublish;
    public CheckBox hospitalityPublish;
    public CheckBox baggagePublish;

    final String directoryPath = "C:/DATABASE/";//////
    final String databaseName = "database.db";
    final String tableName = "Users_Table";
    Model model;
    String username="";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabSignOut();
        String pattern = "dd-MM-yyyy";
        changeDateFormat(birthCreate,pattern);
        changeDateFormat(birthUpdate,pattern);

        username="";
    }

    private void changeDateFormat(DatePicker dp, String pattern)
    {
        dp.setPromptText(pattern.toLowerCase());

        dp.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        dp.setPromptText(pattern);
    }

    public void setModel(Model model){
        this.model=model;
    }

    public void tabSignOut(){
        clearAll();
        username="";
        tabPane.getTabs().remove(0,tabPane.getTabs().size());
        tabPane.getTabs().add(signTab);
        tabPane.getTabs().add(createTab);
        createTab.setText("Sign up");
        create.setText("Sign up!");
        create.setOnAction(this::signUp);
        homeTab.setClosable(false);
        createTab.setClosable(false);
        signTab.setClosable(false);
        readTab.setClosable(false);
        updateTab.setClosable(false);
    }

    public void tabSignIn(){
        clearAll();
        tabPane.getTabs().remove(0,2);
        tabPane.getTabs().add(homeTab);
//        tabPane.getTabs().add(createTab);
        tabPane.getTabs().add(readTab);
        tabPane.getTabs().add(updateTab);
//        createTab.setText("Create");
//        create.setText("Create!");
//        create.setOnAction(this::create);
    }

    public void signUp(ActionEvent event){
        if(create(event)){
            signIn(event,usernameCreate.getText(),passwordCreate.getText());
            clearCreate();
            event.consume();
        }
    }

    private void clearAll() {
        clearSignIn();
        clearHome();
        clearCreate();
        clearRead();
        clearUpdate();
    }

    private void clearSignIn() {
        usernameSign.clear();
        passwordSign.clear();
    }

    private void clearHome() {
        usernameHome.setText("");
        firstHome.setText("");
        lastHome.setText("");
        birthHome.setText("");
        cityHome.setText("");
        countryHome.setText("");
    }

    private void clearCreate() {
        usernameCreate.clear();
        passwordCreate.clear();
        confirmCreate.clear();
        lastCreate.clear();
        firstCreate.clear();
        cityCreate.clear();
        birthCreate.setValue(null);
        countryCreate.clear();
    }

    private void clearRead() {
        usernameRead.clear();
        firstRead.setText("");
        lastRead.setText("");
        birthRead.setText("");
        cityRead.setText("");
        countryRead.setText("");
    }

    private void clearUpdate() {
        usernameUpdate.clear();
        passwordUpdate.clear();
        firstUpdate.clear();
        lastUpdate.clear();
        birthUpdate.setValue(null);
        cityUpdate.clear();
        countryUpdate.clear();
    }

    public boolean create(ActionEvent event) {
        if(createEmpty()==true){
            error("Please fill all the fields");
        }
        else if(passwordCreate.getText().equals(confirmCreate.getText())==false){
            error("Password must be match in both options");
        }
        else if(model.userExist(usernameCreate.getText())==true){
            error("Username already taken");
        }
        else if(dateCheck(birthCreate.getValue())==false){
            error("Age must be at least 18");
        }
        else if(confirm("Are you sure you want to Create an account with these details?")){
            //model.createUser(usernameCreate.getText(),passwordCreate.getText(),DatePicker2Str(birthCreate),firstCreate.getText(),lastCreate.getText(),cityCreate.getText());
            model.createUser(new User(usernameCreate.getText(),passwordCreate.getText(),birthCreate.getValue(),firstCreate.getText(),lastCreate.getText(),cityCreate.getText(),countryCreate.getText()));
            info("User creation was made successfully!");
            event.consume();
            return true;
        }
        event.consume();
        return false;
    }

    private boolean createEmpty() {
        if(usernameCreate.getText().isEmpty() || passwordCreate.getText().isEmpty() || firstCreate.getText().isEmpty() || lastCreate.getText().isEmpty() || birthCreate.getValue()==null || birthCreate.getValue().toString().isEmpty() || cityCreate.getText().isEmpty() || countryCreate.getText().isEmpty())
            return true;
        else
            return false;
    }
    private boolean updateEmpty() {
        if(usernameUpdate.getText().isEmpty() || passwordUpdate.getText().isEmpty() || firstUpdate.getText().isEmpty() || lastUpdate.getText().isEmpty() || birthUpdate.getValue()==null || birthUpdate.getValue().toString().isEmpty() || cityUpdate.getText().isEmpty() || countryUpdate.getText().isEmpty())
            return true;
        else
            return false;
    }

    public void submit(ActionEvent event){
        signIn(event,usernameSign.getText(),passwordSign.getText());
    }

    private void signIn(ActionEvent event, String username, String password) {
        if(model.userExist(username)==false){
            error("Username is incorrect");
            event.consume();
        }
        else if(model.UsersTable_checkPassword(username,password)==false){
            error("Username or Password are incorrect");
            event.consume();
        }
        else{
            this.username=username;
            tabSignIn();
            updateHome(username);
            fillUpdate(username);
        }
    }

    private void fillUpdate(String username) {
        User user=model.getUser(username);
        usernameUpdate.setText(user.getUsername());
        passwordUpdate.setText(user.getPassword());
        confirmUpdate.setText(user.getPassword());
        firstUpdate.setText(user.getFirst_Name());
        lastUpdate.setText(user.getLast_Name());
        birthUpdate.setValue(user.getBirth_Date());
        cityUpdate.setText(user.getCity());
        countryUpdate.setText(user.getCountry());
    }


    public void signOut(ActionEvent event){
        if(confirm("Are you sure you want to sign-out?"))
            tabSignOut();
        event.consume();
    }

    public void delete(ActionEvent event){
        if(confirm("Are you sure you want to delete your account?")){
            info("Your account was deleted successfully!");
            model.deleteUser(username);
            tabSignOut();
        }
        event.consume();
    }

    public void show(ActionEvent event){
        if(model.userExist(usernameRead.getText())==true){
            User user=model.getUser(username);
            firstRead.setText(user.getFirst_Name());
            lastRead.setText(user.getLast_Name());
            birthRead.setText(localDate2Str(user.getBirth_Date()));
            cityRead.setText(user.getCity());
            countryRead.setText(user.getCountry());
        }
        else {
            error("Username is incorrect");
            event.consume();
        }
    }

    private String localDate2Str(LocalDate birth_date) {
        String str;
        str=birth_date.getDayOfMonth()+"-"+birth_date.getMonthValue()+"-"+birth_date.getYear();
        return str;
    }

    public void update(ActionEvent event){
        if(updateEmpty()==true){
            info("Please fill all the fields");
            event.consume();
        }
        else if(model.userExist(usernameUpdate.getText())==true && usernameUpdate.getText().equals(this.username)==false){
            error("Username already taken");
            event.consume();
        }
        else if(passwordUpdate.getText().equals(confirmUpdate.getText())==false){
            error("Password must be match in both options");
            event.consume();
        }
        else if(dateCheck(birthUpdate.getValue())==false){
            error("Age must be at least 18");
            event.consume();
        }
        else if(confirm("Are you sure you want to update the details?")){
            model.updateUserInfo(username,new User(usernameUpdate.getText(),passwordUpdate.getText(),birthUpdate.getValue(),firstUpdate.getText(),lastUpdate.getText(),cityUpdate.getText(),countryUpdate.getText()));
            if(usernameRead.getText().equals(this.username)==true){
                clearRead();
            }
            username=usernameUpdate.getText();
            updateHome(username);
            info("The update was made successfully!");
        }
        event.consume();
    }

    public void baggagePublishClick(Event event){

    }

    public void hospitalityPublishClick(Event event){

    }

    public void partTicketsPublishClick(Event event){

    }

    public void publishPublish(Event event){

    }

    public void flightListPublish(Event event){

    }

    private void updateHome(String username) {
        User user=model.getUser(username);
        usernameHome.setText(username);
        firstHome.setText(user.getFirst_Name());
        lastHome.setText(user.getLast_Name());
        birthHome.setText(localDate2Str(user.getBirth_Date()));
        cityHome.setText(user.getCity());
        countryHome.setText(user.getCountry());
    }

    public boolean confirm(String massage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(massage);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public void error(String massage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(massage);
        Optional<ButtonType> result = alert.showAndWait();
    }

    public void info(String massage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(massage);
        Optional<ButtonType> result = alert.showAndWait();
    }

    private boolean dateCheck(LocalDate date){
        LocalDate today=LocalDate.now().plusDays(1);
        LocalDate before18=today.minusYears(18);
        return (date.isBefore(before18));
    }
}
