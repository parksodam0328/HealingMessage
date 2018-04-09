package kr.hs.emirim.parksodam.healingmessage;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< Updated upstream
import android.widget.Button;
=======
>>>>>>> Stashed changes
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.adapter.MessageAdapter;
import kr.hs.emirim.parksodam.healingmessage.adapter.SearchAdapter;


public class MessageFragment extends BaseFragment {

    ArrayList<MessageItem> list_data_message = new ArrayList<>();
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;
    private FirebaseAuth mAuth;
    ListView lv;
    MessageAdapter m_adapter;
    String check;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        list_data_message = new ArrayList<MessageItem>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference1 = database.getReference("message");
        databaseReference2 = database.getReference("users");
        check = databaseReference2.toString();
        mAuth = FirebaseAuth.getInstance();

        Log.e("aaaaa", check);
        databaseReference1.addChildEventListener(new ChildEventListener() {
            @Override
<<<<<<< Updated upstream
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Log.e("ggggggg", check);
                    MessageItem value = dataSnapshot.getValue(MessageItem.class); // 괄호 안 : 꺼낼 자료 형태
                    list_data_message.add(value);
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

=======
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot searchSnapshot : dataSnapshot.getChildren()){
                    String str_n = searchSnapshot.child("name").getValue(String.class);
                    String str_f = searchSnapshot.child("feel").getValue(String.class);
                    Log.e("name값 불러오기", "성공?");
                    list.add(str_n);
                    list.add(str_f);
                }
                adapter_m.notifyDataSetChanged();
>>>>>>> Stashed changes
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

<<<<<<< Updated upstream
        m_adapter = new MessageAdapter(getActivity(), list_data_message);
        lv = (ListView)view.findViewById(R.id.m_listView);
        lv.setAdapter(m_adapter);


=======
>>>>>>> Stashed changes
        return view;
    }

    @Override
    public String getTitle() {
        return "메세지";
    }

}
