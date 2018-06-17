package kr.hs.emirim.parksodam.healingmessage.myprofile;

        import android.content.DialogInterface;
        import android.graphics.drawable.ShapeDrawable;
        import android.graphics.drawable.shapes.OvalShape;
        import android.os.Build;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.squareup.picasso.Picasso;

        import kr.hs.emirim.parksodam.healingmessage.BaseFragment;
        import kr.hs.emirim.parksodam.healingmessage.CustomDialog;
        import kr.hs.emirim.parksodam.healingmessage.CustomDialog_setting;
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
    private int count = 0;

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


            count = 1;

            //포춘쿠키 커스텀 다이얼로그 띄우기
            fortune_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //커스텀 다이얼로그를 생성,
                  CustomDialog customDialog = new CustomDialog(getActivity());
                    customDialog.callDialog();
                 /*   //한번만 호출가능
                if(count == 1) {
                    //커스텀 다이얼로그 호출,
                    customDialog.callDialog();
                    count = 0;
                }else if(count == 0){
                    Toast.makeText(getContext(), "오늘의 포춘쿠키를 확인하셨습니다!", Toast.LENGTH_SHORT).show();
                }*/
                }
            });

            //정보설정 커스텀 다이얼로그 띄우기
            setting_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //커스텀 다이얼로그를 생성,
                    CustomDialog_setting customDialog_setting = new CustomDialog_setting(getActivity());
                    customDialog_setting.callDialog(id);

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
