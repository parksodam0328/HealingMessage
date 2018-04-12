package kr.hs.emirim.parksodam.healingmessage.myprofile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import kr.hs.emirim.parksodam.healingmessage.R;

import static kr.hs.emirim.parksodam.healingmessage.BarActivity.id;

public class ReceivedMessage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("tagggg",id);
        setContentView(R.layout.activity_received_message);
    }

}
