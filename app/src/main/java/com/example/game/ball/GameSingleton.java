package com.example.game.ball;

import android.content.Context;



public class GameSingleton {
    private static Game game;

    public static Game getInstance(Context context){
        if (game == null) {
            game = new Game(context);
        }

        return game;
    }
}
