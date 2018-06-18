package kr.hs.emirim.parksodam.healingmessage.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import kr.hs.emirim.parksodam.healingmessage.BarActivity;
import kr.hs.emirim.parksodam.healingmessage.R;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText editId;
    private EditText editPw;
    private EditText editName;
    private ImageView backimg;
    private ImageView check;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        editId = (EditText) findViewById(R.id.id);
        editPw = (EditText) findViewById(R.id.pw);
        editName = (EditText) findViewById(R.id.nickname);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //툴바 설정
        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.
        backimg = (ImageView) findViewById(R.id.back_icon);
        backimg.setOnClickListener(new View.OnClickListener() { // 뒤로 가기
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        editPw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editPw.setTransformationMethod(PasswordTransformationMethod.getInstance());

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        check = (ImageView) findViewById(R.id.submit);
        check.setOnClickListener(new View.OnClickListener() {   //가입버튼누르면
            @Override
            public void onClick(View v) {
                final String name = editName.getText().toString();
                String email = editId.getText().toString();
                String password = editPw.getText().toString();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> uploadTask) {
                            if (uploadTask.isSuccessful()) {

                                final String user_id = mAuth.getCurrentUser().getUid();
                                String token_id = FirebaseInstanceId.getInstance().getToken();

                                Map<String, Object> userMap = new HashMap<>();
                                userMap.put("name", name);
                                userMap.put("token_id", token_id);

                                mFirestore.collection("Users").document(user_id).set(userMap);
                                sendToMain();
                            }
                        }
                    });
                }
            }
        });

        try {
            Log.i("tag", "피카소 들어옴");
            Picasso.with(this)
                    .load(R.drawable.back_icon2)
                    .placeholder(R.drawable.back_icon2)
                    .error(R.drawable.back_icon2)
                    .resize(50, 70)
                    .into(backimg);
            Picasso.with(this)
                    .load(R.drawable.signup_btn)
                    .placeholder(R.drawable.signup_btn)
                    .error(R.drawable.signup_btn)
                    .resize(860, 150)
                    .into(check);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendToMain() {
        Intent mainIntent = new Intent(RegisterActivity.this, BarActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
