package com.example.whichbin;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class TriviaAnswerParcel implements Parcelable {
    private ArrayList<String> answeredQuestionsList;
    private ArrayList<String> extraAnswerInfoList;
    private ArrayList<Integer> inputAnswersList;
    private int score;

    public TriviaAnswerParcel(ArrayList<String> answeredQuestionsList, ArrayList<String> extraAnswerInfoList, ArrayList<Integer> inputAnswersList, int score){
        this.answeredQuestionsList = answeredQuestionsList;
        this.extraAnswerInfoList = extraAnswerInfoList;
        this.inputAnswersList =inputAnswersList;
        this.score = score;
    }

    protected TriviaAnswerParcel(Parcel in) {
        this.answeredQuestionsList = in.readArrayList(null);
        this.extraAnswerInfoList = in.readArrayList(null);
        this.inputAnswersList = in.readArrayList(null);
        this.score = in.readInt();
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
        dest.writeList(extraAnswerInfoList);
        dest.writeList(inputAnswersList);
        dest.writeInt(score);
    }


    /** takes input of index of question asked, returns the question number
     *  index from TriviaGameManager
     *
     * @param answeredIndex the order in which the question was asked-eg index 0 was the first
     * @return the index of the question number from TriviaGameManager
     */
    public String getAnsweredQuestion(int answeredIndex){
        return answeredQuestionsList.get(answeredIndex);
    }

    public String getExtraAnswerInfo(int answeredIndex){
        return extraAnswerInfoList.get(answeredIndex);
    }

    // takes input index of question asked, returns the user's selected answer
    public int getInputAnsweredByQNo(int answerIndex){
        return inputAnswersList.get(answerIndex);
    }

    public int getNumberOfQsAnswered(){
        // Previously a bug was causing extra questions to be read-this is hopefully unnecessary but stops the number of questions getting too high.
        if(answeredQuestionsList.size() > 10){
            return 10;
        }
        else {
            return answeredQuestionsList.size();
        }
    }

    public int getScore(){
        return score;
    }
}

