package com.mibarim.driver.RestInterfaces;

import com.mibarim.driver.core.Constants;
import com.mibarim.driver.models.ApiResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by Hamed on 3/10/2016.
 */
public interface GetContactService {
    @POST(Constants.Http.URL_GET_CONTACTS)
    @FormUrlEncoded
    ApiResponse GetContacts(@Header(Constants.Http.PARAM_AUTHORIZATION) String authToken,
                            @Field("UserId") String s);

    @POST(Constants.Http.URL_GET_CONTACT_PASSENGERS)
    @FormUrlEncoded
    ApiResponse GetContactPassengers(@Header(Constants.Http.PARAM_AUTHORIZATION) String authToken,
                            @Field("TripId") String tripId);
}
