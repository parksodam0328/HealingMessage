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
import com.google.firebase.database.ChildEventListener;
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

    ArrayList<SearchItem> list_data = new ArrayList<>();
    ArrayList<SearchItem> result_data = new ArrayList<>();
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    ListView lv;
    SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


//        if(!calledAlerady){
//            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//            //다른 인스턴스 보다 먼저 실행되어야한다.
//            calledAlerady = true;
//        }
        try {
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


        list_data = new ArrayList<SearchItem>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        mAuth = FirebaseAuth.getInstance();


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SearchItem value = dataSnapshot.getValue(SearchItem.class); // 괄호 안 : 꺼낼 자료 형태
                list_data.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        img_btn.setOnClickListener(new View.OnClickListener() {
            int count = 1;
            @Override
            public void onClick(View v) {
                list_data.clear();
                for(int i = 0;i < list_data.size(); i++) {
                    // 검색된 데이터를 리스트에 추가한다
                    adapter.notifyDataSetChanged();
                    list_data.add(list_data.get(i));
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

            emo_btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    list_data.clear();
//                    for(int i=0;i<list_data.size();i++){ //리스트 데이터 검색
//                        SearchItem item = list_data.get(i);
//                        if(item.getName().equals("Happy")){ //해당 타이틀에 키워드가 검색된다면
//                            result_data.add(item);  //해당 데이터를 검색결과에 추가
//                        }
//                    }
                    for(SearchItem item : result_data){
                        if(item.getFeel().toUpperCase().equals("Happy")){
                            list_data.add(item);
                        }
                    }
                }
            });

            emo_btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_data.clear();
                    for(int i=0;i<list_data.size();i++){ //리스트 데이터 검색
                        SearchItem item = list_data.get(i);
                        if(item.getName().equals("Happy")){ //해당 타이틀에 키워드가 검색된다면
                            result_data.add(item);  //해당 데이터를 검색결과에 추가
                        }
                    }
                }
            });

            emo_btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_data.clear();
                    for(int i=0;i<list_data.size();i++){ //리스트 데이터 검색
                        SearchItem item = list_data.get(i);
                        if(item.getName().equals("Happy")){ //해당 타이틀에 키워드가 검색된다면
                            result_data.add(item);  //해당 데이터를 검색결과에 추가
                        }
                    }
                }
            });

            emo_btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_data.clear();
                    for(int i=0;i<list_data.size();i++){ //리스트 데이터 검색
                        SearchItem item = list_data.get(i);
                        if(item.getName().equals("Happy")){ //해당 타이틀에 키워드가 검색된다면
                            result_data.add(item);  //해당 데이터를 검색결과에 추가
                        }
                    }
                }
            });

            adapter = new SearchAdapter(this, list_data);
            lv = (ListView)findViewById(R.id.listView);
            lv.setTextFilterEnabled(true);
            lv.setAdapter(adapter);

            //l

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

