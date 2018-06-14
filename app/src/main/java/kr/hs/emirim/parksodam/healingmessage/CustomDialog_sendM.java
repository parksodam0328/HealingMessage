package kr.hs.emirim.parksodam.healingmessage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mirim on 2018-06-13.
 */

public class CustomDialog_sendM {

    private Context context;


    public CustomDialog_sendM(Context context){
        this.context = context;
    }

    //호출할 다이얼로그 함수를 정의
    public void callDialog(){
        //커스텀 다이얼로그의 정의하기 위해 Dialog 클래스를 생성
        final Dialog dlg = new Dialog(context);

        //액티비티의 타이틀바를 숨긴다
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //커스텀 다이얼로그의 레이아웃 설정
        dlg.setContentView(R.layout.dialog_custom_sendm);

        //커스텀 다이얼로그 호출
        dlg.show();

        //커스텀 다이얼로그의 각 위젯들을 정의
        final TextView message = (TextView)dlg.findViewById(R.id.exp);
        final EditText input_sendM = (EditText)dlg.findViewById(R.id.input_sendm);
        final Button sendBtn = (Button) dlg.findViewById(R.id.sendBtn);
        final Button cancelBtn = (Button) dlg.findViewById(R.id.cancelBtn);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //커스텀 다이얼로그 종료
                dlg.dismiss();
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
