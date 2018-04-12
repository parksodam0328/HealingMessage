package kr.hs.emirim.parksodam.healingmessage;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.adapter.MessageAdapter;
import kr.hs.emirim.parksodam.healingmessage.message.MessageItem;


public class MessageFragment extends BaseFragment {

    ArrayList<MessageItem> list_data_message = new ArrayList<>();
    private DatabaseReference databaseReference1;
    private FirebaseAuth mAuth;
    ListView lv;
    private String id;
    MessageAdapter m_adapter;






    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("userId");
            Log.e("tag",id);
        } else Log.e("sorry"," null");



            Bundle bundle = getArguments();
            final String user = bundle.getString("id_re");


        View view = inflater.inflate(R.layout.fragment_message, container, false);
        list_data_message = new ArrayList<MessageItem>();



<<<<<<< Updated upstream
        databaseReference1 = FirebaseDatabase.getInstance().getReference("message").child(id);
=======
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference1 = database.getReference("message");
>>>>>>> Stashed changes
        mAuth = FirebaseAuth.getInstance();

        databaseReference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
<<<<<<< Updated upstream
                list_data_message.clear();
                Log.e("여기서 값을 넣습니다", id);
                Log.e("TAG", String.valueOf(dataSnapshot.getValue()));
                MessageItem value = dataSnapshot.getValue(MessageItem.class); // 괄호 안 : 꺼낼 자료 형태
                list_data_message.add(value);
//                m_adapter.notifyDataSetChanged();
=======
                String userName = dataSnapshot.getValue().toString();
                String nn = (String) dataSnapshot.child(user).child("id").getValue();
                Log.e("hihihi", "들어왔따!");
                if(nn.equals(user)) {
                    Log.e("hihihi!!!!!!", "들어왔따!");
                    MessageItem value = dataSnapshot.getValue(MessageItem.class); // 괄호 안 : 꺼낼 자료 형태
                    list_data_message.add(value);
                    m_adapter.notifyDataSetChanged();
                }
>>>>>>> Stashed changes
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

        m_adapter = new MessageAdapter(getActivity(), list_data_message);
        lv = (ListView)view.findViewById(R.id.m_listView);
        lv.setAdapter(m_adapter);


        return view;
    }

    @Override
    public String getTitle() {
        return "메세지";
    }

}