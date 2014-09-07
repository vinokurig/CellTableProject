package codenvy.client.models;

import java.util.Date;

public class User {

    private String name;

    private Date birthday;

    private String address;

    private String notes;

    public User(String name, Date birthday, String address, String notes) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
}
