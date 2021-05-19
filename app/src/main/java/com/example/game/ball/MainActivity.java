package com.example.game.ball;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.game.ball.databinding.ActivityMainBinding;


//TODO modyfikacja menu
//TODO doac koniec gry z wynikiem
//TODO aktywność z ustawieniami
//TODO punkty (rankingi)
//TODO aktywnośc z ustawieniami




public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setButtonListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings :
                SettingsActivity.start(context);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setButtonListeners() {
        mBinding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.start(context);
            }
        });

        mBinding.highscoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}