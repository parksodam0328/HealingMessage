package kr.hs.emirim.parksodam.healingmessage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.hs.emirim.parksodam.healingmessage.adapter.FragmentPagerAdapter;
import kr.hs.emirim.parksodam.healingmessage.myprofile.MyProfileFragment;
import kr.hs.emirim.parksodam.healingmessage.search.SearchActivity;


public class BarActivity extends AppCompatActivity {

    public ViewPager vp;
    public TabLayout layout;
    //public FirebaseDatabase mDatabase;
    private String TAG ="myreceiver : ";
    private Toolbar toolbar;
    private TextView myTitleText;
    private ImageView mSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        //툴바 설정
        //FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myTitleText = (TextView) findViewById(R.id.title);
        myTitleText.setText("힐링쪽지");
        mSearch = (ImageView)findViewById(R.id.search);

        setSupportActionBar(toolbar); //툴바를 액션바와 같게 만들어 준다.getSupportActionBar().setTitle("지도");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

            vp = (ViewPager) findViewById(R.id.vp_main);
        setupViewPager(vp);
        layout = (TabLayout) findViewById(R.id.tl_main);
        layout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF")); // 밑줄색
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout));
        layout.setupWithViewPager(vp);

        for (int i = 0; i < 4; i++) {
            Log.e("", String.valueOf(layout.getTabCount()));
            if(i==0) {
                layout.getTabAt(i).setText("홈").setIcon(R.drawable.home_click);
                getSupportActionBar().setTitle("힐링쪽지");
            }
            else if(i==1) {
                layout.getTabAt(i).setText("메세지").setIcon(R.drawable.message);
                getSupportActionBar().setTitle("힐링쪽지");
            }
            else if(i==2) {
                layout.getTabAt(i).setText("내정보").setIcon(R.drawable.profile);
                getSupportActionBar().setTitle("힐링쪽지");
            }
            else if(i==3) {
                layout.getTabAt(i).setText("도움말").setIcon(R.drawable.settings);
                getSupportActionBar().setTitle("힐링쪽지");
            }
        }
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    myTitleText.setText("홈");
                } else if (position == 1)
                {
                    myTitleText.setText("메세지");
                }
                else if (position == 2)
                {
                    myTitleText.setText("내정보");
                }
                else if (position == 3){
                    myTitleText.setText("도움말");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition()==0) {
                    myTitleText.setText("홈");
                    tab.setIcon(R.drawable.home_click);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#000076"));
                }

                else if(tab.getPosition()==1) {
                    myTitleText.setText("메세지");
                    tab.setIcon(R.drawable.message_click);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#000076"));
                }
                else if(tab.getPosition()==2) {
                    myTitleText.setText("내정보");
                    tab.setIcon(R.drawable.profile_click);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#000076"));
                }
                else if(tab.getPosition()==3) {
                    myTitleText.setText("도움말");
                    tab.setIcon(R.drawable.settings_click);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#000076"));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    myTitleText.setText("홈");
                    tab.setIcon(R.drawable.home);
                    layout.setTabTextColors(Color.parseColor("#000076"), Color.parseColor("#666666"));
                }
                else if(tab.getPosition()==1) {
                    myTitleText.setText("메세지");
                    tab.setIcon(R.drawable.message);
                    layout.setTabTextColors(Color.parseColor("#000076"), Color.parseColor("#666666"));
                }
                else if(tab.getPosition()==2) {
                    myTitleText.setText("내정보");
                    tab.setIcon(R.drawable.profile);
                    layout.setTabTextColors(Color.parseColor("#000076"), Color.parseColor("#666666"));
                }
                else if(tab.getPosition()==3) {
                    myTitleText.setText("도움말");
                    tab.setIcon(R.drawable.settings);
                    layout.setTabTextColors(Color.parseColor("#000076"), Color.parseColor("#666666"));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() != vp.getCurrentItem()) {
                    vp.setCurrentItem(tab.getPosition());
                }
            }
        });


//        mDatabase = FirebaseDatabase.getInstance();
//        //mDatabase.setPersistenceEnabled(true);
//
//        IntentFilter filter = new IntentFilter("fcm");
//        kr.hs.emirim.parksodam.mirimbreadclock2.notice.MyReceiver receiver = new kr.hs.emirim.parksodam.mirimbreadclock2.notice.MyReceiver();
//        registerReceiver(receiver, filter);

    }

    private void setupViewPager(ViewPager vp) {
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "홈");
        adapter.addFragment(new MessageFragment(), "메세지");
        adapter.addFragment(new MyProfileFragment(), "내정보");
        adapter.addFragment(new SetFragment(), "도움말");
        vp.setAdapter(adapter);
    }
}