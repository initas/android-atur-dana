package id.situs.aturdana.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

    List<Transaction> Transactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_main);

        //action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://icodea.com/aturdana/")
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
