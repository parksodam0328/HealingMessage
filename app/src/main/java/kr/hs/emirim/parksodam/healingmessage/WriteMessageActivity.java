package kr.hs.emirim.parksodam.healingmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import org.w3c.dom.Text;

import java.util.UUID;

import kr.hs.emirim.parksodam.healingmessage.message.R_User;

/**
 * Created by Mirim on 2018-06-21.
 */

public class WriteMessageActivity extends AppCompatActivity{
    private String id;
    private TextView send_id;
    private TextView receive_id;
    private ImageView send_btn;
    private DatabaseReference mDatabase;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        intent = getIntent();
        send_id = (TextView)findViewById(R.id.send_id);
        receive_id = (TextView)findViewById(R.id.receive_id);
        send_btn = (ImageView)findViewById(R.id.write_btn);

        receive_id.setText(intent.getStringExtra("name"));

        final UUID uuid = UUID.randomUUID();
        ImageView backimg = (ImageView) findViewById(R.id.back_icon);
        backimg.setOnClickListener(new View.OnClickListener() { // 뒤로 가기
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final EditText edit = (EditText)findViewById(R.id.input_message);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(430);
        edit.setFilters(FilterArray);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeMessage(receive_id.getText().toString(),uuid.toString(), send_id.getText().toString(), edit.getText().toString());
                Toast.makeText(getApplication(), "메세지가 성공적으로 전달되었습니다!", Toast.LENGTH_SHORT);
                onBackPressed();
            }
        });

    }


    public void writeMessage(String id,String token,String name, String context){
        R_User user = new R_User(name, context);

        mDatabase.child("message").child(id).child(token).setValue(user);
    }
}
