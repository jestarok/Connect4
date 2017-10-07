package com.jestarok.connect4;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Board extends AppCompatActivity implements View.OnClickListener {

    final static int WIDTH = 7;
    final static int HEIGHT = 6;
    final static int BOTTOM_ROW = WIDTH - 1;
    int player = 1;
    static int[][] board= new int[7][6];
    String mode = "";
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        board= new int[7][6];
        GridView gridView = (GridView) findViewById(R.id.board);
        List<Chip> chips = new ArrayList<>();
        for(int i = 0, j = 0; i < 42; i++){
            chips.add(new Chip(i));
        }
        ChipsAdapter chipsAdapter = new ChipsAdapter(this,chips);
        gridView.setAdapter(chipsAdapter);


        findViewById(R.id.Col1).setOnClickListener(this);
        findViewById(R.id.Col2).setOnClickListener(this);
        findViewById(R.id.Col3).setOnClickListener(this);
        findViewById(R.id.Col4).setOnClickListener(this);
        findViewById(R.id.Col5).setOnClickListener(this);
        findViewById(R.id.Col6).setOnClickListener(this);
        findViewById(R.id.Col7).setOnClickListener(this);

        mode = getIntent().getStringExtra("Mode");
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {
                case (R.id.Col1):
                    if (mode.equalsIgnoreCase("single")) {
                        excecutePlay(0, player);
                        TimeUnit.MILLISECONDS.sleep(200);
                        excecutePlay(rand.nextInt(7), player);
                    } else {
                        excecutePlay(0, player);
                    }
                    break;
                case (R.id.Col2):
                    if (mode.equalsIgnoreCase("single")) {
                        excecutePlay(1, player);
                        TimeUnit.MILLISECONDS.sleep(200);
                        excecutePlay(rand.nextInt(7), player);
                    } else {
                        excecutePlay(1, player);
                    }
                    break;
                case (R.id.Col3):
                    if (mode.equalsIgnoreCase("single")) {
                        excecutePlay(2, player);
                        TimeUnit.MILLISECONDS.sleep(200);
                        excecutePlay(rand.nextInt(7), player);
                    } else {
                        excecutePlay(2, player);
                    }
                    break;
                case (R.id.Col4):
                    if (mode.equalsIgnoreCase("single")) {
                        excecutePlay(3, player);
                        TimeUnit.MILLISECONDS.sleep(200);
                        excecutePlay(rand.nextInt(7), player);
                    } else {
                        excecutePlay(3, player);
                    }
                    break;
                case (R.id.Col5):
                    if (mode.equalsIgnoreCase("single")) {
                        excecutePlay(4, player);
                        TimeUnit.MILLISECONDS.sleep(200);
                        excecutePlay(rand.nextInt(7), player);
                    } else {
                        excecutePlay(4, player);
                    }
                    break;
                case (R.id.Col6):
                    if (mode.equalsIgnoreCase("single")) {
                        excecutePlay(5, player);
                        TimeUnit.MILLISECONDS.sleep(200);
                        excecutePlay(rand.nextInt(7), player);
                    } else {
                        excecutePlay(5, player);
                    }
                    break;
                case (R.id.Col7):
                    if (mode.equalsIgnoreCase("single")) {
                        excecutePlay(6, player);
                        TimeUnit.MILLISECONDS.sleep(200);
                        excecutePlay(rand.nextInt(7), player);
                    } else {
                        excecutePlay(6, player);
                    }
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excecutePlay(int col, int currentPlayer){
        GridView g = (GridView) findViewById(R.id.board);
        FrameLayout f;
        ImageView im;
        TextView t;

        if (board[col][5] != 0) {
            for (int i = 0; i <= 4; i++) {
                if (board[col][i + 1] != 0 && board[col][i] == 0 ) {
                    board[col][i] = currentPlayer;

                    f = (FrameLayout) g.getChildAt(i*7+col);

                    im = (ImageView) f.getChildAt(0);
                    t = (TextView) f.getChildAt(1);
                    if(player == 1) {
                        im.setColorFilter(Color.argb(255, 0, 0, 0));
                        t.setText("X");
                        t.setTextColor(Color.WHITE);
                    } else {
                        im.setColorFilter(Color.argb(255, 255, 255, 255));
                        t.setText("O");
                        t.setTextColor(Color.BLACK);
                    }
                }
            }
        }else {
            board[col][5] = currentPlayer;
            f = (FrameLayout) g.getChildAt(42- ( 7 - col ));
            im = (ImageView) f.getChildAt(0);
            t = (TextView) f.getChildAt(1);
            if(player == 1) {
                im.setColorFilter(Color.argb(255, 0, 0, 0));
                t.setText("X");
                t.setTextColor(Color.WHITE);
            } else {
                im.setColorFilter(Color.argb(255, 255, 255, 255));
                t.setText("O");
                t.setTextColor(Color.BLACK);
            }
        }
        checkwin(player,g);
        player *= -1;
        t = (TextView)findViewById(R.id.turnText);
        if(player > 0)
            t.setText("Player "+1);
        else
            t.setText("Player "+2);
    }

    private void checkwin(int player, GridView g){
        FrameLayout f;
        FrameLayout f2;
        ImageView im;
        TextView t;

        if(!CheckXVertical(player) || !CheckXHorizontal(player) /*|| CheckXDiagonalForward(player) || CheckXDiagonalBack(player)*/){
            try {
                for(int i = 0; i < 42; i++){
                    f = (FrameLayout) g.getChildAt(i);
                    im = (ImageView) f.getChildAt(0);
                    im.setColorFilter(null);
                    t = (TextView) f.getChildAt(1);
                    t.setText("");
                }
                board = new int[7][6];
                t = (TextView)findViewById(R.id.winText);
                if (player > 0){
                    f2 = (FrameLayout) findViewById(R.id.frameLayout);
                    t.setText("Player 1 Wins!");
                    f2.addView(t);
                    TimeUnit.MILLISECONDS.sleep(200);
                    t.setText("");
                    t.refreshDrawableState();
                    f2.removeView(t);
                }
                else{
                    f2 = (FrameLayout) findViewById(R.id.frameLayout);
                    t.setText("Player 2 Wins!");
                    f2.addView(t);
                    TimeUnit.MILLISECONDS.sleep(200);
                    t.setText("");
                    t.refreshDrawableState();
                    f2.removeView(t);

                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static boolean CheckXVertical(int player){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board vertically
            for(int h = 0; HEIGHT > h; h += 1){
                for(int w = 0; WIDTH > w; w += 1){
                    if(board[w][h] == player){ //if it finds an X, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an X, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
//                        board = new int[7][6];
                    }
                }
            }
            break;
        }
        return flag;
    }
    public static boolean CheckXHorizontal(int player){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board horizontally
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == player){ //if it finds an X, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an X, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
//                        board = new int[7][6];
                    }
                }
            }
            break;
        }
        return flag;
    }
    public static boolean CheckXDiagonalForward(int player){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an X is found
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == player){ //if X is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Xs
                            if(checkColumn + w <= WIDTH - 1&& checkRow + h <= HEIGHT - 1){
                                if(board[w + checkColumn][h + checkRow] == player){ //if X is found, add 1 to counter
                                    counter += 1;
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == WIDTH -1 || checkRow == HEIGHT -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }
    public static boolean CheckXDiagonalBack(int player){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an X is found
            for(int w = 0; WIDTH > w; w += 1){
                for(int h = 0; HEIGHT > h; h += 1){
                    if(board[w][h] == player){ //if X is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Xs
                            if(w - checkColumn >= 0 && h - checkRow >= 0){
                                if(board[w - checkColumn][h - checkRow] == player){
                                    counter += 1; //if X is found, add 1 to counter
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == HEIGHT -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }
}
