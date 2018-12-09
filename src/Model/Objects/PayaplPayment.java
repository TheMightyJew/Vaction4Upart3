package Model.Objects;

public class PayaplPayment extends Payment {
    private String username;
    private String password;


    @Override
    public boolean isPaypal() {
        return true;
    }

    @Override
    public boolean isVisa() {
        return false;
    }

    @Override
    public String toString() {
        return ("Paypal Payment: "+username+"|"+password);
    }
}
