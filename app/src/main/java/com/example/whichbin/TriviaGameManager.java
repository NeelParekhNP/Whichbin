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
        TriviaQuestion q1 = new TriviaQuestion("Climate Change was theorised as early as 1896.",
                "Swedish chemist Svante Arrhenius published a paper in 1896 based on the idea that global temperatures were influenced by the amount of carbon dioxide in the Earth's atmosphere.",
                true);
        TriviaQuestion q2 = new TriviaQuestion("Greenhouse gases are gases which trap heat energy in the earth's atmosphere.",
                "Carbon dioxide is the greenhouse gas having most impact because of human activity, others include methane, nitrus oxide, hydroflourocarbons (HFCs) and perfluorocarbons (PFCs).",
                true);
        TriviaQuestion q3 = new TriviaQuestion("Everyone agreed the Kyoto agreement was effective at stopping climate change.",
                "New Scientist magazine described its results as a mixed, noting that global emissions rose between 1997 and 2016.",
                false);
        TriviaQuestion q4 = new TriviaQuestion("Deforestation contributes to emissions about as much as cars.",
                "Deforestation is very bad for the climate as it destroys trees which absorb greenhouse gases like carbon dioxide and, by doing so, releases the carbon dioxide they have absorbed.",
                true);
        TriviaQuestion q5 = new TriviaQuestion("Being a vegan is better for the environment",
                "Even beef cattle not raised on deforested land creates 6 to 12 times as much greenhouse gas as peas. Decreasing the amount of meat and dairy you consume has a big impact.",
                true);
        TriviaQuestion q6 = new TriviaQuestion("Cars are better for the environment than other forms of transport",
                "Walking and cycling are great for short journeys as they don't use fossil fuels. For longer journeys more people sharing transport (like trains and buses) produce less greenhouse gas per person than cars.",
                false);
        TriviaQuestion q7 = new TriviaQuestion("Glaciers are generally growing as a consequence of climate change",
                "Climate change is complex and not all glaciers are getting smaller but most are. As of 2016, glaciers in the European alps have lost approximately half their volume since 1900.",
                false);
        TriviaQuestion q8 = new TriviaQuestion("Cows contribute to methane emissions",
                "Cows have micro organisms in their stomachs which help then break down their food. The waste product of this process is methane which they emit as burps and farts!",
                true);
        TriviaQuestion q9 = new TriviaQuestion("Methane traps more heat than the same amount of carbon dioxide",
                "Methane traps a lot more heat in the atmosphere than carbon dioxide, some scientists think it's 84 times more powerful as a greenhouse gas than carbon dioxide though it doesn't stay in the atmosphere as long.",
                true);
        TriviaQuestion q10 = new TriviaQuestion("Much less carbon dioxide than methane is put into the atmosphere each year",
                "The US emitted over eight times more carbon dioxide than methane in 2017.",
                false);
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

    public String getTriviaQuestionByNo(Integer qNo){
        TriviaQuestion nowQuestion = triviaArray.get(qNo);
        return nowQuestion.triviaGetQuestion();
    }

    public String getExtraAnswerInfo(Integer qNo){
        TriviaQuestion nowQuestion = triviaArray.get(qNo);
        return nowQuestion.triviaGetAnswerInfo();
    }

    /** Getter method, input the question number and this method should return if the correct answer is true or false*/
    public boolean checkTriviaQuestionAnswer(Integer qNo){
        TriviaQuestion nowQuestion = triviaArray.get(qNo);
        return nowQuestion.triviaCheckTrueFalse();
    }
}

