package kr.hs.emirim.parksodam.healingmessage.message;

/**
 * Created by 민경 on 2018-04-05.
 */

public class MessageItem {
    public String name;
    public String title;
    public String context;


    public MessageItem(){}

    public MessageItem(String ms_name, String title, String contents){
        this.name = ms_name;
        this.title = title;
        this.context = contents;
    }

    public String getMs_name() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMs_name(String name) {
        this.name = name;
    }

    public String getContents() {
        return context;
    }

    public void setContents(String contents) {
        this.context = contents;
    }
}
