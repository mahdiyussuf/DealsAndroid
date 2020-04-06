package com.interviewproject.deals.Client;

import com.interviewproject.deals.Model.RootObject;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface DealsAPI {

    @GET("deals")
    Observable<RootObject> getDeals();
}
