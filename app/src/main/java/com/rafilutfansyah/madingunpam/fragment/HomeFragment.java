package com.rafilutfansyah.madingunpam.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.rafilutfansyah.madingunpam.APIService;
import com.rafilutfansyah.madingunpam.R;
import com.rafilutfansyah.madingunpam.adapter.HomeRecyclerViewAdapter;
import com.rafilutfansyah.madingunpam.model.HomeModel;
import com.rafilutfansyah.madingunpam.model.HomeModelResult;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    protected Context context;

    private List<HomeModel> homeModelList;

    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;

    FloatingActionButton floatingActionButton;
    Button buttonGetAPI;

    public HomeFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        setFloatingActionButton();

        buttonGetAPI = (Button) view.findViewById(R.id.button_get_api);
        buttonGetAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.addInterceptor(logging);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.41/laravel/public/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build();

                APIService APIService = retrofit.create(APIService.class);
                Call<HomeModelResult> homeModelResultCall = APIService.getHomeModelResult();
                homeModelResultCall.enqueue(new Callback<HomeModelResult>() {
                    @Override
                    public void onResponse(Call<HomeModelResult> call, Response<HomeModelResult> response) {
                        List<HomeModel> homeModelList = response.body().getUser();
                        for (int i = 0; i < homeModelList.size(); i++) {
                            String username = homeModelList.get(i).getUsername();
                            String name = homeModelList.get(i).getName();
                            Toast.makeText(context, username+" "+name, Toast.LENGTH_LONG).show();
                            HomeModel homeModel = new HomeModel(username, name);
                            homeModelList.add(homeModel);
                        }
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<HomeModelResult> call, Throwable t) {

                    }
                });
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_mading);

        homeModelList = new ArrayList<>();
        //testDataset();
        //initDataset();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        adapter = new HomeRecyclerViewAdapter(homeModelList, context);
        //recyclerView.setAdapter(adapter);

        return view;
    }

    private void testDataset() {
        for(int i=1; i<=10; i++) {
            HomeModel homeModel = new HomeModel("TEST", "test");
            homeModelList.add(homeModel);
        }
    }

    private void initDataset() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.123/laravel/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        APIService APIService = retrofit.create(APIService.class);
        Call<HomeModelResult> homeModelResultCall = APIService.getHomeModelResult();
        homeModelResultCall.enqueue(new Callback<HomeModelResult>() {
            @Override
            public void onResponse(Call<HomeModelResult> call, Response<HomeModelResult> response) {
                List<HomeModel> homeModelList = response.body().getUser();
                for (int i = 0; i < homeModelList.size(); i++) {
                    String username = homeModelList.get(i).getUsername();
                    String name = homeModelList.get(i).getName();
                    Toast.makeText(context, username+" "+name, Toast.LENGTH_LONG).show();
                    HomeModel homeModel = new HomeModel(username, name);
                    homeModelList.add(homeModel);
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<HomeModelResult> call, Throwable t) {

            }
        });
    }

    public void setFloatingActionButton() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Ini adalah pesan Toast", Toast.LENGTH_LONG).show();
            }
        });
    }
}

