package kr.hs.emirim.parksodam.healingmessage.myprofile;

/**
 * Created by 민경 on 2018-04-05.
 */

public class MyProfileItem {
    public String nickname;
    public String feel;


    public MyProfileItem() {
    }

    public MyProfileItem(String nickname, String feel) {
        this.nickname = nickname;
        this.feel = feel;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFeel() {
        return feel;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }
}

