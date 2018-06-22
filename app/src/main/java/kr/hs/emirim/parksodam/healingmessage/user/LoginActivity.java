
package kr.hs.emirim.parksodam.healingmessage.user;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import kr.hs.emirim.parksodam.healingmessage.BarActivity;
import kr.hs.emirim.parksodam.healingmessage.R;


public class LoginActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    //int word_random;
    Handler handler = new Handler();
    EditText checkId;
    EditText checkPw;
    ImageView login;
    ImageView register;
    String TAG = "Handler";
    String id;

    String userName;
    String userPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        word_text = (TextView) findViewById(R.id.word_text);
        checkId = (EditText) findViewById(R.id.checkId);
        checkPw = (EditText) findViewById(R.id.checkPw);
        register = (ImageView) findViewById(R.id.register);

        checkPw.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        checkPw.setTransformationMethod(PasswordTransformationMethod.getInstance());
        login = (ImageView) findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener() { // 로그인 버튼 클릭시
            @Override
            public void onClick(View v) { // 회원정보 체크

                boolean check = isNetWork();
                //네트워크에 연결이 되어있을 때 실행
                if(check == true) {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // dataSnapshot is the "issue" node with all children with id 0
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (checkId.getText().toString().equals("")) { // 아이디 공백일 시
                                    Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                                    checkId.requestFocus();
                                    return;
                                }
                                if (checkPw.getText().toString().equals("")) { // 비밀번호 공백일 시
                                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                                    checkPw.requestFocus();
                                    return;
                                }
                                userName = snapshot.child("id").getValue().toString();
                                userPw = snapshot.child("pw").getValue().toString();
                                if (userName.equals(checkId.getText().toString()) && userPw.equals(checkPw.getText().toString())) {
                                    id = checkId.getText().toString();
                                    Log.e("TAG", userName);
                                    Log.e("TAG", userPw);

                                    Log.e("TAG", id);
                                    Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), BarActivity.class);

                                    //값 넘기기
                                    intent.putExtra("id", userName);
                                    Bundle bundle = new Bundle(); // 파라미터는 전달할 데이터 개수
                                    bundle.putString("userId", id); // key , value
                                    intent.putExtras(bundle);
                                    //
                                    startActivity(intent);
                                    return;
                                }
                            }
                            Toast.makeText(getApplicationContext(), "존재하지 않는 회원 정보입니다.", Toast.LENGTH_SHORT).show(); // 회원 정보가 없을 경우

                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }else{
                    //네트워크에 연결되어있지 않을경우
                    Toast.makeText(getApplicationContext(), "네트워크에 연결되어있지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }


        });
        try {
            Log.i("tag","피카소 들어옴");
            Picasso.with(this)
                    .load(R.drawable.signup_page)
                    .placeholder(R.drawable.signup_page)
                    .error(R.drawable.signup_page)
                    .resize(450,65)
                    .into(register);
            Picasso.with(this)
                    .load(R.drawable.login_btn)
                    .placeholder(R.drawable.login_btn)
                    .error(R.drawable.login_btn)
                    .resize(870,150)
                    .into(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("users");



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    /*public boolean isNetworkConnected(Context context){
        boolean isConnected = false;

        ConnectivityManager manager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //https://m.blog.naver.com/kyechan99/220600129730
    }*/

    //네트워크 연결 확인
    private Boolean isNetWork(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifiAvailable = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnect = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if ((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)){
            return true;
        }else{
            return false;
        }
    }


}

