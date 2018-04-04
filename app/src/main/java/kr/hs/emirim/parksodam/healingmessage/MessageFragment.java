package kr.hs.emirim.parksodam.healingmessage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.parksodam.healingmessage.adapter.SearchAdapter;
import kr.hs.emirim.parksodam.healingmessage.search.SearchItem;


public class MessageFragment extends BaseFragment {
    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView_m;          // 검색을 보여줄 리스트변수
    private SearchAdapter adapter_m;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    static boolean calledAlerady = false;
    TextView feel;

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//
//        if(!calledAlerady){
//            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//            //다른 인스턴스 보다 먼저 실행되어야한다.
//            calledAlerady = true;
//        }

        View view = inflater.inflate(R.layout.fragment_message, container, false);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        listView_m = (ListView) view.findViewById(R.id.m_listView);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter_m = new SearchAdapter(list, getActivity());
        // 리스트뷰에 아답터를 연결한다.
        listView_m.setAdapter(adapter_m);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("users");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot searchSnapshot : dataSnapshot.getChildren()){
                    String str_n = searchSnapshot.child("name").getValue(String.class);
                    String str_f = searchSnapshot.child("feel").getValue(String.class);
                    list.add(str_n);
                    //Log.e("TAG",list.get);
                }
                adapter_m.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG: ", "Failed to read value", databaseError.toException());
            }
        });



        return view;
    }

    @Override
    public String getTitle() {
        return "메세지";
    }

}
