package com.example.whichbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TriviaAnswerActivity extends AppCompatActivity {

    private TextView questionDisplay;
    private TextView answerDisplay;
    private Button nextButton;
    private Button prevButton;
    private Button mainMenuButton;

    private int questionIndex = 0;
    private String question;
    private String answerText;
    private TriviaGameManager triviaGameManager = new TriviaGameManager();

    private TriviaAnswerParcel answerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_answer);

        Intent intent = getIntent();
        answerInfo = intent.getParcelableExtra("answerInfo");

        questionDisplay = (TextView) findViewById(R.id.question_info);
        answerDisplay = (TextView) findViewById(R.id.answer_info);

        nextButton = (Button) findViewById(R.id.next_answer_button);
        prevButton = (Button) findViewById(R.id.prev_answer_button);
        mainMenuButton = (Button) findViewById(R.id.main_menu_button);

        displayAnsweredQuestion(questionIndex);
        displayAnswerFeedback(questionIndex);

        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(questionIndex +1 < answerInfo.getNumberOfQsAnswered()){
                    questionIndex++;
                    displayAnsweredQuestion(questionIndex);
                    displayAnswerFeedback(questionIndex);
                }else{

                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(questionIndex > 0){
                    questionIndex--;
                    displayAnsweredQuestion(questionIndex);
                    displayAnswerFeedback(questionIndex);
                }else{

                }
            }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMainMenu();
            }
        });
    }

    private void displayAnsweredQuestion(int qNo){
        if(answerInfo.getNumberOfQsAnswered() > 0) {
            question = answerInfo.getAnsweredQuestion(questionIndex);
        }else{
            question = "You didn't answer any questions, why not have another go!";
        }
        questionDisplay.setText(question);
    }

    private void displayAnswerFeedback(int qNo){
        if(answerInfo.getNumberOfQsAnswered() > 0) {
            int answerGiven = answerInfo.getInputAnsweredByQNo(questionIndex);
            boolean trueFalse = triviaGameManager.checkTriviaQuestionAnswer(questionIndex);

            answerText = generateAnswerResponse(answerGiven, trueFalse);
        }else{
            answerText = "You never lose when you learn!";
        }
        answerDisplay.setText(answerText);
    }

    private String generateAnswerResponse(int answerGiven, boolean trueFalse){
        if(answerGiven == 0 && trueFalse == true){
            return "You said this statement is true-you're correct, blah blah blah";
        }if(answerGiven == 1 && trueFalse == false){
            return "You said this statement is false-you're correct, yakkety smackety";
        }if(answerGiven == 0 && trueFalse == false){
            return "You said this statement is true-actually it's false murmur murmur...";
        }if(answerGiven == 1 && trueFalse == true){
            return "You answered false-but actually this statement is true! Who knew!";
        }else{
            return "Something's gone wrong here...";
        }
    }

    public void openMainMenu(){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
