package Controller;

import Model.Model;
import Model.Objects.Flight;
import Model.Objects.User;
import Model.Objects.Vacation;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ViewController implements Initializable,Observer {

    //tabs
    public TabPane tabPane;
    public TabPane vacationTabPane;
    public Tab signTab;
    public Tab homeTab;
    public Tab createTab;
    public Tab readTab;
    public Tab updateTab;
    public Tab vacationsTab;
    public Tab searchTab;
    public Tab publishTab;
    public Tab requestTab;
    public Tab personalTab;
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
    public Button flightListBut;

    private final String directoryPath = "C:/DATABASE/";//////
    private final String databaseName = "database.db";
    private final String tableName = "Users_Table";
    private Model model;
    private String username="";
    private boolean loggedIn=false;
    private ArrayList<Flight> flights;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabSignOut();
        String pattern = "dd-MM-yyyy";
        changeDateFormat(birthCreate,pattern);
        changeDateFormat(birthUpdate,pattern);
        username="";
        //publishTab
        baggageLimitPublish.setText("0");
        baggageLimitPublish.setDisable(true);
        hospitalityRankPublish.setText("0");
        hospitalityRankPublish.setDisable(true);
        vacationTypePublish.getItems().addAll(Vacation.Vacation_Type.values());
        flightTypePublish.getItems().addAll(Vacation.Flight_Type.values());
        ticketsClassPublish.getItems().addAll(Vacation.Tickets_Type.values());
        setTabsClosable(false);
    }

    private void setTabsClosable(boolean b) {
        signTab.setClosable(b);
        homeTab.setClosable(b);
        createTab.setClosable(b);
        readTab.setClosable(b);
        updateTab.setClosable(b);
        vacationsTab.setClosable(b);
        searchTab.setClosable(b);
        publishTab.setClosable(b);
        requestTab.setClosable(b);
        personalTab.setClosable(b);
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
        vacationTabPane.getTabs().removeAll(publishTab);
        tabPane.getTabs().add(vacationsTab);
        createTab.setText("Sign up");
        create.setText("Sign up!");
        create.setOnAction(this::signUp);
    }

    public void tabSignIn(){
        clearAll();
        tabPane.getTabs().remove(0,3);
        vacationTabPane.getTabs().add(publishTab);
        tabPane.getTabs().addAll(personalTab,vacationsTab,requestTab);
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
            Massage.errorMassage("Please fill all the fields");
        }
        else if(passwordCreate.getText().equals(confirmCreate.getText())==false){
            Massage.errorMassage("Password must be match in both options");
        }
        else if(model.userExist(usernameCreate.getText())==true){
            Massage.errorMassage("Username already taken");
        }
        else if(dateCheck(birthCreate.getValue())==false){
            Massage.errorMassage("Age must be at least 18");
        }
        else if(Massage.confirmMassage("Are you sure you want to Create an account with these details?")){
            //model.createUser(usernameCreate.getText(),passwordCreate.getText(),DatePicker2Str(birthCreate),firstCreate.getText(),lastCreate.getText(),cityCreate.getText());
            model.createUser(new User(usernameCreate.getText(),passwordCreate.getText(),birthCreate.getValue(),firstCreate.getText(),lastCreate.getText(),cityCreate.getText(),countryCreate.getText()));
            Massage.infoMassage("User creation was made successfully!");
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
            Massage.errorMassage("Username is incorrect");
            event.consume();
        }
        else if(model.UsersTable_checkPassword(username,password)==false){
            Massage.errorMassage("Username or Password are incorrect");
            event.consume();
        }
        else{
            this.username=username;
            tabSignIn();
            updateHome(username);
            fillUpdate(username);
            loggedIn=true;
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
        if(Massage.confirmMassage("Are you sure you want to sign-out?")){
            tabSignOut();
            loggedIn=false;
        }
        event.consume();
    }

    public void delete(ActionEvent event){
        if(Massage.confirmMassage("Are you sure you want to delete your account?")){
            Massage.infoMassage("Your account was deleted successfully!");
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
            Massage.errorMassage("Username is incorrect");
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
            Massage.infoMassage("Please fill all the fields");
            event.consume();
        }
        else if(model.userExist(usernameUpdate.getText())==true && usernameUpdate.getText().equals(this.username)==false){
            Massage.errorMassage("Username already taken");
            event.consume();
        }
        else if(passwordUpdate.getText().equals(confirmUpdate.getText())==false){
            Massage.errorMassage("Password must be match in both options");
            event.consume();
        }
        else if(dateCheck(birthUpdate.getValue())==false){
            Massage.errorMassage("Age must be at least 18");
            event.consume();
        }
        else if(Massage.confirmMassage("Are you sure you want to update the details?")){
            model.updateUserInfo(username,new User(usernameUpdate.getText(),passwordUpdate.getText(),birthUpdate.getValue(),firstUpdate.getText(),lastUpdate.getText(),cityUpdate.getText(),countryUpdate.getText()));
            if(usernameRead.getText().equals(this.username)==true){
                clearRead();
            }
            username=usernameUpdate.getText();
            updateHome(username);
            Massage.infoMassage("The update was made successfully!");
        }
        event.consume();
    }

    public void baggagePublishClick(Event event){
        if(baggagePublish.isSelected()==false){
            baggageLimitPublish.setText("0");
            baggageLimitPublish.setDisable(true);
        }
        else{
            baggageLimitPublish.setDisable(false);
            baggageLimitPublish.setText("");
        }
    }

    public void hospitalityPublishClick(Event event){
        if(hospitalityPublish.isSelected()==false){
            hospitalityRankPublish.setText("0");
            hospitalityRankPublish.setDisable(true);
        }
        else{
            hospitalityRankPublish.setDisable(false);
            hospitalityRankPublish.setText("");
        }
    }

    public void partTicketsPublishClick(Event event){
        if(partTicketsPublish.isSelected()){
            if((isTicketsMore1())==false){
                Massage.errorMassage("You can allow this only if you sell more than 1 ticket");
                partTicketsPublish.setSelected(false);
            }
        }
    }

    private boolean isTicketsMore1() {
        try {
            int num=Integer.parseInt(ticketsNumPublish.getText());
            return (num>1);
        }
        catch (Exception e){
            return false;
        }
    }

    public void publishPublish(Event event){
        if(isPublishPorblem()==false){
            if(model.publishVacation(new Vacation(username,fromDatePublish.getValue(),toDatePublish.getValue(),Integer.parseInt(pricePublish.getText()),Integer.parseInt(ticketsNumPublish.getText()),partTicketsPublish.isSelected(),sourcePublish.getText(),destinationPublish.getText(),baggagePublish.isSelected(),Integer.parseInt(baggageLimitPublish.getText()),ticketsClassPublish.getValue(),flights,flightTypePublish.getValue(),vacationTypePublish.getValue(),hospitalityPublish.isSelected(),Integer.parseInt(hospitalityRankPublish.getText())))==true){
                Massage.infoMassage("Vacation sell published successfully");
                flights=new ArrayList<>();
                clearPublish();
            }
            else
                Massage.infoMassage("Vacation sell publish has failed");
        }
    }

    private void clearPublish() {
        toDatePublish.setValue(null);
        fromDatePublish.setValue(null);
        sourcePublish.setText("");
        destinationPublish.setText("");
        ticketsNumPublish.setText("");
        hospitalityRankPublish.setText("");
        baggageLimitPublish.setText("");
        baggagePublish.setSelected(false);
        hospitalityPublish.setSelected(false);
        partTicketsPublish.setSelected(false);
        pricePublish.setText("");
        ticketsClassPublish.setValue(null);
        vacationTypePublish.setValue(null);
        flightTypePublish.setValue(null);

    }

    private boolean isPublishPorblem() {
        if (toDatePublish.getValue() == null || fromDatePublish.getValue() == null || sourcePublish.getText().isEmpty() || destinationPublish.getText().isEmpty() || ticketsNumPublish.getText().isEmpty() || pricePublish.getText().isEmpty() || ticketsClassPublish.getValue() == null || vacationTypePublish.getValue() == null || flightTypePublish.getValue() == null){
            Massage.errorMassage("Please fill all the fields as needed");
            return true;
        }
        if (baggagePublish.isSelected()) {
            if (baggageLimitPublish.getText().isEmpty()) {
                Massage.errorMassage("Please fill all the fields as needed");
                return true;
            } else if (isNumber(baggageLimitPublish.getText())) {
                Massage.errorMassage("Baggage limit must be a positive integer");
                return true;
            }
        }
        if (hospitalityPublish.isSelected()) {
            if (hospitalityRankPublish.getText().isEmpty()) {
                Massage.errorMassage("Please fill all the fields as needed");
                return true;
            } else if (isNumber(hospitalityRankPublish.getText()) == false) {
                Massage.errorMassage("Hospitality rank must be a positive integer");
                return true;
            }
        }
        if(fromDatePublish.getValue().isAfter(toDatePublish.getValue())){
            Massage.errorMassage("From time must be before To time");
            return true;
        }
        if(isNumber(pricePublish.getText())==false){
            Massage.errorMassage("Price must be a positive integer");
            return true;
        }
        if(isNumber(ticketsNumPublish.getText())==false){
            Massage.errorMassage("Number of tickets must be a positive integer");
            return true;
        }
        if(flights.size()==0){
            Massage.errorMassage("Number of listed flights must be at least 1");
            return true;
        }
        return false;
    }

    private boolean isNumber(String str){
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public void flightListPublish(Event event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../View/FlightsList.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            FlightsListController viewController =fxmlLoader.getController();
            viewController.addObserver(this);
            viewController.setFlights(flights);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Flight list");
            stage.setScene(new Scene(root1,1400,400));
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

    private boolean dateCheck(LocalDate date){
        LocalDate today=LocalDate.now().plusDays(1);
        LocalDate before18=today.minusYears(18);
        return (date.isBefore(before18));
    }

    @Override
    public void update(Observable o, Object arg) {
        flights=((ArrayList<Flight>) arg);
        flightListBut.setText("Flights list ("+flights.size()+")");
    }
}
