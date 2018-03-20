package kr.hs.emirim.parksodam.healingmessage;

/**
 * Created by 민경 on 2018-03-19.
 */

public class Search {
    public String name;
    public String feel;
    public String profile;

    public String getName(){return name;}
    public String getProfile(){return profile;}
    public String getFeel() {return feel;}

    public Search(String name, String feel, String profile){
        this.name = name;
        this.profile = profile;
        this.feel = feel;
    }

    public Search(){}
}
