package Controller;

import Model.Objects.Flight;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.Observable;
import java.util.Optional;

public class addFlightController extends Observable{

    public TextField flightCompany;
    public TextField fromAirport;
    public TextField toAirport;
    public DatePicker departDate;
    public TextField departHour;
    public DatePicker landingDate;
    public TextField landingHour;

    public void cancel(ActionEvent event){
        if(Massage.confirmMassage("Are you sure you dont want to add a flight?"))
            closeStage(event);
    }

    public void confirm(ActionEvent event){
        if(isEmpty())
            Massage.errorMassage("please fill all the fields");
        else if(Massage.confirmMassage("Are you sure you want to add this flight?")){
            notifyObservers(new Flight(flightCompany.getText(),fromAirport.getText(),toAirport.getText(),departDate.getValue(),landingDate.getValue(),departHour.getText(),landingHour.getText()));
            closeStage(event);
        }

    }

    private boolean isEmpty() {
        if(flightCompany.getText().isEmpty() || fromAirport.getText().isEmpty() || departHour.getText().isEmpty() || landingHour.getText().isEmpty() || toAirport.getText().isEmpty() || departDate.getValue()==null || landingDate.getValue()==null)
            return false;
        return true;
    }

    private void closeStage(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
