package kr.hs.emirim.parksodam.healingmessage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

/**
 * Created by Mirim on 2018-03-21.
 */

public class HomeActivity extends Activity {
    ViewFlipper flipper;

    ToggleButton toggle_Flipping;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        flipper= (ViewFlipper)findViewById(R.id.flipper);

        /*for(int i=0;i<7;i++){
            ImageView img= new ImageView(this);
            img.setImageResource(R.drawable.healing_04+i);
            flipper.addView(img);
        }*/
        //사진 더 넣을 생각이면 주석 처리한 이 방법으로 추가하기

        Animation showIn= AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        //ViewFlipper에게 등장 애니메이션 적용
        flipper.setInAnimation(showIn);

        flipper.setOutAnimation(this, android.R.anim.slide_out_right);  //교체될 때 퇴장하는 애니메이션

        flipper.setFlipInterval(3000);  //1초에 한 번
        flipper.startFlipping();    //flipping 시작

        /*//ToggleButton에 Toggle상태 변경 리스너 세팅(OnCheckedChangedListener)
        toggle_Flipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flipper.setFlipInterval(1000);  //1초에 한 번
                flipper.startFlipping();    //flipping 시작
            }
        });*/

    }
    public void mOnClick(View v){

        switch( v.getId() ){

            case R.id.btn_previous:
                flipper.showPrevious();//이전 View로 교체
                break;

            case R.id.btn_next:
                flipper.showNext();//다음 View로 교체
                break;

        }
    }
}


//http://kitesoft.tistory.com/75 참고