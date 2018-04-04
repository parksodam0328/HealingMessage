package kr.hs.emirim.parksodam.healingmessage.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.adapter.SearchAdapter;

/**
 * Created by 민경 on 2018-03-16.
 */

public class SearchActivity extends AppCompatActivity {

    ImageButton emo_btn1;
    ImageButton emo_btn2;
    ImageButton emo_btn3;
    ImageButton emo_btn4;

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    SearchItem sItem = new SearchItem();
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    static boolean calledAlerady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        if(!calledAlerady){
//            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//            //다른 인스턴스 보다 먼저 실행되어야한다.
//            calledAlerady = true;
//        }
        //초기화
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final ImageButton img_btn = (ImageButton)findViewById(R.id.search_btn);
        emo_btn1 = (ImageButton)findViewById(R.id.emo_btn1);
        emo_btn2 = (ImageButton)findViewById(R.id.emo_btn2);
        emo_btn3 = (ImageButton)findViewById(R.id.emo_btn3);
        emo_btn4 = (ImageButton)findViewById(R.id.emo_btn4);

        emo_btn1.setVisibility(View.INVISIBLE);
        emo_btn2.setVisibility(View.INVISIBLE);
        emo_btn3.setVisibility(View.INVISIBLE);
        emo_btn4.setVisibility(View.INVISIBLE);


        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();


        img_btn.setOnClickListener(new View.OnClickListener() {
            int count = 1;
            @Override
            public void onClick(View v) {
                list.clear();
                for(int i = 0;i < arraylist.size(); i++) {
                    // 검색된 데이터를 리스트에 추가한다
                    adapter.notifyDataSetChanged();
                    list.add(arraylist.get(i));
                }

                count++;
                if(count % 2 == 0){
                    emo_btn1.setVisibility(View.VISIBLE);
                    emo_btn2.setVisibility(View.VISIBLE);
                    emo_btn3.setVisibility(View.VISIBLE);
                    emo_btn4.setVisibility(View.VISIBLE);
                    count = 0;
                }
                else{
                    emo_btn1.setVisibility(View.INVISIBLE);
                    emo_btn2.setVisibility(View.INVISIBLE);
                    emo_btn3.setVisibility(View.INVISIBLE);
                    emo_btn4.setVisibility(View.INVISIBLE);
                }

            }
        });

        listView = (ListView) findViewById(R.id.listView);

        try {
            // 리스트를 생성한다.
            list = new ArrayList<String>();

            // 검색에 사용할 데이터을 미리 저장한다.
            settingList();

            // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
            arraylist = new ArrayList<String>();
            arraylist.addAll(list);

            // 리스트에 연동될 아답터를 생성한다.
            adapter = new SearchAdapter(list, this);

            // 리스트뷰에 아답터를 연결한다.
            listView.setAdapter(adapter);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseRef = database.getReference("users");

            databaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot searchSnapshot : dataSnapshot.getChildren()) {
                        String str1 = searchSnapshot.child("name").getValue(String.class);
                        Log.e("name값 불러오기", "성공?");
                        String str2 = searchSnapshot.child("feel").getValue(String.class);
                        Log.e("feel값 불러오기", "???");

                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("TAG: ", "Failed to read value", databaseError.toException());
                }
            });

            emo_btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.clear();
                    for (int i = 0; i < arraylist.size(); i++) {
                        //happy
                        if (arraylist.get(i).toLowerCase().equals("Happy")) {
                            // 검색된 데이터를 리스트에 추가한다
                            adapter.notifyDataSetChanged();
                            list.add(arraylist.get(i));
                        }
                    }
                }
            });

            emo_btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.clear();
                    for (int i = 0; i < arraylist.size(); i++) {
                        //happy
                        if (arraylist.get(i).toLowerCase().equals("Sad")) {
                            // 검색된 데이터를 리스트에 추가한다
                            adapter.notifyDataSetChanged();
                            list.add(arraylist.get(i));
                        }
                    }
                }
            });

            emo_btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.clear();
                    for (int i = 0; i < arraylist.size(); i++) {
                        //happy
                        if (arraylist.get(i).toLowerCase().equals("Angry")) {
                            // 검색된 데이터를 리스트에 추가한다
                            adapter.notifyDataSetChanged();
                            list.add(arraylist.get(i));
                        }
                    }
                }
            });

            emo_btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.clear();
                    for (int i = 0; i < arraylist.size(); i++) {
                        //happy
                        if (arraylist.get(i).toLowerCase().equals("Shyness")) {
                            // 검색된 데이터를 리스트에 추가한다
                            adapter.notifyDataSetChanged();
                            list.add(arraylist.get(i));
                        }
                    }
                }
            });
        }catch (Exception e){

        }
    }
    //검색에 사용될 데이터를 리스트에 추가한다.
    //test
    private void settingList(){



//        list.add("슬플때");
//        list.add("행복할때");
//        list.add("우울할때");
//        list.add("그럭저럭일때");
    }

}

