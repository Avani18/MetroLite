package com.example.shehack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.shehack.model.Station;
import com.example.shehack.util.retrofit.ApiClient;
import com.example.shehack.util.retrofit.ApiInterface;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private SearchView searchView;
    private RecyclerView rv;
    private RecyclerViewAdapter rvAdapter;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.mylist);
        rvAdapter = new RecyclerViewAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(rvAdapter);
        fetchData();

        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
    }

    void fetchData() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getStation().enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                List<Station> stations = response.body();
                if(stations!=null) {
                    rvAdapter.setStations(stations);
                } else {
                    try {
                        Log.e(TAG, "response is null : " + response.code() + " " + response.errorBody().string());
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Log.e(TAG,"FAiled to fetch data");
                t.printStackTrace();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    class StationVH extends RecyclerView.ViewHolder {
        TextView txtvStName;
        Station station;
        public StationVH(@NonNull View itemView) {
            super(itemView);
            txtvStName = itemView.findViewById(R.id.txtv_station_name);
        }

        public void bindView(Station station, int position) {
            txtvStName.setText(station.getName());
        }
    }
    class RecyclerViewAdapter extends RecyclerView.Adapter<StationVH> {

        private List<Station> stations = new ArrayList<>();
        @NonNull
        @Override
        public StationVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_layout_stations,parent,false);
            return new StationVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StationVH holder, int position) {
            holder.bindView(stations.get(position),position);
        }

        @Override
        public int getItemCount() {
            return stations.size();
        }

        public void setStations(List<Station> stations) {
            this.stations = stations;
            notifyDataSetChanged();
        }
    }
}
