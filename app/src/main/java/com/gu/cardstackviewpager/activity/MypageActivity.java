package com.gu.cardstackviewpager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gu.cardstackviewpager.GridView.GridView1Activity;
import com.gu.cardstackviewpager.R;

public class MypageActivity extends AppCompatActivity {
    private Button want;
    private Button watched;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

         want = findViewById(R.id.want);
         watched = findViewById(R.id.watched);

        want.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MypageActivity.this,GridView1Activity.class);
                startActivity(intent);
            }
        });

        watched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MypageActivity.this,GridView1Activity.class);
                startActivity(intent);
            }
        });


    }
    /*public void setListener(){
        ButtonClickListener listener = new ButtonClickListener();
        want.setOnClickListener(listener);
        watched.setOnClickListener(listener);
    }
    class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.want:
                    intent = new Intent(MypageActivity.this,GridView1Activity.class);
                    break;
                case R.id.watched:
                    intent = new Intent(MypageActivity.this,GridView1Activity.class);
                    break;
            }
        }
    }*/


}
