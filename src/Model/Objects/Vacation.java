package Model.Objects;

import java.time.LocalDate;
import java.util.List;

public class Vacation {

    public static enum Tickets_Type{Economy_class,Business_class};
    public static enum Vacation_Type{Pleasure,Business};
    public static enum Flight_Type{One_Way,Round_Trip};

    private String seller_username;//
    private LocalDate fromDate;//
    private LocalDate toDate;//
    private int price_Per_Ticket;//
    private int tickets_Quantity;//
    private boolean canBuyLess;//true\false
    private String sourceCountry;//
    private String destinationCountry;//
    private boolean baggage;//if baggageLimit>0 -> true, else false
    private int baggageLimit;
    private Tickets_Type ticketsType;//
    private List<Flight> flights;//
    private Flight_Type flight_Type;//
    private Vacation_Type vacation_type;//
    private boolean hospitality_Included;//
    private int hospitality_Rank;//between 1 to 5

    public Vacation(String seller_username, LocalDate fromDate, LocalDate toDate, int price_Per_Ticket, int tickets_Quantity, boolean canBuyLess, String sourceCountry, String destinationCountry, boolean baggage, int baggageLimit, Tickets_Type ticketsType, List<Flight> flights, Flight_Type flight_Type, Vacation_Type vacation_type, boolean hospitality_Included, int hospitality_Rank) {
        this.seller_username = seller_username;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price_Per_Ticket = price_Per_Ticket;
        this.tickets_Quantity = tickets_Quantity;
        this.canBuyLess = canBuyLess;
        this.sourceCountry = sourceCountry;
        this.destinationCountry = destinationCountry;
        this.baggage = baggage;
        this.baggageLimit = baggageLimit;
        this.ticketsType = ticketsType;
        this.flights = flights;
        this.flight_Type = flight_Type;
        this.vacation_type = vacation_type;
        this.hospitality_Included = hospitality_Included;
        this.hospitality_Rank = hospitality_Rank;
    }
}
