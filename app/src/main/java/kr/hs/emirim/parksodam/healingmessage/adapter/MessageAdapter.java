package kr.hs.emirim.parksodam.healingmessage.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.message.MessageItem;

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

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.row_listview_message, null);
        }

        MessageItem messageItem = list_data_message.get(position);
        TextView msName = (TextView)convertView.findViewById(R.id.list_ms_name);
        TextView msTitle = (TextView)convertView.findViewById(R.id.list_ms_title);
        TextView msContents = (TextView)convertView.findViewById(R.id.list_ms_contents);
        ImageView pro_img = (ImageView)convertView.findViewById(R.id.list_image);
        msName.setText(messageItem.name);
        msTitle.setText(messageItem.title);
        msContents.setText(messageItem.context);
        try {
            Picasso.with(context)
                    .load(R.drawable.pro_img)
                    .placeholder(R.drawable.pro_img)
                    .error(R.drawable.pro_img)
                    .resize(260, 250)
                    .centerCrop()
                    .into(pro_img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
}
