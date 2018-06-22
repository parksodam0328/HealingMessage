package kr.hs.emirim.parksodam.healingmessage;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirim on 2018-06-13.
 */

public class CustomDialog_setting {

    private Context context;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;


    public CustomDialog_setting(Context context){
        this.context = context;
    }

    //호출할 다이얼로그 함수를 정의
    public void callDialog(final String id){
        Log.e("idididididi", id);
        //커스텀 다이얼로그의 정의하기 위해 Dialog 클래스를 생성
        final Dialog dlg = new Dialog(context);

        //액티비티의 타이틀바를 숨긴다
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //커스텀 다이얼로그의 레이아웃 설정
        dlg.setContentView(R.layout.dialog_custom_setting);

        //커스텀 다이얼로그 호출
        dlg.show();

        //커스텀 다이얼로그의 각 위젯들을 정의
        final TextView message = (TextView)dlg.findViewById(R.id.exp);
        final EditText update_nick = (EditText)dlg.findViewById(R.id.update_nick);
        final EditText update_pw = (EditText)dlg.findViewById(R.id.update_pw);
        final ImageButton happy = (ImageButton)dlg.findViewById(R.id.emo_btn1);
        final ImageButton sad = (ImageButton)dlg.findViewById(R.id.emo_btn2);
        final ImageButton shyness = (ImageButton)dlg.findViewById(R.id.emo_btn3);
        final ImageButton angry = (ImageButton)dlg.findViewById(R.id.emo_btn4);
        final TextView ch_emo = (TextView)dlg.findViewById(R.id.ch_emo);
        final Button sendBtn = (Button) dlg.findViewById(R.id.sendBtn);
        final Button cancelBtn = (Button) dlg.findViewById(R.id.cancelBtn);

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ch_emo.setText("happy");
            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ch_emo.setText("sad");
            }
        });
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ch_emo.setText("angry");
            }
        });
        shyness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ch_emo.setText("shyness");
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference("users/");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("users/"+id);
        databaseReference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals("name")) {
                    update_nick.setText(dataSnapshot.getValue().toString());
                    Log.e("프로필에 닉네임이 들어갑니당", dataSnapshot.getValue().toString());
                }

                if (dataSnapshot.getKey().equals("pw")) {
                    update_pw.setText(dataSnapshot.getValue().toString());
                    Log.e("프로필에 감정!", dataSnapshot.getValue().toString());
                }
                // Log.e("jhi", "Value is: ");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(update_nick.getText().toString().equals("") || update_pw.getText().toString().equals("") || ch_emo.getText().toString().equals("")){
                Toast.makeText(context, "변경할 정보를 입력해주세요!", Toast.LENGTH_SHORT).show();
            }else{
                Map<String, Object> taskMap = new HashMap<String, Object>();
                        taskMap.put("name", update_nick.getText().toString());
                        taskMap.put("pw", update_pw.getText().toString());
                        taskMap.put("feel", ch_emo.getText().toString());
                        Log.e("dddddd", id);
                        databaseReference.child(id).updateChildren(taskMap);
                        Toast.makeText(context, "정보를 변경했습니다", Toast.LENGTH_SHORT).show();
                        dlg.dismiss();
                    }
                }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //커스텀 다이얼로그 종료
                dlg.dismiss();
            }
        });
    }
}
