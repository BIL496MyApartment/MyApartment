package com.bil495calendear.bitirmeprojesi;

public class Surveys {
    private int Response;
    private String SurveyText;
    private String VoterID;

    public Surveys(){

    }

    public Surveys(int response, String surveyText, String voterID) {
        Response = response;
        SurveyText = surveyText;
        VoterID = voterID;
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

    public void setVoterID(String voterID) {
        VoterID = voterID;
    }
}
