package kr.hs.emirim.parksodam.healingmessage.myprofile;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import kr.hs.emirim.parksodam.healingmessage.BaseFragment;
import kr.hs.emirim.parksodam.healingmessage.R;

import static kr.hs.emirim.parksodam.healingmessage.R.drawable.pro_img;


public class MyProfileFragment extends BaseFragment {
    private View view;
    private ImageView fortune_img;
    private ImageView send_message_img;
    private ImageView setting_img;
    private ImageView logout_img;
    private ImageView profile;
    private TextView prof_name;
    private TextView prof_feel;
    private DatabaseReference databaseReference1;
    private FirebaseAuth mAuth;
    private String id;
    private String name;

    String[] word = {"삶이 있는 한 희망은 있어 -키케로", "잘했어", "오늘 하루도 수고했어", "힘내!", "오늘은 내일을 위한 첫걸음",
            "진정으로 웃으려면 고통을 참아야하며, 나아가 고통을 즐길 줄 알아야 해 -찰리 채플린", "피할 수 없으면 즐겨라!", "내일은 내일의 태양이 뜬다",
            "넌 말이야 네 생각보다 괜찮은 사람이야", "누가 뭐라하던 너는 너야", "무엇보다 중요한건 네 자신을 사랑하는 거야",
            "넌 꼭 성공할거야", "지금 안된 건 더 좋은 일이 있을거라는거야", "괜찮아", "고개숙이지마, 똑바로 정면으로 바라봐", "우리는 행복하기 위해 세상에 왔어 -류시화",
            "힘들땐 모두 울어서 떠내려보내는 것도 좋아", "내가 너의 이야기를 들어줄게", "걱정마", "너의 나무는 아직 자라는 중이야", "수고했어! 고마워!",
            "힘내 너는 아름다우니까", "불안해 한다는 건 가능성이 있기 때문에 불안한거야 -민주리선생님", "넌 정말 소중한 존재야", "네가 가고자 하는 길은 꽃길일거야",
            "꽃길만 걷게 해 줄게요 -김세정", "너는 멋진 존재야", "난 용감해, 난 당당해, 난 내가 자랑스러워 이게 나야 -this is me", "내 앞에선 다 괜찮아 울어도 돼",
            "고마워", "사랑해", "너는 특별해", "손이 타버릴 듯 뜨거울 지라도, 담고싶은 태양이 있다면 절대로 놓지 말 것", "시작하는 모든 존재는 늘 아프고 불안하다. 하지만 기억하라 그대는 눈부시게 아름답다",
            "나는 멋지다, 나는 멋지다, 나는 멋지다 - 스피릿핑거스", "때론 당신의 인생에서 엉뚱한 친절과 정신나간 선행을 실천해보라", "진정으로 나 자신의 것이 아닌 것은 과감하게 버려야 합니다"};
    int word_random;
    public MyProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        Bundle bundle = getActivity().getIntent().getExtras(); // 액티비티에서 넘어오는 값 받기
        /*fortune_cookie = (ImageView)  view.findViewById(R.id.fortune_cookie);
        setting = (ImageView)  view.findViewById(R.id.setting);
        howto = (ImageView)  view.findViewById(R.id.howto);*/

        fortune_img = (ImageView) view.findViewById(R.id.fortune);
        send_message_img = (ImageView) view.findViewById(R.id.message);
        setting_img = (ImageView) view.findViewById(R.id.profile);
        logout_img = (ImageView) view.findViewById(R.id.logout);
        profile = (ImageView) view.findViewById(R.id.profile_image);
        try {
            Picasso.with(getActivity())
                    .load(pro_img)
                    .placeholder(pro_img)
                    .error(pro_img)
                    .resize(410, 400)
                    .centerCrop()
                    .into(profile);
            Picasso.with(getActivity())
                    .load(R.drawable.fortune)
                    .placeholder(R.drawable.fortune)
                    .error(R.drawable.fortune)
                    .resize(900, 150)
                    .centerCrop()
                    .into(fortune_img);
            Picasso.with(getActivity())
                    .load(R.drawable.message)
                    .placeholder(R.drawable.message)
                    .error(R.drawable.message)
                    .resize(900, 150)
                    .centerCrop()
                    .into(send_message_img);
            Picasso.with(getActivity())
                    .load(R.drawable.profile)
                    .placeholder(R.drawable.profile)
                    .error(R.drawable.profile)
                    .resize(900, 150)
                    .centerCrop()
                    .into(setting_img);
            Picasso.with(getActivity())
                    .load(R.drawable.logout)
                    .placeholder(R.drawable.logout)
                    .error(R.drawable.logout)
                    .resize(900, 150)
                    .centerCrop()
                    .into(logout_img);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (bundle != null) {
            id = bundle.getString("userId");
            Log.e("여기는 프로필id입니다....",id);

            databaseReference1 = FirebaseDatabase.getInstance().getReference("users/" + id);

            prof_name = (TextView)view.findViewById(R.id.prof_name);
            prof_feel = (TextView)view.findViewById(R.id.prof_feel);


            databaseReference1.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if(dataSnapshot.getKey().equals("name")){
                        prof_name.setText(dataSnapshot.getValue().toString());
                        Log.e("프로필에 닉네임이 들어갑니당", dataSnapshot.getValue().toString());
                    }

                    if(dataSnapshot.getKey().equals("feel")){
                        prof_feel.setText(dataSnapshot.getValue().toString());
                        Log.e("프로필에 감정!", dataSnapshot.getValue().toString());
                    }
                   // Log.e("jhi", "Value is: ");
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


            //포춘쿠키 다이얼로그 띄우기
            fortune_img.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(getActivity());

                    // 제목셋팅
                    alert.setTitle("오늘의 포춘쿠키");

                    for(int i = 0; i< word.length; i++){
                        word_random = (int) (Math.random() * word.length);
                    }
                    Log.e("피치퍼펙트재밋다..", word[word_random]);
                    alert.setMessage(word[word_random]) // 좋은 말 띄우기
                            .setIcon(R.drawable.fortune)
                            .setCancelable(false)
                            .setPositiveButton("확인",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            // 포춘쿠키 확인

                                        }
                                    });


                    alert .setNegativeButton("닫기",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialog, int id) {
                                    // 다이얼로그를 닫기한다
                                    dialog.cancel();
                                }
                            });

                    alert.show();
                }
            });


    } else Log.e("sorry"," null");
        return view;
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMyListener){
            onMyListener = (OnMyListener) context;
        }else throw new RuntimeException(context+" must implements OnMyListenter");
    }


    @Override
    public void onDetach() {
        super.onDetach();
        onMyListener=null;
    }*/

    @Override
    public String getTitle() {
        return "내정보";
    }

}
