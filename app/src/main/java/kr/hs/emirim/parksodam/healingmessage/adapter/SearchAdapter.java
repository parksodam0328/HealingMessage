package kr.hs.emirim.parksodam.healingmessage.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.search.SearchItem;

/**
 * Created by 민경 on 2018-03-15.
 */

public class SearchAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<SearchItem> list_data;

    public SearchAdapter(Context context, ArrayList<SearchItem> list_data){
        this.context = context;
        this.list_data = list_data;
    }

    @Override
    public int getCount() {
        return list_data.size();
    }

    @Override
    public Object getItem(int position) {
        return list_data.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.row_listview,null);
        }

        SearchItem searchItem = list_data.get(position);
        TextView tvName = (TextView)convertView.findViewById(R.id.list_text_name);
        TextView tvFeel = (TextView)convertView.findViewById(R.id.list_text_feel);

        tvName.setText(searchItem.getName());
        tvFeel.setText(searchItem.getFeel());

        return convertView;
    }
}
