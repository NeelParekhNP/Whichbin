package com.example.whichbin;

import java.util.ArrayList;

public class TriviaGameManager {

    private ArrayList<TriviaQuestion> triviaArray;

    /** Constructor method to set up the game and get it running. */
    public TriviaGameManager(){
        triviaArray = new ArrayList<>();
        createQuestions();
    }

    /** Method to populate the questions */
    public void createQuestions(){
        TriviaQuestion q1 = new TriviaQuestion("Climate Change was theorised as early as 1896.", true);
        TriviaQuestion q2 = new TriviaQuestion("Greenhouse gases are gases which trap heat energy in the earth's atmosphere.", true);
        TriviaQuestion q3 = new TriviaQuestion("Everyone agreed the Kyoto agreement was effective at stopping climate change.", false);
        TriviaQuestion q4 = new TriviaQuestion("Deforestation contributes to emissions about as much as cars.", true);
        TriviaQuestion q5 = new TriviaQuestion("Being a vegan is better for the environment", true);
        TriviaQuestion q6 = new TriviaQuestion("Flying is better for the environment than other forms of transport", false);
        TriviaQuestion q7 = new TriviaQuestion("Glaciers are growing as a consequence of climate change", false);
        TriviaQuestion q8 = new TriviaQuestion("Cows contribute to methane emissions", true);
        TriviaQuestion q9 = new TriviaQuestion("Methane traps more heat than the same amount of carbon dioxide", true);
        TriviaQuestion q10 = new TriviaQuestion("Much less carbon dioxide than methane is put into the atmosphere each year", false);
        triviaArray.add(q1);
        triviaArray.add(q2);
        triviaArray.add(q3);
        triviaArray.add(q4);
        triviaArray.add(q5);
        triviaArray.add(q6);
        triviaArray.add(q7);
        triviaArray.add(q8);
        triviaArray.add(q9);
        triviaArray.add(q10);
    }

    public String getTriviaQuestionByNo(int qNo){
        TriviaQuestion nowQuestion = triviaArray.get(qNo);
        return nowQuestion.triviaGetQuestion();
    }


    /** Getter method, input the question number and this method should return if the correct answer is true or false*/
    public boolean checkTriviaQuestionAnswer(int qNo){
        TriviaQuestion nowQuestion = triviaArray.get(qNo);
        return nowQuestion.triviaCheckTrueFalse();
    }
}

