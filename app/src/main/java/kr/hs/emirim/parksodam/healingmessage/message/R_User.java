package kr.hs.emirim.parksodam.healingmessage.message;

/**
 * Created by 태은 on 2018-06-21.
 */

public class R_User {
    public String name;
    public String context;

    public R_User(String name, String context) {
        this.name = name;
        this.context = context;
    }

    public String getContext(){
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public R_User() {

    }
}
