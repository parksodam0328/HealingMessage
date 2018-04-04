package kr.hs.emirim.parksodam.healingmessage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filterable;
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

    ArrayList<SearchItem> list_data = new ArrayList<>();
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    ListView lv;
    SearchAdapter m_adapter;


    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        list_data = new ArrayList<SearchItem>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        mAuth = FirebaseAuth.getInstance();


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SearchItem value = dataSnapshot.getValue(SearchItem.class); // 괄호 안 : 꺼낼 자료 형태
               list_data.add(value);
                m_adapter.notifyDataSetChanged();
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
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot searchSnapshot : dataSnapshot.getChildren()){
//                    String str_n = searchSnapshot.child("name").getValue(String.class);
//                    String str_f = searchSnapshot.child("feel").getValue(String.class);
//                    list.add(str_n);
//                    //Log.e("TAG",list.get);
//                }
//                adapter_m.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("TAG: ", "Failed to read value", databaseError.toException());
//            }
//        });

        m_adapter = new SearchAdapter(getActivity(), list_data);
        lv = (ListView)view.findViewById(R.id.m_listView);
        lv.setAdapter(m_adapter);
        //lv.setOnItemClickListener(this);


        return view;
    }

    @Override
    public String getTitle() {
        return "메세지";
    }

}
