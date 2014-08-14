package codenvy.client.mvp.models;

import java.util.Date;

public class User{

    private String name;

    private Date birthday;

    private String address;

    public User(String name, Date birthday, String address) {
        this.name = name;
        this.address = address;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthday() {
        return birthday;
    }

}
