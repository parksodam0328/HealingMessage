package kr.hs.emirim.parksodam.healingmessage.user;

/**
 * Created by Mirim on 2018-03-19.
 */

public class User {

    public String id;
    public String pw;
    public String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {

    }

    public User(String id, String pw, String name) {
        this.id = id;
        this.pw = pw;
        this.name = name;
    }

}