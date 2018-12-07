package Model.Objects;

public class VacationSell {

    public static enum Vacation_Status{available,sold,out_of_date};

    private int id;
    private Vacation vacation;
    private Vacation_Status status;
}
