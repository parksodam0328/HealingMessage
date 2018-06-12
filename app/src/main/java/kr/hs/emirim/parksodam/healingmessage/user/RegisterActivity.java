package kr.hs.emirim.parksodam.healingmessage.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import kr.hs.emirim.parksodam.healingmessage.R;

public class RegisterActivity extends AppCompatActivity {
        private DatabaseReference mDatabase;
        private EditText editId;
        private EditText editPw;
        private EditText editName;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            editId = (EditText)findViewById(R.id.id);
            editPw = (EditText)findViewById(R.id.pw);
            editName = (EditText)findViewById(R.id.nickname);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //툴바 설정
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        ImageView backimg = (ImageView) findViewById(R.id.back_icon);
        backimg.setOnClickListener(new View.OnClickListener() { // 뒤로 가기
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        editPw.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
        editPw.setTransformationMethod(PasswordTransformationMethod.getInstance());

        getSupportActionBar().setDisplayShowTitleEnabled(false);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            ImageView check = (ImageView) findViewById(R.id.submit);
            check.setOnClickListener(new View.OnClickListener() {   //가입버튼누르면
                @Override
                public void onClick(View v) {
                    if (editId.getText().toString().length() > 8) {    //아이디 길이 판별
                        Toast.makeText(RegisterActivity.this, "아이디 길이는 8자 이내입니다.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (editPw.getText().toString().length() > 16) {    //비밀번호 길이 판별
                            Toast.makeText(RegisterActivity.this, "비밀번호 길이는 16자 이내입니다.", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            if (!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", editId.getText().toString())) {  //아이디 형식 판별  //숫자가 아이디의 첫문자가 되면 안됌
                                Toast.makeText(RegisterActivity.this, "아이디 형식을 지켜주세요.", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                if (!Pattern.matches("^[a-zA-Z][a-zA-Z0-9]*$", editPw.getText().toString())) {  //비밀번호 형식 판별  //숫자가 비밀번호의 첫문자가 되면 안됌
                                    Toast.makeText(RegisterActivity.this, "비밀번호 형식을 지켜주세요.", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    writeNewUser(editId.getText().toString(), editPw.getText().toString(), editName.getText().toString());     //회원가입 완료
                                    //mDatabase.child("users").child().setValue(editPw);
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    Toast.makeText(RegisterActivity.this, editName.getText().toString() + "님 회원가입되었습니다", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }
                });
            }

    private void writeNewUser(String userId, String pw, String name) {
        User user = new User(userId, pw, name);

        mDatabase.child("users").child(userId).setValue(user);
    }

    }
