package kr.hs.emirim.parksodam.healingmessage;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mirim on 2018-06-13.
 */

public class CustomDialog {

    private Context context;

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

    public CustomDialog(Context context){
        this.context = context;
    }

    //호출할 다이얼로그 함수를 정의
    public void callDialog(){
        //커스텀 다이얼로그의 정의하기 위해 Dialog 클래스를 생성
        final Dialog dlg = new Dialog(context);

        //액티비티의 타이틀바를 숨긴다
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //커스텀 다이얼로그의 레이아웃 설정
        dlg.setContentView(R.layout.dialog_custom);

        //커스텀 다이얼로그 호출
        dlg.show();

        //커스텀 다이얼로그의 각 위젯들을 정의
        final TextView message = (TextView)dlg.findViewById(R.id.message);

        for(int i = 0; i< word.length; i++){
            word_random = (int) (Math.random() * word.length);
        }
        Log.e("피치퍼펙트재밋다..", word[word_random]);
        message.setText(word[word_random]);

        final Button okButton = (Button) dlg.findViewById(R.id.okButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "포춘쿠키를 확인했습니다!", Toast.LENGTH_SHORT).show();
                //커스텀 다이얼로그 종료
                dlg.dismiss();
            }
        });
    }
}
