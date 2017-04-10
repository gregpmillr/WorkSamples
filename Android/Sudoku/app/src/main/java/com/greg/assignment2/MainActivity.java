package com.greg.assignment2;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


public class MainActivity extends AppCompatActivity {

    /**
     * Declare and initialize values.
     */
    private Button btnFinish;
    private Button btnSelected;
    private Button btnReset;
    private Button btnUndo;
    private Button btnDelete;
    private TextView txtSelected;
    private TextView txtTimer;
    private Integer selectedPosition;
    long startTime = 0;
    private MediaPlayer mediaPlayer;
    public GridView gridView;
    public GridView gridButtonChangers;
    private CustomGridAdapter gridAdapter;
    private CustomNumberChangeGridAdapter gridNumberChangeAdapter;
    private Object[] lastMove = new Object[2];

    public String[] puzzleItems = new String[]{
            "5","3","4","6","7","8","9","1","2",
            "6","7","2","1","9","5","3","4","8",
            "1","9","8","3","4","2","5", "6","7",
            "8","5","9","7","6","1","4","2","3",
            "4","2","6","8","5","3","7","9","1",
            "7","1","3","9","2", "4","8","5","6",
            "9","6","1","5","3","7","2","8","4",
            "2","8","7","4","1","9","6","3","5",
            "3","4","5", "2","8","6","1","7","9"
    };

    public String[] buttonItems = new String[]{ "1","2","3","4","5","6","7","8","9" };

    public Boolean[] masker = new Boolean[]{
            TRUE, TRUE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE,
            TRUE, FALSE, FALSE, TRUE, TRUE, TRUE, FALSE, FALSE, FALSE,
            FALSE, TRUE, TRUE, FALSE, FALSE, FALSE, FALSE, TRUE, FALSE,
            TRUE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, TRUE,
            TRUE, FALSE, FALSE, TRUE, FALSE, TRUE, FALSE, FALSE, TRUE,
            TRUE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, FALSE, TRUE,
            FALSE, TRUE, FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, FALSE,
            FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, FALSE, FALSE, TRUE,
            FALSE, FALSE, FALSE, FALSE, TRUE, FALSE, FALSE, TRUE, TRUE,
    };

    Handler timerHandler = new Handler();

    /**
     * Initialize timer
     */
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            txtTimer.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Initialize values
         */
        gridView = (GridView) this.findViewById(R.id.myGridView);
        gridButtonChangers = (GridView) this.findViewById(R.id.gridChangeNumbers);
        btnFinish = (Button) this.findViewById(R.id.btnFinish);
        gridAdapter = new CustomGridAdapter(MainActivity.this, puzzleItems, masker);
        gridView.setAdapter(gridAdapter);
        gridNumberChangeAdapter = new CustomNumberChangeGridAdapter(MainActivity.this, buttonItems);
        gridButtonChangers.setAdapter(gridNumberChangeAdapter);
        txtTimer = (TextView) this.findViewById(R.id.txtTimer);
        btnReset= (Button) this.findViewById(R.id.btnReset);
        btnDelete = (Button) this.findViewById(R.id.btnDelete);
        mediaPlayer = MediaPlayer.create(this, R.raw.runescapetheme);
        btnUndo = (Button) this.findViewById(R.id.btnUndo);
        btnReset.setText("Reset");
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        mediaPlayer.start();

        /**
         * Reset grid, timer, and mediaPlayer
         */
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btn = (Button) view;
                    //reset grid
                    gridAdapter = new CustomGridAdapter(MainActivity.this, puzzleItems, masker);
                    gridView.setAdapter(gridAdapter);
                    gridNumberChangeAdapter = new CustomNumberChangeGridAdapter(MainActivity.this, buttonItems);
                    gridButtonChangers.setAdapter(gridNumberChangeAdapter);

                    //reset time
                    timerHandler.removeCallbacks(timerRunnable);
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);

                    //reset music
                    if(mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.runescapetheme);
                    mediaPlayer.start();
            }
        });//end onClick

        /**
         * When a gridView item is clicked, set the selected TextView and position.
         */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txtSelected = (TextView) view.findViewById(R.id.txtCell);
                selectedPosition = position;
            }
        });//end onClick

        /**
         * If all TextViews are filled in, Sudoku is finished.
         */
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean incorrectItem = false;

                //get each time and check if it has text. If they all do, Sudoku is finished.
                for(int i = 0; i <= 80; i++){
                        ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i);
                        TextView text = (TextView) gridChild.getChildAt(0);
                        if(text.getText().toString() == ""){ incorrectItem = true; break;}
                }
                if(!incorrectItem){ System.out.println("Congrats, you've solved Sudoku!"); }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "One or more items " +
                            "are empty.", Toast.LENGTH_SHORT);
                    toast.show();}
            }
        });//end onClick

        /**
         * Get last moves if they exist and set last item
         */
        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lastMove[0] != null && lastMove[1] != null){
                    System.out.println((Integer)lastMove[0]);
                    System.out.println((String)lastMove[1]);

                    //get the TextView
                    ViewGroup gridChild = (ViewGroup) gridView.getChildAt((Integer)lastMove[0]);
                    TextView text = (TextView) gridChild.getChildAt(0);
                    text.setText((String)lastMove[1]);
                }else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Cannot undo, there are no " +
                            "previous moves!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });//end onClick

        /**
         * Clear the text of the selected TextView
         */
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedPosition != null){
                    //get the TextView
                    ViewGroup gridChild = (ViewGroup) gridView.getChildAt(selectedPosition);
                    TextView text = (TextView) gridChild.getChildAt(0);
                    if(text.isEnabled()){
                        text.setText("");
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "This item" +
                                " cannot be deleted", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Cannot undo, there are no " +
                            "previous moves!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }//end method

    /**
     * Change the text of the TextView to the button's text that has been clicked
     * @param v
     */
    public void changeNum(View v){
        //check if a TextView has been selected
        if(selectedPosition != null){
            btnSelected = (Button) v;
            int row = 0;
            int rowPosStart = 0;
            int rowPosEnd = 0;
            int colPosStart = 0;
            int colPosEnd = 0;

            String selectedBtnText = btnSelected.getText().toString();

            //calculate the stard and end positions for the column and row to check values against
            colPosStart = selectedPosition%9;
            colPosEnd = 80 - (8-colPosStart);
            row = selectedPosition / 9;
            rowPosStart = row * 9;
            rowPosEnd = rowPosStart + 8;

            boolean invalidMove = false;
            String neighbourTextVal;

            //scan row for matching values
            for(int i = rowPosStart; i < rowPosEnd; i++) {
                ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i);
                int childSize = gridChild.getChildCount();
                for(int k = 0; k < childSize; k++) {
                    if( gridChild.getChildAt(k) instanceof TextView ) {
                        TextView neighbourTxt = (TextView) gridChild.getChildAt(k);
                        neighbourTextVal = neighbourTxt.getText().toString();
                        if(neighbourTextVal == selectedBtnText){ invalidMove = true; }
                    }
                }
            }

            //scan column for matching values
            for(int i = colPosStart; i <= colPosEnd; i+=9) {
                ViewGroup gridChild = (ViewGroup) gridView.getChildAt(i);
                int childSize = gridChild.getChildCount();
                for(int k = 0; k < childSize; k++) {
                    if( gridChild.getChildAt(k) instanceof TextView ) {
                        TextView neighbourTxt = (TextView) gridChild.getChildAt(k);
                        neighbourTextVal = neighbourTxt.getText().toString();
                        if(neighbourTextVal == selectedBtnText){ invalidMove = true; }
                    }
                }
            }

            //calculate the index's quadrant
            int quadrant = calculateQuadrant(row,colPosStart);
            int center;

            //get the center index of the quadrant
            if(quadrant == 0){ center = 10; }
            else if(quadrant == 1){ center = 13; }
            else if(quadrant == 2){ center = 16; }
            else if(quadrant == 3){ center = 37; }
            else if(quadrant == 4){ center = 40; }
            else if(quadrant == 5){ center = 43; }
            else if(quadrant == 6){ center = 64; }
            else if(quadrant == 7){ center = 67; }
            else if(quadrant == 8){ center = 70; }
            else{center = 0;}

            ViewGroup gridChild;
            TextView neighbourTxt;

            //check around the middle index of quadrant
            gridChild = (ViewGroup) gridView.getChildAt(center);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            gridChild = (ViewGroup) gridView.getChildAt(center-10);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            gridChild = (ViewGroup) gridView.getChildAt(center-9);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            gridChild = (ViewGroup) gridView.getChildAt(center-8);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            gridChild = (ViewGroup) gridView.getChildAt(center-1);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            gridChild = (ViewGroup) gridView.getChildAt(center+1);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            gridChild = (ViewGroup) gridView.getChildAt(center+8);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            gridChild = (ViewGroup) gridView.getChildAt(center+9);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            gridChild = (ViewGroup) gridView.getChildAt(center+10);
            neighbourTxt = (TextView) gridChild.getChildAt(0);
            if(neighbourTxt.getText().toString() == selectedBtnText){ invalidMove = true; }

            if(!invalidMove && txtSelected.isEnabled()){
                //set positions for undo action
                lastMove[0] = selectedPosition;
                lastMove[1] = txtSelected.getText().toString();
                txtSelected.setText(selectedBtnText);
            }
            else{
                System.out.println("Invalid move!");
            }
        }else{
            //no TextView has been selected yet
            Toast toast = Toast.makeText(getApplicationContext(), "Select a label first!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }//end method

    /**
     * Calculate the quadrant of the selected TextView
     * @param row
     * @param col
     * @return
     */
    public int calculateQuadrant(int row, int col){
        if(row < 3 && col < 3){ return 0; }
        else if(row < 3 && col < 6){ return 1; }
        else if(row < 3 && col < 9){ return 2; }
        else if(row < 6 && col < 3){ return 3; }
        else if(row < 6 && col < 6){ return 4; }
        else if(row < 6 && col < 9){ return 5; }
        else if(row < 9 && col < 3){ return 6; }
        else if(row < 9 && col < 6){ return 7; }
        else if(row < 9 && col < 9){ return 8; }
        else{return 0;}
    }// end method

}//end class
