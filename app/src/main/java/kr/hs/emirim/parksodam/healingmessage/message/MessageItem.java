package kr.hs.emirim.parksodam.healingmessage.message;

/**
 * Created by 민경 on 2018-04-05.
 */

public class MessageItem {
    private String ms_name;
    private String contents;

    public MessageItem(){}

    public MessageItem(String ms_name, String contents){
        this.ms_name = ms_name;
        this.contents = contents;
    }

    public String getMs_name() {
        return ms_name;
    }

    public void setMs_name(String name) {
        this.ms_name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
