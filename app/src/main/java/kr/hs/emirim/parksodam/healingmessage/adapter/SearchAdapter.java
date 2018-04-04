package kr.hs.emirim.parksodam.healingmessage.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.search.SearchItem;

/**
 * Created by 민경 on 2018-03-15.
 */

public class SearchAdapter extends BaseAdapter{

    private Context context;
    private List<String> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;

    public SearchAdapter(List<String> list, Context context){
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View v = convertView;
        if(convertView == null){
            convertView = inflate.inflate(R.layout.row_listview,null);
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView)convertView.findViewById(R.id.list_text_name);
            //viewHolder.feel = (TextView)convertView.findViewById(R.id.list_text_feel);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        viewHolder.name.setText(list.get(position));
        //viewHolder.feel.setText(list.get(position));




        return convertView;
    }


    class ViewHolder{
        TextView name;
        TextView feel;
    }
}
