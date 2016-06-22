package id.situs.aturdana.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.adapters.DashboardAdapter;
import id.situs.aturdana.rest.ApiAddressInterface;
import id.situs.aturdana.models.Dashboard;
import id.situs.aturdana.models.Transaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_main);

        //drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //retrofit
        String baseUrl = getResources().getString(R.string.api_address);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiAddressInterface service = retrofit.create(ApiAddressInterface.class);
        Call<Dashboard> call = service.getDashboard();

        call.enqueue(new Callback<Dashboard>() {
            @Override
            public void onResponse(Call<Dashboard> call, Response<Dashboard> response) {
                if (response.isSuccessful()) {
                    Dashboard dashboard = response.body();
                    Log.d("+++", "response = " + new Gson().toJson(dashboard));

                    //transaction list
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.transaction_list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    DashboardAdapter dashboardAdapter = new DashboardAdapter(dashboard);
                    recyclerView.setAdapter(dashboardAdapter);
                } else {
                    Log.d("+++", "Status Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Dashboard> call, Throwable t) {
                Log.d("+++", t.getMessage());
            }

        });
    }

    public void transactionDetailActivity(View view) {
        Intent intent = new Intent(this, TransactionDetailActivity.class);
        startActivity(intent);
    }

    public void toggleDrawer(View view) {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }
}