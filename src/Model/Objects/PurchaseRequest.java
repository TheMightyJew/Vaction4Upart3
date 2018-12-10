package Model.Objects;

public class PurchaseRequest {
    public enum Request_Status{rejected,accepted,pending};

    private int id;
    private String username;
    private VacationSell vacationSell;
    private Request_Status status;

    public PurchaseRequest(int id, String username, VacationSell vacationSell, Request_Status status) {
        this.id = id;
        this.username = username;
        this.vacationSell = vacationSell;
        this.status = status;
    }
}
