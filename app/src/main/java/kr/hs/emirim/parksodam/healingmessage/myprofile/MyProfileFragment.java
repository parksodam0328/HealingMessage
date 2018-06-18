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

        import com.bumptech.glide.Glide;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.firestore.DocumentSnapshot;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.squareup.picasso.Picasso;

        import kr.hs.emirim.parksodam.healingmessage.BarActivity;
        import kr.hs.emirim.parksodam.healingmessage.BaseFragment;
        import kr.hs.emirim.parksodam.healingmessage.CustomDialog;
        import kr.hs.emirim.parksodam.healingmessage.CustomDialog_logout;
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
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
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
        //Bundle bundle = getActivity().getIntent().getExtras(); // 액티비티에서 넘어오는 값 받기
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
        mAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
        prof_name = (TextView)view.findViewById(R.id.prof_name);
        prof_feel = (TextView)view.findViewById(R.id.prof_feel);

        id = mAuth.getCurrentUser().getUid();
        mFirestore.collection("Users").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String user_name = documentSnapshot.getString("name");
                String feel = documentSnapshot.getString("feel");

                prof_name.setText(user_name);
                prof_feel.setText(feel);

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
                customDialog_setting.callDialog();

            }
        });

        logout_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //커스텀 다이얼로그를 생성,
                CustomDialog_logout customDialog_logout = new CustomDialog_logout(getActivity());
                customDialog_logout.callDialog();

            }
        });
        return view;
    }

    @Override
    public String getTitle() {
        return "내정보";
    }
}

