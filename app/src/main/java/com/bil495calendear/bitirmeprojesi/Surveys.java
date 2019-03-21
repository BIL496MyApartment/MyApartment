package com.bil495calendear.bitirmeprojesi;

public class Surveys {
    private int Response;
    private String SurveyText;
    private String VoterID;
    private int SurveyID;

    public Surveys(){

    }

    public Surveys(int response, String surveyText, String voterID,int surveyID) {
        Response = response;
        SurveyText = surveyText;
        VoterID = voterID;
        SurveyID = surveyID;
    }

    public int getResponse() {
        return Response;
    }

    public void setResponse(int response) {
        Response = response;
    }

    public String getSurveyText() {
        return SurveyText;
    }

    public void setSurveyText(String surveyText) {
        SurveyText = surveyText;
    }

    public String getVoterID() {
        return VoterID;
    }

    public int getSurveyID() {
        return SurveyID;
    }

    public void setSurveyID(int surveyID) {
        SurveyID = surveyID;
    }

    public void setVoterID(String voterID) {
        VoterID = voterID;
    }
}
