package kr.hs.emirim.parksodam.healingmessage.user;

/**
 * Created by Mirim on 2018-03-19.
 */

public class User {

    public String id;
    public String pw;
    public String name;

    public User() {

    }

    public User(String id, String pw, String name) {
        this.id = id;
        this.pw = pw;
        this.name = name;
    }

}