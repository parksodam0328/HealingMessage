package kr.hs.emirim.parksodam.healingmessage;

import android.graphics.drawable.Drawable;

/**
 * Created by 민경 on 2018-03-20.
 */

public class SearchItem {
    private Drawable image;
    private String name;
    private String feel;

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
