package Model.Objects;

public class PurchaseRequest {
    public enum Request_Status{rejected,accepted,pending,Out_Of_Date};

    private int id;
    private String username;
    private VacationSell vacationSell;
    private Request_Status status;
}
