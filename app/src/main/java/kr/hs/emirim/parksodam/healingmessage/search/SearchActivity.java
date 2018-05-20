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


    ArrayList<SearchItem> result_data = new ArrayList<>();
    private DatabaseReference databaseReference;
  private FirebaseAuth mAuth;

    SearchAdapter adapter;

    private  ArrayList<SearchItem> list_data = new ArrayList<>();
    private ListView lv;
    private SearchAdapter searchAdapter;
    private ArrayList<SearchItem> arrayList;

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


        list_data = new ArrayList<SearchItem>();

        arrayList = new ArrayList<SearchItem>();
        arrayList.addAll(list_data);

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

            adapter = new SearchAdapter(this, list_data);
            lv = (ListView)findViewById(R.id.listView);
            lv.setTextFilterEnabled(true);
            lv.setAdapter(adapter);


        img_btn.setOnClickListener(new View.OnClickListener() {
            int count = 1;
            @Override
            public void onClick(View v) {
                list_data.clear();
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
                    list_data.clear();
                    //파이어베이스 안에 feel 에 있는 happy와 같은 값을 가져온다
                    databaseReference.orderByChild("feel").equalTo("happy").addChildEventListener(new ChildEventListener() {
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
                }
            });

            emo_btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_data.clear();
                    databaseReference.orderByChild("feel").equalTo("sad").addChildEventListener(new ChildEventListener() {
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
                }
            });

            emo_btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_data.clear();
                    databaseReference.orderByChild("feel").equalTo("shyness").addChildEventListener(new ChildEventListener() {
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
                }

            });

            emo_btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list_data.clear();
                    databaseReference.orderByChild("feel").equalTo("angry").addChildEventListener(new ChildEventListener() {
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


                }
            });



            //l

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

