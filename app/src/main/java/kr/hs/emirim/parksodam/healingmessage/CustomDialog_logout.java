package kr.hs.emirim.parksodam.healingmessage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import kr.hs.emirim.parksodam.healingmessage.user.LoginActivity;

/**
 * Created by Mirim on 2018-06-13.
 */

public class CustomDialog_logout {

    private Context context;

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
        final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
        final String mUserId = mAuth.getCurrentUser().getUid();
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> tokenMapRemove = new HashMap<>();
                tokenMapRemove.put("token_id", FieldValue.delete());

                mFirestore.collection("Users").document(mUserId).update(tokenMapRemove).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mAuth.signOut();
                        dlg.dismiss();
                        Intent loginIntent = new Intent(context.getApplicationContext(),LoginActivity.class);
                        context.getApplicationContext().startActivity(loginIntent);

                    }
                });
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
