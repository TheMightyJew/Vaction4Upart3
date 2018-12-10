package Controller;

import Model.Objects.Flight;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.util.*;

public class FlightsListController extends Observable implements Observer {

    private ArrayList<Flight> flights=new ArrayList<>();
    @Override
    public void update(Observable o, Object arg) {
        flights.add((Flight)(arg));
        showList();
    }

    private void showList() {
    }

    public void addFlight(Event event){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addFlight.fxml"));
            AddFlightController viewController =fxmlLoader.getController();
            viewController.addObserver(this);
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Add flight");
            stage.setScene(new Scene(root1,600,400));
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void reset(Event event){
        if(Massage.confirmMassage("Are you sure you want to reset all the flights above?")){
            flights=new ArrayList<>();
            showList();
        }
    }

    public void confirm(ActionEvent event){
        if(Massage.confirmMassage("Are you sure these are all the flights of the vacation?")) {
            notifyObservers(flights);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }

    }


}
