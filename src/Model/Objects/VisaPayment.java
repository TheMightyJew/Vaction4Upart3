package Model.Objects;

import java.util.Date;

public class VisaPayment extends Payment {
    private int cardNumber;
    private int threeNumbers;
    private Date date;
    private int ownerId;
    private String ownerFirstName;
    private String ownerLastName;


    @Override
    public boolean isPaypal() {
        return false;
    }

    @Override
    public boolean isVisa() {
        return true;
    }

    @Override
    public String toString() {
        return ("Visa Payment: "+cardNumber+"|"+threeNumbers+"|"+date.toString()+"|"+ownerId+"|"+ownerFirstName+" "+ownerLastName);
    }
}
