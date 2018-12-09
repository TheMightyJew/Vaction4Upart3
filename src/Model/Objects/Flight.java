package Model.Objects;

import java.util.Date;

public class Flight {

    private String flightCompany;
    private String sourceAirPort;
    private String destinationAirPort;
    private Date departDate;
    private Date landDate;
    private String departHour;
    private String landHour;

    public Flight(String flightCompany, String sourceAirPort, String destinationAirPort, Date departDate, Date landDate, String departHour, String landHour) {
        this.flightCompany = flightCompany;
        this.sourceAirPort = sourceAirPort;
        this.destinationAirPort = destinationAirPort;
        this.departDate = departDate;
        this.landDate = landDate;
        this.departHour = departHour;
        this.landHour = landHour;
    }

    public String getFlightCompany() {
        return flightCompany;
    }

    public String getSourceAirPort() {
        return sourceAirPort;
    }

    public String getDestinationAirPort() {
        return destinationAirPort;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public Date getLandDate() {
        return landDate;
    }

    public String getDepartHour() {
        return departHour;
    }

    public String getLandHour() {
        return landHour;
    }
}


