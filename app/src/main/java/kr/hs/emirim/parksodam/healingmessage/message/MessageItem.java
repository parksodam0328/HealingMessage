package kr.hs.emirim.parksodam.healingmessage.message;

/**
 * Created by 민경 on 2018-04-05.
 */

public class MessageItem {
    public String name;
    public String context;

    public MessageItem(){}

    public MessageItem(String ms_name, String contents){
        this.name = ms_name;
        this.context = contents;
    }

    public String getMs_name() {
        return name;
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
