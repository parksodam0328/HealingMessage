package kr.hs.emirim.parksodam.healingmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
        private DatabaseReference mDatabase;
        private EditText editId;
        private EditText editPw;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            editId = (EditText)findViewById(R.id.id);
            editPw = (EditText)findViewById(R.id.pw);
            //초기화
            Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);

            //툴바 설정
            setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
            ImageView backimg = (ImageView) findViewById(R.id.back_icon);
            backimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            getSupportActionBar().setDisplayShowTitleEnabled(false);


            mDatabase = FirebaseDatabase.getInstance().getReference();
            Button check = (Button) findViewById(R.id.submit);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    writeNewUser(editId.getText().toString(), editPw.getText().toString());
                    //mDatabase.child("users").child().setValue(editPw);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

                }
            });
        }

    private void writeNewUser(String userId, String pw) {
        User user = new User(userId, pw);

        mDatabase.child("users").child(userId).setValue(user);
    }

    }
