package com.example.w0273754.quizproj;

import android.app.Activity;
import android.os.*;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity2Activity extends Activity {

    //count which question user is on
    int count = 0;
    int correctAnswers = 0;
    //user's name
    TextView name;
    TextView helloMessage;
    //actual answer to question
    String answer = null;
    //radioButtons
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    Button nextQuestion;
    Button btnQuit;

    //2 arrayLists, one for answers, one for definition
    ArrayList<String> answers = new ArrayList<String>();
    ArrayList<String> definitions = new ArrayList<String>();
    ArrayList<String> radOptions = new ArrayList<String>();

    //hashmap to map answers to definitions
    Map<String,String> map = new HashMap<String,String>();//create map

    TextView txtDefinition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        //link xml to java
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        nextQuestion = (Button) findViewById(R.id.btnNextQuestion);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        txtDefinition = (TextView) findViewById(R.id.txtDefinition);
        helloMessage = (TextView) findViewById(R.id.txtHelloMessage);

        //users name
        name = (TextView) findViewById(R.id.name);
        String myName = "";

        //handle bundle
        Bundle extras=getIntent().getExtras();
        if(extras != null)//if bundle has something in it
        {
            //get user's name, set the text to the user's name
            myName = extras.getString("KEY");
            name.setText(myName);
        }

        InputStream is = this.getResources().openRawResource(R.raw.definitionsanswers);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String str = null;

        //store each line into definition and answer array lists
        try{
            while((str = br.readLine())!=null)
            {
                //store even number in the text file into an array list called answers
                //store odd number in the text file into an array list called definitions
                String[] ar=str.split(";");
                String answer = ar[0];
                String definition = ar[1];
                answers.add(answer);
                definitions.add(definition);
                map.put(answer, definition);
            }//end while
        }catch(IOException e){
            Log.w("Custom Error", "error reading answersdefinitions.txt");
        }//end catch

        setup();

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked() || radioButton4.isChecked()) {
                    answer = checkForAnswer();
                    checkAnswer();
                    checkCount();
                } else{
                    System.out.println("Please select answer");
                }
            }//end onClick
        });//end onClickListener


        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }//end onCreate

    //check to see what the actual answer is from the definition in the TextView. Assign answer to string "answer"
    public String checkForAnswer(){
        if(txtDefinition.getText().toString() == map.get("2"))
        {
            answer = (getKeyFromValue(map,"1+1?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("4")){
            answer = (getKeyFromValue(map,"2+2?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("6")){
            answer = (getKeyFromValue(map,"3+3?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("8")){
            answer = (getKeyFromValue(map,"4+4?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("10")){
            answer = (getKeyFromValue(map,"5+5?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("12")){
            answer = (getKeyFromValue(map,"6+6?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("14")){
            answer = (getKeyFromValue(map,"7+7?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("16")){
            answer = (getKeyFromValue(map,"8+8?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("18")){
            answer = (getKeyFromValue(map,"9+9?").toString());
        }
        else if(txtDefinition.getText().toString() == map.get("20")){
            answer = (getKeyFromValue(map,"10+10?").toString());
        }
        return answer;//returns actual answer
    }


    public void checkCount(){
        definitions.remove(0);
        Collections.shuffle(definitions);
        txtDefinition.setText(definitions.get(0));

        //if all questions have been asked, then print out how many are correct
        if(count == 9){
            radioButton1.setVisibility(View.GONE);
            radioButton2.setVisibility(View.GONE);
            radioButton3.setVisibility(View.GONE);
            radioButton4.setVisibility(View.GONE);
            nextQuestion.setVisibility(View.GONE);
            String origName = name.getText().toString();
            name.setVisibility(View.GONE);
            helloMessage.setText("You've finished the quiz!");
            correctAnswers +=1;
            txtDefinition.setText(origName + ", you got " + correctAnswers + " answers correct!");
            if(correctAnswers < 5){
                txtDefinition.append(" Let's hope you do a bit better next time.");
            }
            else if(correctAnswers <= 9){
                txtDefinition.append(" You did well, but not perfect!");
            }
            else{
                txtDefinition.append(" Perfect score!");
            }

        }
        setAnswers();
    }//end method checkCount


    public void checkAnswer(){
        String userAnswer = null;
        if (radioButton1.isChecked()) {
            userAnswer = radioButton1.getText().toString();
        }
        if (radioButton2.isChecked()) {
            userAnswer = radioButton2.getText().toString();
        }
        if (radioButton3.isChecked()) {
            userAnswer = radioButton3.getText().toString();
        }
        if (radioButton4.isChecked()) {
            userAnswer = radioButton4.getText().toString();
        }

        if(userAnswer == answer){
            Toast.makeText(getBaseContext(),"Correct!",Toast.LENGTH_LONG).show();
            correctAnswers+=1;
            count+=1;
        }
        if(userAnswer != answer){
            Toast.makeText(getBaseContext(),"Incorrect!",Toast.LENGTH_LONG).show();
            count+=1;
        }
    }//end method checkAnswer


    public void setup(){
        radOptions.clear();
        //shuffle arrayLists to randomize
        Collections.shuffle(answers);
        Collections.shuffle(definitions);
        //set the definition to ArrayList definitions at 0
        txtDefinition.setText(definitions.get(0));
        //assign radioButtons
        setAnswers();
    }//end method setup


    public void setAnswers(){
        radOptions.clear();

        Collections.shuffle(answers);
        answer = checkForAnswer();
        radOptions.add(answer);

        Random rn = new Random();
        int option1 = 0;
        String str1 = "";
        int option2 = 0;
        String str2 = "";
        int option3 = 0;
        String str3 = "";

        boolean valid1 = false;
        while (!valid1) {
            option1 = rn.nextInt(9);
            str1 = answers.get(option1);
            if (!str1.equals(answer)) {
                radOptions.add(str1);
                valid1 = true;
            }
        }

        boolean valid2 = false;

        while (!valid2) {
            option2 = rn.nextInt(9);
            str2 = answers.get(option2);
            if ((!str2.equals(answer)) && (option2 != option1)) {
                radOptions.add(str2);
                valid2 = true;
            }
        }


        boolean valid3 = false;

        while (!valid3) {
            option3 = rn.nextInt(9);
            str3 = answers.get(option3);
            if ((!str3.equals(answer)) && (option3 != option2) && (option3 != option1)) {
                radOptions.add(str3);
                valid3 = true;
            }
        }

        Collections.shuffle(radOptions);
        radioButton1.setText(radOptions.get(0));
        radioButton2.setText(radOptions.get(1));
        radioButton3.setText(radOptions.get(2));
        radioButton4.setText(radOptions.get(3));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
}
