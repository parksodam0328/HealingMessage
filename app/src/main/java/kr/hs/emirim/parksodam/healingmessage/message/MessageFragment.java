package kr.hs.emirim.parksodam.healingmessage.message;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import kr.hs.emirim.parksodam.healingmessage.BaseFragment;
import kr.hs.emirim.parksodam.healingmessage.CustomDialog;
import kr.hs.emirim.parksodam.healingmessage.CustomDialog_detail;
import kr.hs.emirim.parksodam.healingmessage.R;
import kr.hs.emirim.parksodam.healingmessage.adapter.MessageAdapter;
import kr.hs.emirim.parksodam.healingmessage.search.SearchActivity;


public class MessageFragment extends BaseFragment {

    ArrayList<R_User> list_data_message = new ArrayList<>();
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;
    ListView lv;
    private String id, name;
    String i="mesaasge";
    MessageAdapter m_adapter;
    int cnt=1;

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
/*

            Bundle bundle = getArguments();
            final String user = bundle.getString("id_re");
*/


        View view = inflater.inflate(R.layout.fragment_message, container, false);
        list_data_message = new ArrayList<R_User>();

        FloatingActionButton search_tab = (FloatingActionButton) view.findViewById(R.id.search_tab);

        search_tab.setOnClickListener(new View.OnClickListener() { // 검색창으로 이동
            @Override
            public void onClick(View v) {
                //onMyListener.onReceivedData(id);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        databaseReference2 = FirebaseDatabase.getInstance().getReference("users/" + id);
        databaseReference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals("name")){
                    name = dataSnapshot.getValue().toString();
                    Log.e("이읆읆ㅇ나르망ㄹ는ㄻ", name);
                    databaseReference1 = FirebaseDatabase.getInstance().getReference("message/"+name);
                    //databaseReference1 = database.getReference("message");

                    databaseReference1.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            Log.e("TAG", String.valueOf(dataSnapshot.getValue()));
                            //list_data_message.clear();
                            //Log.e("여기서 값을 넣습니다", id);
                            R_User value = dataSnapshot.getValue(R_User.class); // 괄호 안 : 꺼낼 자료 형태

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

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
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

        //아이템 클릭시 작동(세부정보 보여주기)
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               /* showAlertDialog(list_data_message.get(position).getName(), list_data_message.get(position).getContext());*/
        /*        Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("name", list_data_message.get(position).getName());
                intent.putExtra("context", list_data_message.get(position).getContext());

                startActivity(intent);*/

                CustomDialog_detail customDialog = new CustomDialog_detail(getActivity());
                customDialog.callDialog(list_data_message.get(position).getName(), list_data_message.get(position).getContext());
            }
        });

        return view;
    }




    @Override
    public String getTitle() {
        return "메세지";
    }

}