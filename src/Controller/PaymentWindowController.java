package Controller;

import Model.Model;
import Model.Objects.PayaplPayment;
import Model.Objects.VisaPayment;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.Observable;

public class PaymentWindowController extends Observable {

    public TextField firstNamePayment;
    public TextField lastNamePayment;
    public TextField cardNumberPayment;
    public TextField idPayment;
    public TextField cvvPayment;
    public DatePicker datePayment;
    public TextField emailPayment;
    public TextField passwordPayment;

    private Model model;
    private int requestID;

    public void setData(Model model,int requestID){
        this.model=model;
        this.requestID=requestID;
    }

    public void payVisaPayment(ActionEvent event){
        if(firstNamePayment.getText().isEmpty() || lastNamePayment.getText().isEmpty() || cardNumberPayment.getText().isEmpty() || idPayment.getText().isEmpty() || cvvPayment.getText().isEmpty() || datePayment.getValue()==null){
            Massage.errorMassage("Please fill all the fields");
        }
        else if(isNumber(cardNumberPayment.getText())==false || isNumber(idPayment.getText())==false || isNumber(cvvPayment.getText())==false || cvvPayment.getText().length()!=3){
            Massage.errorMassage("Error in input");
        }
        else{
            if(model.payForVacation(requestID,new VisaPayment((int)(Integer.parseInt(cardNumberPayment.getText())),(int)(Integer.parseInt(cvvPayment.getText())),datePayment.getValue(),idPayment.getText(),firstNamePayment.getText(),lastNamePayment.getText()))){
                Massage.infoMassage("Payment was made successfully");
                setChanged();
                notifyObservers();
                closeStage(event);
            }
            else{
                Massage.errorMassage("Payment failed");
            }
        }
    }

    public boolean isNumber(String str){
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void cancelPayment(javafx.event.ActionEvent event){
        closeStage(event);
    }

    public void payPaypalPayment(ActionEvent event){
        if(emailPayment.getText().isEmpty() || passwordPayment.getText().isEmpty()){
            Massage.errorMassage("Please fill all the fields");
        }
        if(model.payForVacation(requestID,new PayaplPayment(emailPayment.getText(),passwordPayment.getText()))){
            Massage.infoMassage("Payment was made successfully");
            setChanged();
            notifyObservers();
            closeStage(event);
        }
        else{
            Massage.errorMassage("Payment failed");
        }
    }

    private void closeStage(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
