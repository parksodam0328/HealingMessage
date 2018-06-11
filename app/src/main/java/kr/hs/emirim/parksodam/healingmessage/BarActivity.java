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

import kr.hs.emirim.parksodam.healingmessage.adapter.FragmentPagerAdapter;
import kr.hs.emirim.parksodam.healingmessage.message.MessageFragment;
import kr.hs.emirim.parksodam.healingmessage.myprofile.MyProfileFragment;
import kr.hs.emirim.parksodam.healingmessage.search.SearchActivity;

import static kr.hs.emirim.parksodam.healingmessage.R.drawable.healing_message_image;


public class BarActivity extends AppCompatActivity {

    public ViewPager vp;
    public TabLayout layout;
    //public FirebaseDatabase mDatabase;
    private String TAG ="myreceiver : ";
    private Toolbar toolbar;
    private ImageView mTitle;
    private ImageView mSearch;
    public static String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        //툴바 설정
        //FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
        mTitle = (ImageView)findViewById(R.id.imageTitle) ;

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
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

        for (int i = 0; i < 3; i++) {
            Log.e("", String.valueOf(layout.getTabCount()));
            if(i==0) {
                layout.getTabAt(i).setText("홈").setIcon(R.drawable.home_click);
                mTitle.setImageResource(healing_message_image);
            }
            else if(i==1) {
                layout.getTabAt(i).setText("메세지").setIcon(R.drawable.message_btn);
                mTitle.setImageResource(R.drawable.message_image);
            }
            else if(i==2) {
                layout.getTabAt(i).setText("내정보").setIcon(R.drawable.profile_btn);
                mTitle.setImageResource(R.drawable.myprofile_image);
            }

        }
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mTitle.setImageResource(healing_message_image);
                } else if (position == 1)
                {
                    mTitle.setImageResource(R.drawable.message_image);
                }
                else if (position == 2)
                {
                    mTitle.setImageResource(R.drawable.myprofile_image);
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
                    mTitle.setImageResource(R.drawable.healing_message_image);
                    tab.setIcon(R.drawable.home_click);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#EDBED3"));
                }

                else if(tab.getPosition()==1) {
                    mTitle.setImageResource(R.drawable.message_image);
                    tab.setIcon(R.drawable.message_click);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#EDBED3"));
                }
                else if(tab.getPosition()==2) {
                    mTitle.setImageResource(R.drawable.myprofile_image);
                    tab.setIcon(R.drawable.profile_click);
                    layout.setTabTextColors(Color.parseColor("#666666"), Color.parseColor("#EDBED3"));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    mTitle.setImageResource(healing_message_image);
                    tab.setIcon(R.drawable.home_btn);
                    layout.setTabTextColors(Color.parseColor("#000076"), Color.parseColor("#EDBED3"));
                }
                else if(tab.getPosition()==1) {
                    mTitle.setImageResource(R.drawable.message_image);
                    tab.setIcon(R.drawable.message_btn);
                    layout.setTabTextColors(Color.parseColor("#000076"), Color.parseColor("#EDBED3"));
                }
                else if(tab.getPosition()==2) {
                    mTitle.setImageResource(R.drawable.myprofile_image);
                    tab.setIcon(R.drawable.profile_btn);
                    layout.setTabTextColors(Color.parseColor("#000076"), Color.parseColor("#EDBED3"));
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
        vp.setAdapter(adapter);
    }
}