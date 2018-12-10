package Model.Objects;

import java.time.LocalDate;
import java.util.List;

public class Vacation {

    public static enum Tickets_Type{Economy_class,Business_class};
    public static enum Vacation_Type{Pleasure,Business};
    public static enum Flight_Type{One_Way,Round_Trip};

    private String seller_username;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int price_Per_Ticket;
    private int tickets_Quantity;
    private boolean canBuyLess;
    private String sourceCountry;
    private String destinationCountry;
    private boolean baggage;
    private int baggageLimit;
    private Tickets_Type ticketsType;
    private List<Flight> flights;
    private Flight_Type flight_Type;
    private Vacation_Type vacation_type;
    private boolean hospitality_Included;
    private int hospitality_Rank;//between 1 to 5


}
