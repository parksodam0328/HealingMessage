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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.CustomDialog_sendM;
import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.WriteMessageActivity;
import kr.hs.emirim.parksodam.healingmessage.search.SearchItem;

/**
 * Created by 민경 on 2018-04-05.
 */

public class HomeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SearchItem> list_data;
    Button button;
    private String id;

    public HomeAdapter(Context context, ArrayList<SearchItem> list_data) {
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
        final TextView tvName = (TextView) convertView.findViewById(R.id.list_text_name);
        final TextView tvFeel = (TextView) convertView.findViewById(R.id.list_text_feel);

        ImageView pro_img = (ImageView) convertView.findViewById(R.id.list_image);

        // 보내기 클릭 시 dialog띄우기
        ImageView button1 = (ImageView) convertView.findViewById(R.id.list_btn_send);

        try {
            Picasso.with(context)
                    .load(R.drawable.send)
                    .placeholder(R.drawable.send)
                    .error(R.drawable.send)
                    .resize(300, 120)
                    .centerCrop()
                    .into(button1);
            Picasso.with(context)
                    .load(R.drawable.pro_img)
                    .placeholder(R.drawable.pro_img)
                    .error(R.drawable.pro_img)
                    .resize(210, 200)
                    .centerCrop()
                    .into(pro_img);
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* button1.setOnClickListener(new Button.OnClickListener() {
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

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //onMyListener.onReceivedData(id);
                Intent i = new Intent(context, WriteMessageActivity.class);
                i.putExtra("name", tvName.getText().toString());
                context.startActivity(i);
            }
        });
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
