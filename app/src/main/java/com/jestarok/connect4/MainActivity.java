package com.jestarok.connect4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1Player).setOnClickListener(this);
        findViewById(R.id.btn2Player).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case (R.id.btn2Player):
                intent = new Intent(MainActivity.this, Board.class);
                intent.putExtra("Mode","Multi");
                break;
            case (R.id.btn1Player):
                intent = new Intent(MainActivity.this, Board.class);
                intent.putExtra("Mode","Single");
                break;
        }
        if (intent != null){
            startActivity(intent);
        }
    }
}
