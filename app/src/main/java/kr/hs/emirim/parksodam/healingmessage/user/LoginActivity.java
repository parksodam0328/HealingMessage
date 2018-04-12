
package kr.hs.emirim.parksodam.healingmessage.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kr.hs.emirim.parksodam.healingmessage.BarActivity;
import kr.hs.emirim.parksodam.healingmessage.R;


public class LoginActivity extends AppCompatActivity {
    String[] word = {"삶이 있는 한 희망은 있어 -키케로", "잘했어", "오늘 하루도 수고했어", "힘내!", "오늘은 내일을 위한 첫걸음",
            "진정으로 웃으려면 고통을 참아야하며, 나아가 고통을 즐길 줄 알아야 해 -찰리 채플린", "피할 수 없으면 즐겨라!", "내일은 내일의 태양이 뜬다",
            "넌 말이야 네 생각보다 괜찮은 사람이야", "누가 뭐라하던 너는 너야", "무엇보다 중요한건 네 자신을 사랑하는 거야",
            "넌 꼭 성공할거야", "지금 안된 건 더 좋은 일이 있을거라는거야", "괜찮아", "고개숙이지마, 똑바로 정면으로 바라봐", "우리는 행복하기 위해 세상에 왔어 -류시화",
            "힘들땐 모두 울어서 떠내려보내는 것도 좋아", "내가 너의 이야기를 들어줄게", "걱정마", "너의 나무는 아직 자라는 중이야", "수고했어! 고마워!",
            "힘내 너는 아름다우니까", "불안해 한다는 건 가능성이 있기 때문에 불안한거야 -민주리선생님", "넌 정말 소중한 존재야", "네가 가고자 하는 길은 꽃길일거야",
            "꽃길만 걷게 해 줄게요 -김세정", "너는 멋진 존재야", "난 용감해, 난 당당해, 난 내가 자랑스러워 이게 나야 -this is me", "내 앞에선 다 괜찮아 울어도 돼",
            "고마워", "사랑해", "너는 특별해"};

    private DatabaseReference databaseReference;
    //int word_random;
    TextView word_text;
    Handler handler = new Handler();
    EditText checkId;
    EditText checkPw;
    Button login;
    Button register;
    String TAG = "Handler";
    String id;

    private FirebaseAuth mAuth;
    String userName;
    String userPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        word_text = (TextView) findViewById(R.id.word_text);
        checkId = (EditText) findViewById(R.id.checkId);
        checkPw = (EditText) findViewById(R.id.checkPw);
//        word_text.setText(word[word_random]);


        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        login = (Button) findViewById(R.id.login);
            login.setOnClickListener(new View.OnClickListener() { // 로그인 버튼 클릭시
                @Override
                public void onClick(View v) {

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) { // 회원정보 체크
                                // dataSnapshot is the "issue" node with all children with id 0
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    if (checkId.getText().toString().equals("")) {
                                        Toast.makeText(getApplicationContext(), "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                                        checkId.requestFocus();
                                        return;
                                    }
                                    if (checkPw.getText().toString().equals("")) {
                                        Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                                        checkPw.requestFocus();
                                        return;
                                    }
                                    userName = snapshot.child("id").getValue().toString();
                                    userPw = snapshot.child("pw").getValue().toString();
                                    if (userName.equals(checkId.getText().toString()) && userPw.equals(checkPw.getText().toString())) {
                                        id = checkId.getText().toString();
                                        Log.e("TAG",userName);
                                        Log.e("TAG",userPw);

                                        Log.e("TAG", id);
                                        Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), BarActivity.class);
                                        //값 넘기기
                                        intent.putExtra("id",userName);
                                        Bundle bundle = new Bundle(); // 파라미터는 전달할 데이터 개수
                                        bundle.putString("userId", id); // key , value
                                        intent.putExtras(bundle);
                                        //
                                        startActivity(intent);
                                        return;
                                    }
                                }
                            Toast.makeText(getApplicationContext(), "존재하지 않는 회원정보입니다.", Toast.LENGTH_SHORT).show();
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


            });



        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() { // 화원이 아닐 시 회원가입 액티비티로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.e(TAG,"들어옴");
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                for (;;) { //int i = 0; i < 3; i++
//                    word_random = (int) (Math.random() * 32);
//                    try {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//
//                                word_text.setText(word[word_random]);
//                                Log.e(TAG, word[word_random]);
//                                //word_text.setText("");
//                            }
//                        });
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread.setDaemon(true);
//        thread.start();
//    }
//
//    @Override public void onStop() {
//        super.onStop();
//
//        }


}

