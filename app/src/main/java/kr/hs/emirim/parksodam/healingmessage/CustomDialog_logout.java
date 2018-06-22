package kr.hs.emirim.parksodam.healingmessage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.hs.emirim.parksodam.healingmessage.user.LoginActivity;

/**
 * Created by Mirim on 2018-06-13.
 */

public class CustomDialog_logout {

    private Context context;
    DatabaseReference databaseReference;


    public CustomDialog_logout(Context context){
        this.context = context;
    }

    //호출할 다이얼로그 함수를 정의
    public void callDialog(){
        //커스텀 다이얼로그의 정의하기 위해 Dialog 클래스를 생성
        final Dialog dlg = new Dialog(context);

        //액티비티의 타이틀바를 숨긴다
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //커스텀 다이얼로그의 레이아웃 설정
        dlg.setContentView(R.layout.dialog_custom_logout);

        //커스텀 다이얼로그 호출
        dlg.show();

        //커스텀 다이얼로그의 각 위젯들을 정의
        final TextView message = (TextView)dlg.findViewById(R.id.exp);
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelBtn = (Button) dlg.findViewById(R.id.cancelBtn);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mAuth.signOut();
                    Toast.makeText(context, "로그아웃했습니다!", Toast.LENGTH_SHORT).show();
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                } catch (Exception e) {
                    Log.e("hoho", "onClick: Exception " + e.getMessage(), e);
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
