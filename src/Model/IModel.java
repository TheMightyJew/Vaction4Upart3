package Model;


import Model.Objects.*;
import javafx.util.Pair;

import java.util.Date;
import java.util.List;

public interface IModel {
    //Users Actions
    void UsersTable_createUser(String Username_val,String Password_val,String Birthday_val, String FirstName_val,String LastName_val,String City_val);
    User UsersTable_getUserByUsername(String Username_val);
    void UsersTable_updateUserInfoByUsername(String Username_key, String Username_val,String Password_val,String Birthday_val, String FirstName_val,String LastName_val,String City_val);
    boolean UsersTable_deleteUserByUsername(User user);
    boolean UsersTable_existingUsername(User user);
    //update top functions to this functions:
    boolean UsersTable_createUser(User user);

    boolean UsersTable_updateUserInfoByUsername(String username,User user);
    //new functions:

    /**
     * put the vacation in the database with id that you decide and a VacationSell.Vacation_Status=available
     * @param vacation
     * @return if publish succeeded;
     */
    boolean publishVacation(Vacation vacation);

    /**
     * put the request in the database with id that you decide and a PurchaseRequst.Request_Status=pending
     * @param request
     * @return if send succeeded;
     */
    boolean sendRequest(Request request);

    /**
     * get the requests that username user sent to other users.
     * @param username
     * @return
     */
    List<PurchaseRequest> getMyRequests(String username);

    /**
     * save payment to database using payment.tostring function.
     * @param requestId
     * @param payment
     * @return
     */
    boolean payForVacation(int requestId,Payment payment);
    /**
     * get the request that username user received from other users.
     * @param username
     * @return
     */
    List<PurchaseRequest> getReceivedRequests(String username);

    /**
     *
     * @param requestId
     */
    boolean acceptRequest(int requestId);

    /**
     *
     * @param requestId
     */
    boolean rejectRequest(int requestId);

    /**
     *
     * @param flightCompany
     * @param fromDate
     * @param toDate
     * @param baggage
     * @param baggageMin
     * @param ticketsNum
     * @param tickets_type
     * @param maxPricePerTicket
     * @param sourceCountry
     * @param destCountry
     * @param vacation_type
     * @param hospitalityIncluded
     * @param minHospitalityRank
     * @return list of vacationsell where status=available;
     */
    List<VacationSell> getVacations(String flightCompany, Date fromDate, Date toDate, boolean baggage, int baggageMin, int ticketsNum, Vacation.Tickets_Type tickets_type,int maxPricePerTicket, String sourceCountry, String destCountry, Vacation.Vacation_Type vacation_type, boolean hospitalityIncluded, int minHospitalityRank);

}
