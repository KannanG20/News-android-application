package com.example.infotech.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.infotech.Adapters.AdapterRV;
import com.example.infotech.Interfaces.RecyclerInterface;
import com.example.infotech.ModelClass.ArticlesData;
import com.example.infotech.ModelClass.ModelRV;
import com.example.infotech.R;
import com.example.infotech.Instance.RetrofitInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements RecyclerInterface {
    private List<ArticlesData> newsData;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeToRefresh;
    private AdapterRV adapterRV;
    private Context context;
    private TextView connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blackShaded));
        }
        connection =(TextView) findViewById(R.id.connectivity);
        recyclerView = findViewById(R.id.recyclerView);
        swipeToRefresh = findViewById(R.id.swipeToRefresh);
        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!isConnected(context)){
                    recyclerView.setVisibility(View.GONE);
                    connection.setVisibility(View.VISIBLE);
                }else{
                    connection.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    refreshItems();
                }
                swipeToRefresh.setRefreshing(false);
            }
        });
        if(!isConnected(context)){
            connection.setVisibility(View.VISIBLE);
        }else {
            fetchNewsData();
        }
    }

    private boolean isConnected(Context context){
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void fetchNewsData() {
        RetrofitInstance.getInstance().retrofitApi.getAllNews().enqueue(new Callback<ModelRV>() {
            @Override
            public void onResponse(Call<ModelRV> call, Response<ModelRV> response) {
                ModelRV modelRV = response.body();
                newsData = new ArrayList<>(Arrays.asList(modelRV.getArticles()));
                createView(newsData);
            }
            @Override
            public void onFailure(Call<ModelRV> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, NewsDetails.class);
        ArticlesData data = newsData.get(position);
        intent.putExtra("NewsPoster", data.getUrlToImage());
        intent.putExtra("title", data.getTitle());
        intent.putExtra("description", data.getDescription());
        intent.putExtra("content", data.getContent());
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void refreshItems(){
        Collections.shuffle(newsData, new Random(System.currentTimeMillis()));
        AdapterRV adapterRV =  new AdapterRV(this, newsData, this);
        recyclerView.setAdapter(adapterRV);
    }

    private void createView(List<ArticlesData> newsData){
        AdapterRV adapterRV = new AdapterRV(this,newsData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapterRV);
    }
}