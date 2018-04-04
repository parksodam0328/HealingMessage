package kr.hs.emirim.parksodam.healingmessage.search;

import android.graphics.drawable.Drawable;

/**
 * Created by 민경 on 2018-03-20.
 */

public class SearchItem {
    private String name;
    private String feel;

    public SearchItem(){}

    public SearchItem(String name, String feel){
        this.name = name;
        this.feel = feel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

}
