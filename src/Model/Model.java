package Model;


import Model.Objects.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Model implements IModel {

    //attributes
    private String databaseName;
    //Enums...
    public enum UsersfieldNameEnum {
        Username, Password, Birthday, FirstName, LastName, City;
    }

    public enum VacationsfieldNameEnum {Vacation_id, Publisher_Username,Source_Country,Destination_Country, Price_Per_ticket, Num_Of_Passengers, Can_Buy_less_Tickets, Tickets_Type, Vacation_Type,Flight_Type,Baggage_Limit, Lodging_Included, Lodging_Rating,Vacation_Status;}

    public enum PurchaseRequestsfieldNameEnum {PurchaseRequest_id, Requester_Username, Vacation_id, Request_Status;}

    public enum FlightsToVacationsfieldNameEnum {Vacation_id, Flight_id};

//    public enum PurchasesfieldNameEnum {Username, VacationID, PaymentCompany, CardNumber, CVV, ValidDate, UserID, FirstName, LastName;}

    public enum FlightsfieldNameEnum {FlightID, OriginAirport, DestinationAirport, DepartureDate, DepartureTime, ArrivalDate, ArrivalTime, FlightComapny;}

    public enum PaymentfieldsNameEnum {PaymentID,Username,VacationID,Payment_Method;}

    public enum VisePaymentfieldsEnum {PaymentID,CardNumber,CVV,ValidDate,UserID,FirstName,LastName;}

    public enum PaypalPaymentfieldsEnum {PaymentID,email,password;}


    public enum tableNameEnum {Users_table, Vacations_Table, Purchases_Table, Flights_table, FlightsToVacations_Table, PurchaseRequests_Table,Payments_Table,VisaPayment_Table;}

    //constructor
    public Model(String databaseName) {
        this.databaseName = databaseName + ".db";
        createNewDatabase();
    }

    // private functions (generics)
    private void createNewDatabase() {

        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try (Connection conn = DriverManager.getConnection(url)) {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        createNewUsersTable();
//        createNewPurchasesTable();
        createNewFlightsTable();
        createNewVacationsTable();
        createNewPurchaseRequestTable();
        createNewFlightsToVacationsTable();
        createNewPaymentsTable();
        createNewVisaPaymentTable();
        createPaypalPaymentTavle();
    }//creating a new database with the parameter name

    public void createNewUsersTable() {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Users_Table (\n"
                    + "Username text PRIMARY KEY,\n"
                    + "	Password text NOT NULL,\n"
                    + "	Birthday text NOT NULL,\n"
                    + "	FirstName text NOT NULL,\n"
                    + "	LastName text NOT NULL,\n"
                    + "	City text NOT NULL,\n"
                    + "	State text NOT NULL\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//creating a new users table

    public void createNewPurchasesTable() {
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Purchases_Table (\n"
                    + "Username text NOT NULL,\n"
                    + "VacationID text NOT NULL,\n"
                    + "PaymentCompany text NOT NULL,\n"
                    + "CardNumber text,\n"
                    + "CVV text,\n"
                    + "ValidDate text,\n"
                    + "UserID text,\n"
                    + "FirstName text,\n"
                    + "LastName text,\n"
                    + "CONSTRAINT PK_Person PRIMARY KEY (Username,VacationID)"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewFlightsTable() {
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Flights_Table (\n"
                    + "FlightID BIGINT AUTO_INCREMENT NOT NULL,\n"
                    + "OriginAirport text NOT NULL,\n"
                    + "DestinationAirport text NOT NULL,\n"
                    + "DepartureDate text,\n"
                    + "DepartureTime text,\n"
                    + "ArrivalDate text,\n"
                    + "ArrivalTime text,\n"
                    + "FlightComapny text,\n"
                    + "CONSTRAINT PK_Person PRIMARY KEY (FlightID)"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewVacationsTable() {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Vacations_Table (\n"
                    + "Vacation_id INTEGER AUTOINCREMENT,\n"
                    + "Publisher_Username text NOT NULL,\n"
                    + "Source_Country text NOT NULL,\n"
                    + "Destination_Country text NOT NULL,\n"
                    + "Price_Per_ticket text NOT NULL,\n"
                    + "Num_Of_Passengers text NOT NULL,\n"
                    + "Can_Buy_less_Tickets text NOT NULL,\n"
                    + "Tickets_Type text NOT NULL,\n"
                    + "Vacation_Type text NOT NULL,\n"
                    + "Flight_Type text NOT NULL,\n"
                    + "Baggage_Limit text NOT NULL,\n"
                    + "Lodging_Included text NOT NULL,\n"
                    + "Lodging_Rating text NOT NULL,\n"
                    + "Vacation_Status text NOT NULL,\n"
                    + "	PRIMARY KEY (Vacation_id)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//creating a new vacations table

    public void createNewPurchaseRequestTable() {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PurchaseRequests_Table (\n"
                    + "PurchaseRequest_id INTEGER AUTOINCREMENT,\n"
                    + "Requester_Username text NOT NULL,\n"
                    + "Vacation_id text NOT NULL,\n"
                    + "Request_Status text NOT NULL,\n"
                    + "	PRIMARY KEY (PurchaseRequest_id)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//creating a new PurchaseRequest Table

    public void createNewFlightsToVacationsTable() {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS FlightsToVacations_Table (\n"
                    + "Vacation_id text NOT NULL,\n"
                    + "Flight_id text NOT NULL,\n"
                    + "	CONSTRAINT PK_FTV PRIMARY KEY (Vacation_id,Flight_id)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//creating a new FlightsToVacations Table

    public void createNewPaymentsTable()
    {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Payments_Table (\n"
                    + "PaymentID INTEGER AUTOINCREMENT NOT NULL,\n"
                    + "Username text NOT NULL,\n"
                    + "VacationID text NOT NULL,\n"
                    + "Payment_Method text NOT NULL,\n"
                    + "	CONSTRAINT PK_FTV PRIMARY KEY (PaymentID)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewVisaPaymentTable(){
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS VisaPayments_Table (\n"
                    + "PaymentID INTEGER AUTOINCREMENT NOT NULL,\n"
                    + "CardNumber text,\n"
                    + "CVV text,\n"
                    + "ValidDate text,\n"
                    + "UserID text,\n"
                    + "FirstName text,\n"
                    + "LastName text,\n"
                    + "	CONSTRAINT PK_FTV PRIMARY KEY (PaymentID)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPaypalPaymentTavle(){
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Paypalayments_Table (\n"
                    + "PaymentID INTEGER AUTOINCREMENT NOT NULL,\n"
                    + "email text NOT NULL,\n"
                    + "password text,\n"
                    + "	CONSTRAINT PK_FTV PRIMARY KEY (PaymentID)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //
    //
    //
    //
    //
    //
    //
    //functional functions:
    //
    //
    //
    //
    //
    //

    private void insertQuery(String table_name, Class<? extends Enum<?>> tableEnum, String[] insert_values) {
        String[] field_array = getNames(tableEnum);
        String sql = "INSERT INTO " + table_name + "(";
        for (int i = 0; i < field_array.length; i++) {
            sql += field_array[i] + ",";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql += ") VALUES(";
        for (int i = 0; i < field_array.length; i++) {
            if (i != field_array.length - 1)
                sql += "?,";
            else
                sql += "?)";
        }

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < field_array.length; i++) {
                if (i >= insert_values.length) {
                    pstmt.setString(i + 1, "");
                } else {
                    pstmt.setString(i + 1, insert_values[i]);
                }

            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//insert query pattern

    private List<String[]> selectQuery(String table_name, String where_condition) {
        String sql = "SELECT *" + " FROM " + table_name +" WHERE " + where_condition ;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
            int nCol = rs.getMetaData().getColumnCount();
            List<String[]> table = new ArrayList<>();
            while( rs.next()) {
                String[] row = new String[nCol];
                for( int iCol = 1; iCol <= nCol; iCol++ ){
                    Object obj = rs.getObject( iCol );
                    row[iCol-1] = (obj == null) ?null:obj.toString();
                }
                table.add( row );
            }
            return table;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }//select query pattern

    private void updateQuery(String table_name,Class<? extends Enum<?>> tableEnum, String[] update_values, String where_condition) {
        String [] fields_array = getNames(tableEnum);
        String sql = "UPDATE "+table_name+" SET ";
        for (int i=0;i<fields_array.length;i++){
            sql += fields_array[i] + " = ? ,";
        }
        sql = sql.substring(0,sql.length()-1) + " ";
        sql += "WHERE " + where_condition;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            for (int j=0;j<fields_array.length;j++){
                if (j>= update_values.length){
                    pstmt.setString(j+1, "");
                }
                else{
                    pstmt.setString(j+1, update_values[j]);
                }

            }
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//update query pattern

    private static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }//converting enum to String array

    private void deleteQuery(String table_name, String where_condition) {
        String sql = "DELETE FROM " + table_name+" WHERE "+where_condition ;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//delete query pattern

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:"+ Configuration.loadProperty("directoryPath") + databaseName;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }//connecting to the database
    //public Functions:

    // Users_Table
    @Override
    public void createUser(String Username_val, String Password_val, String Birthday_val, String FirstName_val, String LastName_val, String City_val) {
        String[] values = {Username_val, Password_val, Birthday_val, FirstName_val, LastName_val, City_val};
        insertQuery("Users_Table", UsersfieldNameEnum.class, values);
    }

//    @Override
//    public String[] getUser(String Username_val) {
//        List<String[]> result = selectQuery("Users_Table",UsersfieldNameEnum.Username + " = '" + Username_val+"'");
//        if(result.size() != 1)
//            return null;
//        else
//            return result.get(0);
//    }

//    @Override
//    public void updateUserInfo(String Username_key, String Username_val, String Password_val, String Birthday_val, String FirstName_val, String LastName_val, String City_val) {
//        String [] values = {Username_val,Password_val,Birthday_val,FirstName_val,LastName_val,City_val};
//        updateQuery(tableNameEnum.Users_table.toString(),UsersfieldNameEnum.class,values,UsersfieldNameEnum.Username.toString() + " = '" + Username_key+"'");
//    }

    @Override
    public boolean deleteUser(String Username_key) {
        deleteQuery(tableNameEnum.Users_table.toString(), UsersfieldNameEnum.Username + " = '" + Username_key + "'");
        return false;
    }

    @Override
    public boolean userExist(String username) {
        if(getUser(username) == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean createUser(User user) {

        return false;
    }

    @Override
    public User getUser(String Username_val) {
        return null;
    }

    @Override
    public boolean updateUserInfo(String username, User user) {

        return false;
    }


    public boolean UsersTable_checkPassword(String Username_val, String Password_val) {
        List<String[]> result = selectQuery("Users_Table", UsersfieldNameEnum.Username + " = '" + Username_val + "'");
        if (result.size() != 1)
            return false;
        else if (result.get(0)[1].equals(Password_val) == false)
            return false;
        return true;
    }



    //todo all function below

    public void UsersTable_createUser(User user) {
    }

    public void UsersTable_updateUserInfoByUsername(String username, User user) {

        try {
            String Birthday_val = user.getBirth_Date().toString();
            String [] values = {user.getUsername(),user.getPassword(),Birthday_val,user.getFirst_Name(),user.getLast_Name(),user.getCity(),user.getCountry()};
            updateQuery(tableNameEnum.Users_table.toString(),UsersfieldNameEnum.class,values,UsersfieldNameEnum.Username.toString() + " = '" + username+"'");
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    @Override
    public boolean publishVacation(Vacation vacation) {
        return false;
    }

    @Override
    public List<VacationSell> getVacations(String flightCompany, Date fromDate, Date toDate, boolean baggage, Integer baggageMin, Integer ticketsNum, Vacation.Tickets_Type tickets_type, Integer maxPricePerTicket, String sourceCountry, String destCountry, Vacation.Vacation_Type vacation_type, boolean hospitalityIncluded, Integer minHospitalityRank) {
        return null;
    }

    @Override
    public boolean sendRequest(Request request) {
        try{
            String[] values = {request.getUsername(),""+request.getVacationId(),"pending"};
            insertQuery(tableNameEnum.PurchaseRequests_Table.toString(), PurchaseRequestsfieldNameEnum.class, values);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public List<PurchaseRequest> getMyRequests(String username) {
        List<String[]> results = selectQuery(tableNameEnum.PurchaseRequests_Table.name(), PurchaseRequestsfieldNameEnum.Requester_Username + "='" + username + "'");
        List<PurchaseRequest> ans = new ArrayList<>();
        for (String[] row : results) {
            List<String[]> vaction_of_request = selectQuery(tableNameEnum.Vacations_Table.name(),VacationsfieldNameEnum.Vacation_id + "='"+ row[2]+"'");//get information to create vacation
            if(vaction_of_request.size()!=1)
                return null;
            String[] vacation = vaction_of_request.get(0);
            LocalDate fromDate = LocalDate.MAX;
            LocalDate toDate = LocalDate.MIN;
            List<Flight> flightForCreateVacation = new ArrayList<>();
            List<String[]> flight_of_vacation = selectQuery(tableNameEnum.FlightsToVacations_Table.name(),FlightsToVacationsfieldNameEnum.Vacation_id +"='"+row[2]+"'");
            for(String[] flight:flight_of_vacation)
            {
                List<String[]> flightInfo = selectQuery(tableNameEnum.Flights_table.name(),FlightsfieldNameEnum.FlightID+"='"+flight[1]+"'");
                for(String[] fly:flightInfo)
                {
                    Flight flightToList = new Flight(fly[7],fly[1],fly[2],LocalDate.parse(fly[3]),LocalDate.parse(fly[5]),fly[4],fly[6]);
                    flightForCreateVacation.add(flightToList);
                    LocalDate dep = LocalDate.parse(fly[3]);
                    LocalDate arr = LocalDate.parse(fly[5]);
                    if(dep.isBefore(fromDate))
                        fromDate = dep;
                    if(arr.isAfter(toDate))
                        toDate = arr;
                }
            }
            Vacation vac = new Vacation(vacation[1],fromDate,toDate,Integer.parseInt(vacation[4]),Integer.parseInt(vacation[5]),vacation[6].equals("true"),vacation[2],vacation[3],(Integer.parseInt(vacation[10])>0?true:false),Integer.parseInt(vacation[10]),Vacation.Tickets_Type.valueOf(vacation[7]),flightForCreateVacation,Vacation.Flight_Type.valueOf(vacation[9]),Vacation.Vacation_Type.valueOf(vacation[8]),vacation[11].equals("true"),Integer.parseInt(vacation[12]));
            VacationSell vacSell = new VacationSell(Integer.parseInt(vacation[0]),vac,VacationSell.Vacation_Status.valueOf(vacation[13]));
            PurchaseRequest purchaseRequest = new PurchaseRequest(Integer.parseInt(row[0]),row[2],vacSell,PurchaseRequest.Request_Status.valueOf(row[3]));
            ans.add(purchaseRequest);
        }
        return ans;
    }

    @Override
    public boolean payForVacation(int requestId, Payment payment) {
        return false;
    }

    @Override
    public List<PurchaseRequest> getReceivedRequests(String username) {
        return null;
    }

    @Override
    public void acceptRequest(int requestId) {
        String[] values = selectQuery(tableNameEnum.PurchaseRequests_Table.toString(), PurchaseRequestsfieldNameEnum.PurchaseRequest_id + "='" + requestId + "'").get(0);
        values[3] = PurchaseRequest.Request_Status.accepted.toString();
        updateQuery(tableNameEnum.PurchaseRequests_Table.toString(), PurchaseRequestsfieldNameEnum.class, values, PurchaseRequestsfieldNameEnum.PurchaseRequest_id + "='" + requestId + "'");
    }

    @Override
    public void rejectRequest(int requestId) {
        String[] values = selectQuery(tableNameEnum.PurchaseRequests_Table.toString(), PurchaseRequestsfieldNameEnum.PurchaseRequest_id + "='" + requestId + "'").get(0);
        values[3] = PurchaseRequest.Request_Status.rejected.toString();
        updateQuery(tableNameEnum.PurchaseRequests_Table.toString(), PurchaseRequestsfieldNameEnum.class, values, PurchaseRequestsfieldNameEnum.PurchaseRequest_id + "='" + requestId + "'");
    }

    @Override
    public boolean payForVacation(int requestId, Payment payment) {
        return false;
    }


    public boolean UsersTable_checkPassword(String Username_val,String Password_val) {
        List<String[]> result = selectQuery("Users_Table",UsersfieldNameEnum.Username + " = '" + Username_val+"'");
        if(result.size() != 1)
            return false;
        else if(result.get(0)[1].equals(Password_val)==false)
            return false;
        return true;
    }

    //todo all function below

    public static void main(String[] args) {
        Model model=new Model("Vaction4U");
        Request rq = new Request("oded",8);
        model.sendRequest(rq);


}
