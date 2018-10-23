package com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview.APIs;


import com.durga.balaji66.deleteitemusingpopupmenuinrecyclerview.ResponseClass;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {


    // Delete Particular Candidate

    @FormUrlEncoded
    @POST("DeleleParticularCandidate")
    Call<ResponseBody> DeleteParticularCandidate(
            @Field("UId") String uid
    );


    //getting candidate list

    @GET("CandidateList")
    Call<ResponseClass> CandidateList();

}
