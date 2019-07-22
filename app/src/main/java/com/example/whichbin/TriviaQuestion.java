package com.example.whichbin;

/** A class to hold the questions for the Trivia game.
 *  Maybe this will later become a superclass if multiple choice options are added.
 */
public class TriviaQuestion {
    private String question;
    private String answerInfo;
    private boolean trueFalse;

    /** Constructor requires a question and a boolean value */
    public TriviaQuestion(String question, String answerInfo, boolean trueFalse){
        this.question = question;
        this.answerInfo = answerInfo;
        this.trueFalse = trueFalse;
    }

    public String triviaGetQuestion(){
        return question;
    }

    public Boolean triviaCheckTrueFalse(){
        return trueFalse;
    }

    public String triviaGetAnswerInfo(){
        return answerInfo;
    }
}
