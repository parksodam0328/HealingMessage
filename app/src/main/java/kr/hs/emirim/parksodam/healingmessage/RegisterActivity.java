package kr.hs.emirim.parksodam.healingmessage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Iterator;

    public class RegisterActivity extends AppCompatActivity {
        private DatabaseReference databaseReference;
        private EditText editEmail;
        private ValueEventListener checkRegister = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                while (child.hasNext()) {
                    if (editEmail.getText().toString().equals(child.next().getKey())) {
                        Toast.makeText(getApplicationContext(), "존재하는 아이디 입니다.", Toast.LENGTH_LONG).show();
                        databaseReference.removeEventListener(this);
                        return;
                    }
                }
                makeNewId();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        private FirebaseAuth auth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

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


            databaseReference = FirebaseDatabase.getInstance().getReference("users");
            editEmail = (EditText) findViewById(R.id.email);
            Button check = (Button) findViewById(R.id.submit);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    databaseReference.addListenerForSingleValueEvent(checkRegister);
                }
            });
        }

        void makeNewId() {
            Date date = new Date(System.currentTimeMillis());
            databaseReference.child(editEmail.getText().toString()).child("가입일").setValue(date.toString());
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
        }

    }
