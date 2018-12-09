package Model;


import Model.Objects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Model implements IModel {
    //attributes
    private String databaseName;
    //Enums...
    public enum UsersfieldNameEnum {Username,Password,Birthday,FirstName,LastName,City,State;}
    public enum VacationsfieldNameEnum {Vacation_id,Publisher_Username,Num_Of_Passengers,Vacation_Type,Lodging_Included,Lodging_Rating;}
    public enum PurchaseRequestsfieldNameEnum {PurchaseRequest_id,Requester_Username,Vacation_id,Request_Status;}
    public enum FlightsToVacationsfieldNameEnum {Vacation_id,Flight_id,Tickets_type,baggage;}
    public enum PurchasesfieldNameEnum {Username,VacationID,PaymentCompany,CardNumber,CVV,ValidDate,UserID,FirstName,LastName;}
    public enum FlightsfieldNameEnum {FlightID,OriginAirport,DestinationAirport,DepartureDate,DepartureTime,ArrivalDate,ArrivalTime,FlightComapny;}

    public enum tableNameEnum{Users_table,Vacations_Table,Purchases_Table,Flights_table,FlightsToVacations_Table,PurchaseRequests_Table;}

    //constructor
    public Model(String databaseName) {
        this.databaseName = databaseName+".db";
        createNewDatabase();
    }
    // private functions (generics)
    private void createNewDatabase() {

        String url = "jdbc:sqlite:resources/" /*Configuration.loadProperty("directoryPath")*/ + databaseName;

        try (Connection conn = DriverManager.getConnection(url)) {
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        createNewUsersTable();
        createNewPurchasesTable();
        createNewFlightsTable();
        createNewVacationsTable();
        createNewPurchaseRequestTable();
        createNewFlightsToVacationsTable();
    }//creating a new database with the parameter name
    public void createNewUsersTable() {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:"+ Configuration.loadProperty("directoryPath") + databaseName);
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
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }//creating a new users table

    public void createNewPurchasesTable(){
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try{
            c = DriverManager.getConnection("jdbc:sqlite:"+ Configuration.loadProperty("directoryPath") + databaseName);
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
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void createNewFlightsTable(){
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try{
            c = DriverManager.getConnection("jdbc:sqlite:"+ Configuration.loadProperty("directoryPath") + databaseName);
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
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void createNewVacationsTable() {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:"+ Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Vacations_Table (\n"
                    + "Vacation_id BIGINT AUTO_INCREMENT,\n"
                    + "Publisher_Username text NOT NULL,\n"
                    + "	Num_Of_Passengers text NOT NULL,\n"
                    + "Vacation_Type text NOT NULL,\n"
                    + "Lodging_Included text NOT NULL,\n"
                    + "Lodging_Rating text NOT NULL,\n"
                    + "	PRIMARY KEY (Vacation_id)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }//creating a new vacations table

    public void createNewPurchaseRequestTable() {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:"+ Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PurchaseRequests_Table (\n"
                    + "PurchaseRequest_id BIGINT AUTO_INCREMENT,\n"
                    + "Requester_Username text NOT NULL,\n"
                    + "Vacation_id text NOT NULL,\n"
                    + "Request_Status text NOT NULL,\n"
                    + "	PRIMARY KEY (PurchaseRequest_id)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }//creating a new PurchaseRequest Table

    public void createNewFlightsToVacationsTable() {
        // SQLite connection string
        Connection c = null;
        Statement stmt = null;
        String url = "jdbc:sqlite:" + Configuration.loadProperty("directoryPath") + databaseName;

        try {
            c = DriverManager.getConnection("jdbc:sqlite:"+ Configuration.loadProperty("directoryPath") + databaseName);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS FlightsToVacations_Table (\n"
                    + "Vacation_id text NOT NULL,\n"
                    + "Flight_id text NOT NULL,\n"
                    + "Tickets_type text NOT NULL,\n"
                    + "baggage text NOT NULL,\n"
                    + "	CONSTRAINT PK_FTV PRIMARY KEY (Vacation_id,Flight_id,Tickets_type,baggage)\n"
                    + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }//creating a new FlightsToVacations Table


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

    private void insertQuery(String table_name,Class<? extends Enum<?>> tableEnum, String[] insert_values) {
        String [] field_array = getNames(tableEnum);
        String sql = "INSERT INTO "+table_name+"(";
        for (int i=0;i<field_array.length;i++){
            sql += field_array[i] + ",";
        }
        sql = sql.substring(0,sql.length()-1);
        sql += ") VALUES(";
        for (int i=0;i<field_array.length;i++){
            if (i != field_array.length -1)
                sql +=  "?,";
            else
                sql += "?)";
        }

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i=0;i<field_array.length;i++){
                if(i>= insert_values.length){
                    pstmt.setString(i+1, "");
                }
                else{
                    pstmt.setString(i+1, insert_values[i]);
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
    public void createUser(String Username_val, String Password_val, String Birthday_val, String FirstName_val, String LastName_val, String City_val,String State_val) {
        String [] values = {Username_val,Password_val,Birthday_val,FirstName_val,LastName_val,City_val,State_val};
        insertQuery("Users_Table",UsersfieldNameEnum.class,values);
    }

    @Override
    public User getUser(String Username_val) {
        List<String[]> result = selectQuery("Users_Table",UsersfieldNameEnum.Username + " = '" + Username_val+"'");
        if(result.size() != 1)
            return null;
        else{
            String[] ans = result.get(0);
            Date bDate = new Date(ans[2]);
            return new User(ans[0],ans[1],bDate,ans[3],ans[4],ans[5],ans[6]);
        }
    }

    @Override
    public void updateUserInfo(String Username_key, String Username_val, String Password_val, String Birthday_val, String FirstName_val, String LastName_val, String City_val,String State_val) {
        String [] values = {Username_val,Password_val,Birthday_val,FirstName_val,LastName_val,City_val,State_val};
        updateQuery(tableNameEnum.Users_table.toString(),UsersfieldNameEnum.class,values,UsersfieldNameEnum.Username.toString() + " = '" + Username_key+"'");
    }

    @Override
    public boolean deleteUser(User user) {
        try{
            deleteQuery(tableNameEnum.Users_table.toString(),UsersfieldNameEnum.Username + " = '" + user.getUsername() + "'");
            return  true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean userExist(String username) {
        if(getUser(username) == null)
            return false;
        else
            return true;
    }
    //TODO: Functions.
    @Override
    public boolean createUser(User user) {
        try {
            String user_birth_date = user.getBirth_Date().getMonth()+"/"+user.getBirth_Date().getDay()+"/"+user.getBirth_Date().getYear();
            String[] values = {user.getUsername(), user.getPassword(), user_birth_date, user.getFirst_Name(), user.getLast_Name(), user.getCity(), user.getCountry()};
            insertQuery("Users_Table", UsersfieldNameEnum.class, values);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateUserInfo(String username, User user) {
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
        return false;
    }

    @Override
    public List<PurchaseRequest> getMyRequests(String username) {
        return null;
    }

    @Override
    public List<PurchaseRequest> getReceivedRequests(String username) {
        return null;
    }

    @Override
    public void acceptRequest(int requestId) {

    }

    @Override
    public void rejectRequest(int requestId) {

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



    }
}
