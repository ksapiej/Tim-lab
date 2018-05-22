package com.example.webapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Krzysiek on 19.05.2018.
 */

public class Controller implements Callback<ArrayList<Integer>> {
    static final String BASE_URL = "http://172.23.176.68:8080/api/";
    private ArrayList<Integer> intList;
    private MainActivity mainActivity;

    public Controller(MainActivity activity) {
        super();
        this.mainActivity = activity;
    }

    public void start(int val) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ServiceAPI serviceAPI = retrofit.create(ServiceAPI.class);

        Call<ArrayList<Integer>> call = serviceAPI.loadList(val);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ArrayList<Integer>> call, Response<ArrayList<Integer>> response) {
        if (response.isSuccessful()) {
            intList = response.body();
            mainActivity.updateList();
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Integer>> call, Throwable t) {
        t.printStackTrace();
    }

    public ArrayList<Integer> getIntList() {
        return intList;
    }
}
