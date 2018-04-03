package kr.hs.emirim.parksodam.healingmessage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.parksodam.healingmessage.adapter.SearchAdapter;
import kr.hs.emirim.parksodam.healingmessage.slider.FragmentSlider;
import kr.hs.emirim.parksodam.healingmessage.slider.SliderIndicator;
import kr.hs.emirim.parksodam.healingmessage.slider.SliderPagerAdapter;
import kr.hs.emirim.parksodam.healingmessage.slider.SliderView;

public class HomeFragment extends BaseFragment {

    public HomeFragment() {
        // Required empty public constructor
    }
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;
    private SliderView sliderView;
    private LinearLayout mLinearLayout;
    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView_m;          // 검색을 보여줄 리스트변수
    private SearchAdapter adapter_m;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    static boolean calledAlerady = false;
    TextView feel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        sliderView = (SliderView) view.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.pagesContainer);

        setupSlider();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        listView_m = (ListView) view.findViewById(R.id.listView_h);

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
                    Log.e("name값 불러오기", "성공?");
                    list.add(str_n);
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

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-1.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-2.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-3.jpg"));
        fragments.add(FragmentSlider.newInstance("http://www.menucool.com/slider/prod/image-slider-4.jpg"));

        mAdapter = new SliderPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }



    @Override
    public String getTitle() {
        return "홈";
    }

}
