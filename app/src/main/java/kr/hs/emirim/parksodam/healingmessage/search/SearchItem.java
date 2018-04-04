package kr.hs.emirim.parksodam.healingmessage.search;

import android.graphics.drawable.Drawable;

/**
 * Created by 민경 on 2018-03-20.
 */

public class SearchItem {
    private Drawable image;
    private String name;
    private String feel;
    private String pw;
    private String id;


    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
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
