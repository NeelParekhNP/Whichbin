package com.example.whichbin;

public class Questions {

    private int question;
    private int answer;

    public Questions(int question, int answer) {
        this.question = question;
        this.answer = answer;
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
}
