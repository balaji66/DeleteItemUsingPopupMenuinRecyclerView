package com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseClass {

    @SerializedName("CandidateList")
    @Expose
    private List<ModelClass> details = null;


    List<ModelClass> getDetails() {
        return details;
    }

    public void setDetails(List<ModelClass> details) {
        this.details = details;
    }



}
