package com.example.game.ball;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity; //Klasa podstawowa dla działań, które chcą korzystać z niektórych nowszych funkcji platformy na starszych urządzeniach z Androidem.
import android.os.Bundle; //import potrzebny do klasy Bundle aby odczytywać stan zmiennych gry
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.game.ball.R;
import com.example.game.ball.databinding.ActivityGameBinding;

import java.text.DecimalFormat;
import java.util.Locale;

public class GameActivity extends AppCompatActivity implements SensorEventListener{  //SensorEventListener Służy do otrzymywania powiadomień z SensorManager, gdy są nowe dane z czujnika.

    private ActivityGameBinding mBinding;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game); //Ustaw widok zawartości działania na podany układ i zwróć skojarzone powiązanie
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //dostep do akcelerometru
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //dostep do akcelerometru
        game = GameSingleton.getInstance(getApplicationContext());
        game.onStart();
        mBinding.drawView.setBackgroundColor(Color.GRAY); //kolor tła
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_FASTEST); //pobieranie danych z akcelerometru tak szybko jak to możliwe i przekazywane do accelerometer
        }
        game.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.restart :
                game.onStart();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float acceleration0 = event.values[0] / 9.81f;
        float acceleration1 = event.values[1] / 9.81f;
        //float acceleration2 = event.values[2] / 9.81f;

        int stopX = (int) (-acceleration0 * 500 ); //stop dla osi x czym wyższa wartośc pomnożona tym sznurek bardziej elastyczny (500 optymalnie)
        int stopY = (int) (acceleration1 * 500);

        gameLoop(stopX, stopY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void gameLoop(int sensorX, int sensorY){
        if(!game.isGameOver()){
            game.update(sensorX, sensorY);
            updateUI(sensorX, sensorY);
        }
        else {                                   //koniec gry
            //TODO dodać game over
            sensorManager.unregisterListener(this);   //wyłączenie nasłuchiwania czujników

        }
    }

    private void updateUI(int sensorX, int sensorY){
        mBinding.score.setText(String.format(Locale.getDefault(), "%s%d", getResources().getString(R.string.score), game.getPoints()));
        mBinding.timeRemaining.setText(new DecimalFormat("#.#").format(game.getTimeRemainingMilis() / 1000));
        draw(sensorX, sensorY);
    }

    private void draw(int sensorX, int sensorY){
        mBinding.drawView.setStopX(sensorX);
        mBinding.drawView.setStopY(sensorY);
        mBinding.drawView.setTargetX(game.getTarget().x);
        mBinding.drawView.setTargetY(game.getTarget().y);
        mBinding.drawView.setTargetRadius(game.getTargetRadius());
        mBinding.drawView.invalidate();
    }
}
