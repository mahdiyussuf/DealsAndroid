package com.interviewproject.deals.ViewModel;

import com.interviewproject.deals.Client.DealsAPI;
import com.interviewproject.deals.Client.RetrofitClient;
import com.interviewproject.deals.Model.Deals;
import com.interviewproject.deals.Model.RootObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * View Model for the MainActivity View
 */
public class DealsViewModel {

        //Retrofit interface for retrieval of deals from API
        private DealsAPI dealsAPI;

        //Image urls from the target deals API, http://lorempixel.com/400/400/, is very slow to access
        //Replaced with similar service from picsum which loads fast, and works with Glide service well
        private final String NEW_IMAGE_URL = "https://picsum.photos/400/?temp=";

        public DealsViewModel() {

            Retrofit retrofit = RetrofitClient.getInstance();
            dealsAPI = retrofit.create(DealsAPI.class);


        }


        //Returns Observable from Retrofit API on call to get new list of deals data from external service
        public Observable<RootObject> dealsObservable() {
            return dealsAPI.getDeals();
        }

        //Replaces URL within each deal's default image URL string with one from a similar service
        //that allows for faster loading and operation with Glide image service
        public List<Deals> reformatImageURLs (List<Deals> dealsList) {
            int i = 0;
            for (Deals deals : dealsList) {
                //Replace URL retrieved from target API with new one and add different number to address
                //This ensures each image loaded is different, circumventing caching by Glide service
                deals.setImage(NEW_IMAGE_URL + String.valueOf(i));
                i++;
            }

            return dealsList;
        }
}
