package com.example.whichbin;

import android.widget.Switch;

import java.util.ArrayList;

public class TriviaGameManager {

    private ArrayList<TriviaQuestion> triviaArray;

    /** Constructor method to set up the game and get it running. */
    public TriviaGameManager(){
        triviaArray = new ArrayList<>();

        createQuestions(3);
    }

    /** Method to populate the questions */
    public void createQuestions(int caseNumber){
        switch (caseNumber) {
            // Science questions
            case 1:{
                // Information sources: https://climate.nasa.gov/scientific-consensus/ & https://www.theguardian.com/environment/blog/2009/feb/19/climate-change-arrhenius
                TriviaQuestion q1 = new TriviaQuestion("Climate Change was theorised as early as 1896.",
                        "Swedish chemist Svante Arrhenius published a paper in 1896 based on the idea that global temperatures were influenced by the amount of carbon dioxide in the Earth's atmosphere. " +
                                "Today 97% of scientists agree climate change is due to human caused emissions of greenhouse gases.",
                        true);
                // Information sources: https://www.learner.org/courses/envsci/unit/text.php?unit=2&secNum=4
                TriviaQuestion q2 = new TriviaQuestion("Greenhouse gases are gases which trap heat energy in the earth's atmosphere.",
                        "Carbon dioxide is the greenhouse gas having most impact because of human activity, others include methane, nitrus oxide, hydroflourocarbons (HFCs) and perfluorocarbons (PFCs).",
                        true);
                // Information sources: https://www.space.com/17683-earth-atmosphere.html
                TriviaQuestion q3 = new TriviaQuestion("As of 2019, greenhouse gases make up a quarter of the atmosphere.",
                        "The overwhelming majority of the atmosphere is not made of greenhouse gas-78% is nitrogen and 21% is oxygen. " +
                                "Greenhouse gases make up less than 1% of the but they have a very powerful effect on global temperature.",
                        false);
                // Information sources: https://www.epa.gov/ghgemissions/overview-greenhouse-gases
                TriviaQuestion q4 = new TriviaQuestion("Much less carbon dioxide than methane is put into the atmosphere each year.",
                        "The US emitted over eight times more carbon dioxide than methane in 2017.",
                        false);
                // Information sources: https://www.scientificamerican.com/article/how-bad-of-a-greenhouse-gas-is-methane/ & https://climatechange.lta.org/get-started/learn/co2-methane-greenhouse-effect/
                TriviaQuestion q5 = new TriviaQuestion("Methane traps more heat than the same amount of carbon dioxide.",
                        "Methane traps a lot more heat in the atmosphere than carbon dioxide, some scientists think it's 84 times more powerful as a greenhouse gas than carbon dioxide " +
                                "though it doesn't stay in the atmosphere as long.",
                        true);
                // Information sources: https://www.britannica.com/list/5-notorious-greenhouse-gases
                TriviaQuestion q6 = new TriviaQuestion("Water vapour doesn’t contribute to global warming.",
                        "In fact, water vapour is the most powerful greenhouse gas but it’s not directly controlled by human activity. How much water vapor goes into the atmosphere is a " +
                                "result of global temperatures so, if other greenhouse gases increase the temperature, there’s a knock-on effect of more water vapour in the atmosphere causing additional " +
                                "global warming.",
                        false);
                // Information sources: https://earthobservatory.nasa.gov/features/GlobalWarming/page3.php & https://www.livescience.com/58891-why-2-degrees-celsius-increase-matters.html
                TriviaQuestion q7 = new TriviaQuestion("In the past century the Earth’s average temperature has increased by 0.7 degrees Celsius.",
                        "While 0.7 degrees may not seem like much of a difference locally as a global change this is massive! To put this amount in perspective, the global average temperature during " +
                                "the last ice age (ending 15,000 years ago) was about 5 degrees lower than the average today-a few degrees change in the global average is a big deal!",
                        true);
                // Information sources: https://www.nationalgeographic.com/environment/global-warming/methane/
                TriviaQuestion q8 = new TriviaQuestion("Cows contribute to methane emissions",
                        "Cows have micro organisms in their stomachs which help then break down their food. The waste product of this process is methane which they emit as burps and farts!",
                        true);
                // Information sources: https://www.nationalgeographic.com/environment/global-warming/deforestation/?utm_source=youtube&utm_medium=social&utm_content=yt20190403-environment-deforestation&utm_campaign=editorial&utm_rd=&cmpid=org=ngp::mc=social::src=youtube::cmp=editorial::add=yt20190403-environment-deforestation::urid=
                TriviaQuestion q9 = new TriviaQuestion("Deforestation contributes to emissions about as much as cars.",
                        "Deforestation is very bad for the climate as it destroys trees which absorb greenhouse gases like carbon dioxide and, by doing so, releases the carbon dioxide they have absorbed.",
                        true);
                // Information sources: https://www.bbc.co.uk/news/newsbeat-46107843 & https://www.nationalgeographic.org/encyclopedia/ozone-layer/
                TriviaQuestion q10 = new TriviaQuestion("The ozone layer (part of the Earth’s atmosphere which filters out harmful rays from the sun) is getting more and more damaged by pollution.",
                        "The ozone layer is actually healing! It was being damaged by manmade chemicals called chlorofluorocarbons (or CFC’s) from things like old types of fridges. Thanks to an " +
                                "international agreement in the 1970’s, less of these harmful chemicals have been emitted and the damage might be completely reversed by the 2030’s!",
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
                break;
            }
            // Human caused impact of climate change on nature questions
            case 2: {
                // Information sources: https://www.eea.europa.eu/data-and-maps/indicators/glaciers-2/assessment
                TriviaQuestion q1 = new TriviaQuestion("Glaciers are generally growing as a consequence of climate change.",
                        "Climate change is complex and not all glaciers are getting smaller but most are. As of 2016, glaciers in the European alps have lost approximately half their " +
                                "volume since 1900.",
                        false);
                // Information sources: https://beekeepersnaturals.com/blogs/blog/climate-change-hurting-bees & https://www.iberdrola.com/environment/climate-change-endangered-species
                TriviaQuestion q2 = new TriviaQuestion("Bees are thriving in warmer temperatures.",
                        "In the areas further south where bees used to live they are dying off because of the heat. Unlike some other insects (like butterflies) bees are unlikely to relocate." +
                                "Bees are very important for pollinating plants, including 71% of the plants we eat, so this is bad news!",
                        false);
                // Information sources: https://www.nationalgeographic.com/environment/2019/01/climate-change-drives-migration-crisis-in-bangladesh-from-dhaka-sundabans/
                TriviaQuestion q3 = new TriviaQuestion("Climate change is causing increased risk of flooding.",
                        "As the global temperature rises, icy areas are melting and the water in the oceans is expanding as it heats up; this is causing sea levels to rise " +
                                "and disrupting weather patterns. This is especially bad news for low lying areas like Bangladesh where 13.3 million people could be have to leave their homes by 2050.",
                        true);
                // Information sources: https://www.bbc.co.uk/news/science-environment-48827490
                TriviaQuestion q4 = new TriviaQuestion("An area of trees the size of a football pitch is cut down from the amazon rainforest every minute.",
                        "Trees are frequently pushed over by bulldozers or flattened by a chain pulled between two vehicles. Trees are import as they pull carbon dioxide from the air and store " +
                                "it, so there’s less in the atmosphere. Less trees means more greenhouse gases heating the planet.",
                        true);
                // Information sources: http://news.bbc.co.uk/earth/hi/earth_news/newsid_9455000/9455661.stm
                TriviaQuestion q5 = new TriviaQuestion("Red foxes have a hard time adapting to climate change.",
                        "Actually red foxes are among the species dealing better with climate change as they have adapted by moving to new areas. Their northern cousins, the arctic foxes, aren’t " +
                                "as able to adapt to climate change though as they are built for cold weather and depend on the sea ice to move around.",
                        false);
                // Information sources: https://www.sciencedaily.com/terms/fossil_fuel.htm
                TriviaQuestion q6 = new TriviaQuestion("Oil is the decayed remains of plants and animals.",
                        "This is why it’s called a fossil fuel. Other fossil fuels include natural gas (released by the decaying organisms) diesel, petrol and other fuel oils. These fuels are " +
                                "used to power transportation and generate electrical power but all produce carbon dioxide, creating global warming.",
                        true);
                // Information sources: https://www.theguardian.com/environment/2012/apr/17/shale-gas-fracking-uk & https://gimletmedia.com/shows/science-vs/v4hew5
                TriviaQuestion q7 = new TriviaQuestion("Burning natural gas produces more carbon dioxide than burning oil.",
                        "While it does produce some carbon dioxide, burning natural gas creates less than oil and coal. Some people argue it’s the least bad fossil fuel but it’s not that simple. " +
                                "Frequently greenhouse gases including methane escape during the extraction of natural gas so it’s unclear whether it’s better than other fossil fuels.",
                        false);
                // Information sources: https://www.ucsusa.org/global-warming/science-and-impacts/impacts/causes-of-drought-climate-change-connection.html
                TriviaQuestion q8 = new TriviaQuestion("Climate change will produce more floods but also and more droughts.",
                        "The impact of climate change on the weather is complicated. Climate and weather both describe the conditions of the atmosphere-climate in the long term and weather in the " +
                                "short term. Weather changes are likely to include less snow, which usually melts to provide water in the summer and more evaporation-causing drought.",
                        true);
                // Information sources: https://www.worldwildlife.org/pages/everything-you-need-to-know-about-coral-bleaching-and-how-we-can-stop-it
                // & https://www.cntraveler.com/gallery/10-places-to-visit-before-theyre-lost-to-climate-change & https://www.bbc.co.uk/programmes/articles/3ShcTJ032pp1z2mmkBJMhLX/10-amazing-facts-about-coral-reefs-and-one-terrible-truth
                TriviaQuestion q9 = new TriviaQuestion("Coral bleaching is spilled chemicals turning these underwater plants white.",
                        "Corals are actually classed as animals, though they house microscopic algae which give them their vibrant colours. Coral bleaching happens when increased ocean " +
                                "temperatures and acidity (caused by the ocean absorbing) make them eject the algae.  50% of the Great Barrier Reef in Australia has died due to climate change.",
                        false);
                // Information sources: https://environmentjournal.online/articles/uks-longest-ever-coal-free-run-comes-to-an-end/ & https://www.bbc.co.uk/news/business-48215896
                TriviaQuestion q10 = new TriviaQuestion("Britain has been burning coal for electricity continuously for the last 100 years.",
                        "Britain managed 18 days without coal beginning in May 2019. This was the first time the country had gone over a full week without coal since the 1880’s. " +
                                "Many countries are using less fossil fuels and more environmentally friendly alternatives like solar power and hydroelectric energy which don’t produce greenhouse gases.",
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
                break;
            }
            // What we can do to stop climate change questions
            case 3:{
                // Information sources: https://www.theguardian.com/environment/2018/may/31/avoiding-meat-and-dairy-is-single-biggest-way-to-reduce-your-impact-on-earth
                TriviaQuestion q1 = new TriviaQuestion("Being a vegan is better for the environment.",
                        "Even beef cattle not raised on deforested land creates 6 to 12 times as much greenhouse gas as peas. Decreasing the amount of meat and dairy you consume has a big impact.",
                        true);
                // Information sources: http://www.bbc.com/future/story/20181102-what-can-i-do-about-climate-change
                TriviaQuestion q2 = new TriviaQuestion("Cars are better for the environment than most other forms of transport.",
                        "Walking and cycling are great for short journeys as they don't use fossil fuels. For longer journeys more people sharing transport (like trains and buses) produce less greenhouse gas per person than cars.",
                        false);
                // Information sources: https://www.bbc.co.uk/news/uk-scotland-48257019
                TriviaQuestion q3 = new TriviaQuestion("In Scotland twice as much plastic as food waste was thrown away in 2016.",
                        "People in Scotland threw out 456,000 tonnes of food waste compared to 224,000 tonnes of plastic. Food that’s thrown away rots and releases carbon dioxide, so by buying" +
                                " less food and eating leftovers people can save money and reduce the amount of greenhouse gas they cause. Using less plastic and recycling what you do use is great too though.",
                        false);
                // Information sources: https://www.theguardian.com/environment/2018/may/21/human-race-just-001-of-all-life-but-has-destroyed-over-80-of-wild-mammals-study
                // & https://www.economist.com/graphic-detail/2011/07/27/counting-chickens
                TriviaQuestion q4 = new TriviaQuestion("Just under a third of the biomass (weight of) mammals in the world is made up of livestock like cows, sheep and pigs.",
                        "Of the biomass of the warm-blooded, milk-producing animals we call mammals, a whopping 60% is made up of livestock. Humans make up 36% with only 4% being wild animals. " +
                                "A 2011 estimate said there are 1.4 billion cows, 1 billion sheep and 1 billion pigs. Reducing consumption of dairy can help prevent climate change.",
                        false);
                // Information sources: https://www.transportenvironment.org/sites/te/files/publications/2018_04_CO2_emissions_cars_The_facts_report_final_0_0.pdf
                TriviaQuestion q5 = new TriviaQuestion("Carbon emissions from transport have increased in Europe since 1990.",
                        "Other polluters like agriculture, industry and power generation have all lowered their environmental impact. Transport has on average increased its impact. " +
                                "Where possible, people can share one car instead of taking two to reduce their impact.",
                        true);
                // Information sources: https://iopscience.iop.org/article/10.1088/1748-9326/aa7541
                TriviaQuestion q6 = new TriviaQuestion("Someone reduces their carbon footprint (the amount of carbon dioxide emissions they cause) more by eating a plant-based diet than by switching to a green energy provider.",
                        "A scientific study estimated that on average people emit 1.5 tonnes of carbon dioxide less a year by buying green energy whereas eating a plant-based diet saved " +
                                "0.8 tonnes. Both prevent climate change though.",
                        false);
                // Information sources: https://iopscience.iop.org/article/10.1088/1748-9326/aa7541
                TriviaQuestion q7 = new TriviaQuestion("Washing clothes in cold water reduces your carbon footprint more than recycling.",
                        "These are fairly close with washing clothes in cold was reducing carbon dioxide emissions by 0.247 tonnes per year and recycling reducing it by 0.2125. " +
                                "Every little helps though so even reducing the temperature you run your washing machine at and recycling a few extra items is good!",
                        true);
                // Information sources: https://oxfamapps.org/media/press_release/2016-06-over-three-billion-clothes-left-unworn-in-the-nations-wardrobes-survey-finds/ & https://sharecloth.com/blog/reports/apparel-overproduction
                TriviaQuestion q8 = new TriviaQuestion("The average person in the UK has an average of 11 items of clothing with the tags still on in their wardrobe.",
                        "Fashion produces a lot of greenhouse gas as oil is used to produce polyester fabrics in energy intensive processs and growing cotton requires lots of water. " +
                                "Only buying clothes you will wear, buying second hand clothes and wearing them for longer can all help fight climate change.",
                        true);
                // Information sources: https://www.edenproject.com/learn/for-everyone/water-saving-tips & https://www.south-staffs-water.co.uk/help-and-advice/read/how-does-saving-water-help-the-environment-200100000002621
                TriviaQuestion q9 = new TriviaQuestion("Turning off the tap when you brush your teeth can save 6 litres of water a minute.",
                        "A lot of energy is used in for treating and pumping water and some of this energy comes from fossil fuel sources. Using less water, in ways like taking shorter showers, " +
                                "only running the tap for as long as you need and not overfilling the kettle, reduces your carbon footprint and saves money.",
                        true);
                // Information sources: https://transitionnetwork.org/news-and-blog/totnes-declares-a-climate-ecological-emergency-what-next-event-report/
                TriviaQuestion q10 = new TriviaQuestion("Voting in a general election is the only way to impact the government’s decisions about climate change.",
                        "Voting is a great way to influence government decisions on climate change but there are many others like protest marches, emailing the government and signing petitions. " +
                                "Some towns are working at a local level to change their environmental impact like Totnes in Devon which declared its own climate emergency.",
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
                break;
            }
        }
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

