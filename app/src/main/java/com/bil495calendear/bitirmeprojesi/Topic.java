package com.bil495calendear.bitirmeprojesi;

import java.util.List;


public class Topic {


    private String TopicText;
    private String TopicID;
    private String userID;



    public Topic(){

    }



    public Topic(String topicText, String topicID, String userID) {
        TopicText = topicText;
        TopicID = topicID;
        this.userID = userID;
    }



    public String getTopicText() {
        return TopicText;
    }


    public void setTopicText(String topicText) {
        TopicText = topicText;
    }


    public String getTopicID() {
        return TopicID;
    }


    public void setTopicID(String topicID) {
        TopicID = topicID;
    }


    public String getUserID() {
        return userID;
    }


    public void setUserID(String userID) {
        this.userID = userID;
    }




}
