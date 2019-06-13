package com.example.whichbin;


public class Questions {

    private int question;
    private int answer;
    private int image;


    public Questions(int question, int answer, int image) {
        this.question = question;
        this.answer = answer;
        this.image = image;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int isAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
