package kr.hs.emirim.parksodam.healingmessage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.SearchItem;
import kr.hs.emirim.parksodam.healingmessage.adapter.SearchAdapter;

/**
 * Created by Mirim on 2018-04-03.
 */

public class SearchAdd extends SearchAdapter {
    private Activity context;
    List<SearchItem> searchList;

    public SearchAdd(List<String> list, Context context) {
        super(list, context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.row_listview, null, true);

        TextView nameTxt = (TextView)listViewItem.findViewById(R.id.list_text_name);
        TextView feelTxt = (TextView)listViewItem.findViewById(R.id.list_text_feel);

        SearchItem searchAdd = searchList.get(position);
        nameTxt.setText(searchAdd.getName());
        feelTxt.setText(searchAdd.getFeel());

        return listViewItem;
    }
}