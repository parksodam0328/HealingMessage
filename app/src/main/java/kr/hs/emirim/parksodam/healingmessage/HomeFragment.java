package kr.hs.emirim.parksodam.healingmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import kr.hs.emirim.parksodam.healingmessage.adapter.HomeAdapter;
import kr.hs.emirim.parksodam.healingmessage.search.SearchActivity;
import kr.hs.emirim.parksodam.healingmessage.search.SearchItem;
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

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    ArrayList<SearchItem> list_data_home = new ArrayList<>();
    ArrayList<SearchItem> result_data = new ArrayList<>();
    ListView lv;
    HomeAdapter h_adapter;

    private String id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        sliderView = (SliderView) view.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.pagesContainer);
        setupSlider();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("users");
        mAuth = FirebaseAuth.getInstance();

        list_data_home = new ArrayList<SearchItem>();

        FloatingActionButton search_tab = (FloatingActionButton) view.findViewById(R.id.search_tab);

        search_tab.setOnClickListener(new View.OnClickListener() { // 검색창으로 이동
            @Override
            public void onClick(View v) {
                //onMyListener.onReceivedData(id);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SearchItem value = dataSnapshot.getValue(SearchItem.class); // 괄호 안 : 꺼낼 자료 형태
                list_data_home.add(value);
                h_adapter.notifyDataSetChanged();
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

        h_adapter = new HomeAdapter(getActivity(), list_data_home);
        lv = (ListView)view.findViewById(R.id.h_listView);
        lv.setAdapter(h_adapter);
        
        return view;
    }

    private void setupSlider() {
        sliderView.setDurationScroll(900);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance(R.drawable.s1));
        fragments.add(FragmentSlider.newInstance(R.drawable.s2));
        fragments.add(FragmentSlider.newInstance(R.drawable.s3));
        fragments.add(FragmentSlider.newInstance(R.drawable.s4));
        fragments.add(FragmentSlider.newInstance(R.drawable.s5));

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
