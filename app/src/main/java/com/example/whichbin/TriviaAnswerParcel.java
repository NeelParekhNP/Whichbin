package com.example.whichbin;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class TriviaAnswerParcel implements Parcelable {
    private ArrayList<String> answeredQuestionsList;
    private ArrayList<Integer> inputAnswersList;

    public TriviaAnswerParcel(ArrayList<String> answeredQuestionsList, ArrayList<Integer> inputAnswersList){
        this.answeredQuestionsList = answeredQuestionsList;
        this.inputAnswersList =inputAnswersList;
    }

    protected TriviaAnswerParcel(Parcel in) {
        this.answeredQuestionsList = in.readArrayList(null);
        this.inputAnswersList = in.readArrayList(null);

        //previous code this.answeredQuestionsList = (ArrayList<String>) in.createTypedArrayList();
    }

    public static Creator<TriviaAnswerParcel> CREATOR = new Creator<TriviaAnswerParcel>() {
        @Override
        public TriviaAnswerParcel createFromParcel(Parcel in) {
            return new TriviaAnswerParcel(in);
        }

        @Override
        public TriviaAnswerParcel[] newArray(int size) {
            return new TriviaAnswerParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(answeredQuestionsList);
        dest.writeList(inputAnswersList);
    }

    /** method to add a question that was answered,
     *
      * @param question - the question's index in TriviaGameManager
     * @param selectedAnswer - the user's selected answer (0 is true, 1 is false)
     *
     *                       Might now be irrelevant...

    public void addQuestion(String question, int selectedAnswer){
        answeredQuestionsList.add(question);
        inputAnswersList.add(selectedAnswer);
    }
     */


    /** takes input of index of question asked, returns the question number
     *  index from TriviaGameManager
     *
     * @param answeredIndex the order in which the question was asked-eg index 0 was the first
     * @return the index of the question number from TriviaGameManager
     */
    public String getAnsweredQuestion(int answeredIndex){
        return answeredQuestionsList.get(answeredIndex);
    }

    // takes input index of question asked, returns the user's selected answer
    public int getInputAnsweredByQNo(int answerIndex){
        return inputAnswersList.get(answerIndex);
    }

    public int getNumberOfQsAnswered(){
        return answeredQuestionsList.size();
    }
}

