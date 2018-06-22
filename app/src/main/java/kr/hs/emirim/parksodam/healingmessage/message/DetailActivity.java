package kr.hs.emirim.parksodam.healingmessage.message;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import kr.hs.emirim.parksodam.healingmessage.R;

/**
 * Created by 민경 on 2018-06-22.
 */

public class DetailActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        TextView name = (TextView)findViewById(R.id.name);
        TextView context = (TextView)findViewById(R.id.context);

        name.setText(intent.getStringExtra("name"));
        context.setText(intent.getStringExtra("context"));
    }


}
