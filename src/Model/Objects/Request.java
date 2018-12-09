package Model.Objects;

public class Request {

    private String username;//user name of the request sender.
    private int vacationId;

    public Request(String username, int vacationId) {
        this.username = username;
        this.vacationId = vacationId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getVacationId() {
        return vacationId;
    }

    public void setVacationId(int vacationId) {
        this.vacationId = vacationId;
    }
}
