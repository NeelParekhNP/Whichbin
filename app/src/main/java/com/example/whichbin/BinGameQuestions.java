package com.example.whichbin;

/** Object class to save information about the questions in the bin game */


public class BinGameQuestions {

    private int question;
    private int answerInt;
    private int image;
    private String answer;


    public BinGameQuestions(int question, int answerInt, int image, String answer) {
        this.question = question;
        this.answerInt = answerInt;
        this.image = image;
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int isAnswer() {
        return answerInt;
    }

    public void setAnswerInt(int answerInt) {
        this.answerInt = answerInt;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
