package com.rafilutfansyah.madingunpam;

/**
 * Created by Rafi Lutfansyah on 17/04/2017.
 */

import com.rafilutfansyah.madingunpam.model.HomeModelResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("user/API")
    Call<HomeModelResult> getHomeModelResult();

    @GET("user/API")
    Call<ResponseBody> getHomeModelResultJSON();

}