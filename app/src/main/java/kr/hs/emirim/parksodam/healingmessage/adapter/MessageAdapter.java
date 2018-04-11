package kr.hs.emirim.parksodam.healingmessage.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.message.MessageItem;
import kr.hs.emirim.parksodam.healingmessage.R;

/**
 * Created by 민경 on 2018-04-05.
 */

public class MessageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<MessageItem> list_data_message;

    public MessageAdapter(Context context, ArrayList<MessageItem> list_data_message) {
        this.context = context;
        this.list_data_message = list_data_message;
    }

    @Override
    public int getCount() {
        return list_data_message.size();
    }

    @Override
    public Object getItem(int position) {
        return list_data_message.get(position).getMs_name();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_listview_message, null);
        }

        MessageItem messageItem = list_data_message.get(position);
        TextView msName = (TextView)convertView.findViewById(R.id.list_ms_name);
        TextView msContents = (TextView)convertView.findViewById(R.id.list_ms_contents);

        msName.setText(messageItem.getMs_name());
        msContents.setText(messageItem.getContents());

        return convertView;
    }
}
