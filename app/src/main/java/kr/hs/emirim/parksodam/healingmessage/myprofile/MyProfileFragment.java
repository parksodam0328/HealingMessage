package kr.hs.emirim.parksodam.healingmessage.myprofile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.hs.emirim.parksodam.healingmessage.BaseFragment;
import kr.hs.emirim.parksodam.healingmessage.R;


public class MyProfileFragment extends BaseFragment {
    private View view;
    private ImageView received_message;
    private ImageView send_message;
    private ImageView fortune_cookie;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    String[] word = {"삶이 있는 한 희망은 있어 -키케로", "잘했어", "오늘 하루도 수고했어", "힘내!", "오늘은 내일을 위한 첫걸음",
            "진정으로 웃으려면 고통을 참아야하며, 나아가 고통을 즐길 줄 알아야 해 -찰리 채플린", "피할 수 없으면 즐겨라!", "내일은 내일의 태양이 뜬다",
            "넌 말이야 네 생각보다 괜찮은 사람이야", "누가 뭐라하던 너는 너야", "무엇보다 중요한건 네 자신을 사랑하는 거야",
            "넌 꼭 성공할거야", "지금 안된 건 더 좋은 일이 있을거라는거야", "괜찮아", "고개숙이지마, 똑바로 정면으로 바라봐", "우리는 행복하기 위해 세상에 왔어 -류시화",
            "힘들땐 모두 울어서 떠내려보내는 것도 좋아", "내가 너의 이야기를 들어줄게", "걱정마", "너의 나무는 아직 자라는 중이야", "수고했어! 고마워!",
            "힘내 너는 아름다우니까", "불안해 한다는 건 가능성이 있기 때문에 불안한거야 -민주리선생님", "넌 정말 소중한 존재야", "네가 가고자 하는 길은 꽃길일거야",
            "꽃길만 걷게 해 줄게요 -김세정", "너는 멋진 존재야", "난 용감해, 난 당당해, 난 내가 자랑스러워 이게 나야 -this is me", "내 앞에선 다 괜찮아 울어도 돼",
            "고마워", "사랑해", "너는 특별해"};
    int word_random;
    String id;
    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
        received_message = (ImageView)  view.findViewById(R.id.received_message);
        send_message = (ImageView)  view.findViewById(R.id.send_message);
        fortune_cookie = (ImageView)  view.findViewById(R.id.fortune_cookie);
        word_random = (int) (Math.random() * word.length);

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getString("userId");
            Log.e("tag",id);
        } else Log.e("sorry"," null");

        received_message.setOnClickListener(new View.OnClickListener() { // 받은 메세지함으로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReceivedMessage.class);
                startActivity(intent);
            }
        });

        send_message.setOnClickListener(new View.OnClickListener() { // 보낸 메세지함으로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SendMessage.class);
                startActivity(intent);
            }
        });

        // 포춘쿠키는 다이얼로그 띄우기
        fortune_cookie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        getActivity());

                // 제목셋팅
                alertDialogBuilder.setTitle("오늘의 포춘쿠키");

                // AlertDialog 셋팅
                alertDialogBuilder
                        .setMessage(word[word_random]) // 좋은 말 띄우기
                        .setCancelable(false)
                        .setPositiveButton("확인",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 포춘쿠키 확인
                                        getActivity().finish();
                                    }
                                })
                        .setNegativeButton("닫기",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 닫기한다
                                        dialog.cancel();
                                    }
                                });
            }
        });


        return view;
    }

    @Override
    public String getTitle() {
        return "내정보";
    }

}
