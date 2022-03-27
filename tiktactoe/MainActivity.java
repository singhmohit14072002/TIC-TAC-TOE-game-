package com.example.tiktactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.String;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // taking reference of every button
    Button btno0,btno1,btno2,btno3,btno4,btno5,btno6,btno7,btno8;
    TextView headerText;

     int PLAYER_O = 0;
     int PLAYER_X = 1;

     int activePlayer = PLAYER_O;

     // for setting the position we set the array
          int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1}; // -1 means position are blank

          boolean isGameActive = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        headerText = findViewById(R.id.header_text);
        // give all button id
        headerText.setText("O turn");

        btno0 = findViewById(R.id.btno0);
        btno1 = findViewById(R.id.btno1);
        btno2 = findViewById(R.id.btno2);
        btno3 = findViewById(R.id.btno3);
        btno4 = findViewById(R.id.btno4);
        btno5 = findViewById(R.id.btno5);
        btno6 = findViewById(R.id.btno6);
        btno7 = findViewById(R.id.btno7);
        btno8 = findViewById(R.id.btno8);
        // set onclick listener for do something  to click button

        btno0.setOnClickListener(this);
        btno1.setOnClickListener(this);
        btno2.setOnClickListener(this);
        btno3.setOnClickListener(this);
        btno4.setOnClickListener(this);
        btno5.setOnClickListener(this);
        btno6.setOnClickListener(this);
        btno7.setOnClickListener(this);
        btno8.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        //logic for button press

        if(!isGameActive)
            return;


       Button clickedBtn = findViewById(v.getId());
       // taking tag and the tag is in string so for converting we use parseInt
       int clickedTag = Integer.parseInt(v.getTag().toString());


       if (filledPos[clickedTag] != -1){ // -1 means user did filled value then go ahead otherwise return from this position
           return;
        }

       filledPos[clickedTag] = activePlayer;

       if (activePlayer == PLAYER_O){
           clickedBtn.setText("O");// for setting the text we use this
           clickedBtn.setBackground(getDrawable(R.color.white));
           activePlayer = PLAYER_X;
           headerText.setText("X turn");
       }
       else {
           clickedBtn.setText("X");
           clickedBtn.setBackground(getDrawable(R.color.white));
           activePlayer = PLAYER_O;
           headerText.setText("O turn");
       }



     checkForWin();

    }

    private void checkForWin() {

        // we will check who is win and who will not .
          int[][] winningPos = {{0,1,2} , {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8} ,{2,4,6}};

          for(int i=0; i<8; i++){
            int val0 = winningPos[i][0];
              int val1 = winningPos[i][1];
              int val2 = winningPos[i][2];

              if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                  if (filledPos[val0] != -1){
                      // winner declare

                      isGameActive = false;

                      if(filledPos[val0] == PLAYER_O)
                         showDialog("O is winner");
                      else
                          showDialog("X is winner");

                  }

              }


          }


    }
    


// if winner is declare then for restart the game so we make the new function
  private   void showDialog(String winnerText) {
       new AlertDialog.Builder(this).
      setTitle(winnerText).
              setPositiveButton("Restart game", new DialogInterface.OnClickListener(){
                          @Override
                          public void onClick(DialogInterface dialog, int which){
                              restartGame();

                          }
                   } )
                              .show();
  }

  private void restartGame(){
        activePlayer = PLAYER_O;
        headerText.setText("O turn");
        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        btno0.setText("");
      btno1.setText("");
      btno2.setText("");
      btno3.setText("");
      btno4.setText("");
      btno5.setText("");
      btno6.setText("");
      btno7.setText("");
      btno8.setText("");


      btno0.setBackground(getDrawable(android.R.color.holo_red_light));
      btno1.setBackground(getDrawable(android.R.color.holo_red_light));
      btno2.setBackground(getDrawable(android.R.color.holo_red_light));
      btno3.setBackground(getDrawable(android.R.color.holo_red_light));
      btno4.setBackground(getDrawable(android.R.color.holo_red_light));
      btno5.setBackground(getDrawable(android.R.color.holo_red_light));
      btno6.setBackground(getDrawable(android.R.color.holo_red_light));
      btno7.setBackground(getDrawable(android.R.color.holo_red_light));
      btno8.setBackground(getDrawable(android.R.color.holo_red_light));

      isGameActive = true;
  }
}
