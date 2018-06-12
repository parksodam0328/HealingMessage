package kr.hs.emirim.parksodam.healingmessage.myprofile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.message.MessageItem;

/**
 * Created by 민경 on 2018-04-05.
 */

public class MyProfileAdapter extends BaseAdapter {

    private Context context;

    public MyProfileAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.fragment_my_profile, null);
        }
        
        MyProfileItem myProfileItem = new MyProfileItem();
        TextView nickName = (TextView)convertView.findViewById(R.id.prof_name);
        TextView feel = (TextView)convertView.findViewById(R.id.prof_feel);

        nickName.setText(myProfileItem.getNickname());
        feel.setText(myProfileItem.getFeel());

        return convertView;
    }
}
