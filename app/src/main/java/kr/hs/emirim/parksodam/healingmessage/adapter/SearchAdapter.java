package kr.hs.emirim.parksodam.healingmessage.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.search.SearchItem;


/**
 * Created by 민경 on 2018-03-15.
 */

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SearchItem> list_data;
    Button button;

    public SearchAdapter(Context context, ArrayList<SearchItem> list_data) {
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
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_listview, null);
        }

        SearchItem searchItem = list_data.get(position);
        TextView tvName = (TextView) convertView.findViewById(R.id.list_text_name);
        TextView tvFeel = (TextView) convertView.findViewById(R.id.list_text_feel);

        // 보내기 클릭 시 dialog띄우기
        Button button1 = (Button) convertView.findViewById(R.id.list_btn_send);
        button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);

                alert.setTitle("응원하기");
                alert.setMessage("작은 응원의 한마디로 큰 힘이 되어주세요!");


                final EditText name = new EditText(context);
                alert.setView(name);

                alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String username = name.getText().toString();

                    }
                });


                alert.setNegativeButton("no",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                alert.show();

            }
        });

        tvName.setText(searchItem.getName());
        tvFeel.setText(searchItem.getFeel());

        return convertView;
    }
}
