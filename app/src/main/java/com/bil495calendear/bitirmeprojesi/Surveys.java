package com.bil495calendear.bitirmeprojesi;

import java.util.List;

public class Surveys {

    private String SurveyText;
    private String SurveyID;
    private List<Integer> Response;
    private List<String> VoterID;
    public Surveys(){

    }

    public Surveys(List<Integer> response, String surveyText, List<String> voterID,String surveyID) {
        Response = response;
        SurveyText = surveyText;
        VoterID = voterID;
        SurveyID = surveyID;
    }

    public List<Integer> getResponse() {
        return Response;
    }

    public void setResponse(List<Integer> response) {
        Response = response;
    }

    public List<String> getVoterID() {
        return VoterID;
    }

    public void setVoterID(List<String> voterID) {
        VoterID = voterID;
    }

    public String getSurveyText() {
        return SurveyText;
    }

    public void setSurveyText(String surveyText) {
        SurveyText = surveyText;
    }

    public String getSurveyID() {
        return SurveyID;
    }

    public void setSurveyID(String surveyID) {
        SurveyID = surveyID;
    }

}
