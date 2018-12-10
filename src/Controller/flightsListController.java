package Controller;

import Model.Objects.Flight;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.util.*;

public class flightsListController extends Observable implements Observer {

    private ArrayList<Flight> flights=new ArrayList<>();
    @Override
    public void update(Observable o, Object arg) {
        flights.add((Flight)(arg));
        showList();
    }

    private void showList() {
    }

    public void addFlight(Event event){

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
