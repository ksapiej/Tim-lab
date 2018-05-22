package com.example.webapp;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Krzysiek on 19.05.2018.
 */

public interface ServiceAPI {

    @GET("getList/{nr}")
    Call<ArrayList<Integer>> loadList(@Path("nr") int nr);
}
