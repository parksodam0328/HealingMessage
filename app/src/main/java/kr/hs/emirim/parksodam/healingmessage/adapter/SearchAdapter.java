package kr.hs.emirim.parksodam.healingmessage.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.CustomDialog_sendM;
import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.WriteMessageActivity;
import kr.hs.emirim.parksodam.healingmessage.search.SearchActivity;
import kr.hs.emirim.parksodam.healingmessage.search.SearchItem;


/**
 * Created by 민경 on 2018-03-15.
 */

public class SearchAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SearchItem> list_data_search;
    Button button;

    public SearchAdapter(Context context, ArrayList<SearchItem> list_data_search) {
        this.context = context;
        this.list_data_search = list_data_search;
    }

    @Override
    public int getCount() {
        return list_data_search.size();
    }

    @Override
    public Object getItem(int position) {
        return list_data_search.get(position).getName();
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

        SearchItem searchItem = list_data_search.get(position);
        TextView tvName = (TextView) convertView.findViewById(R.id.list_text_name);
        TextView tvFeel = (TextView) convertView.findViewById(R.id.list_text_feel);
        ImageButton button1 = (ImageButton) convertView.findViewById(R.id.list_btn_send);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onMyListener.onReceivedData(id);
                Intent i = new Intent(context, WriteMessageActivity.class);
                context.startActivity(i);
            }
        });


/*        // 보내기 클릭 시 dialog띄우기
        ImageButton button1 = (ImageButton) convertView.findViewById(R.id.list_btn_send);
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
        });*/

//        ImageButton button1 = (ImageButton) convertView.findViewById(R.id.list_btn_send);
//
//        //보내기 클릭시 커스텀 다이얼로그 띄우기
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //커스텀 다이얼로그를 생성,
//                CustomDialog_sendM customDialogM = new CustomDialog_sendM(context);
//                customDialogM.callDialog();
//            }
//        });

        tvName.setText(searchItem.getName());
        tvFeel.setText(searchItem.getFeel());

        return convertView;
    }
}
