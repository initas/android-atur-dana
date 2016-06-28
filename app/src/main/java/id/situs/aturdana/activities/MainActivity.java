package id.situs.aturdana.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.squareup.picasso.Picasso;

import id.situs.aturdana.R;
import id.situs.aturdana.adapters.DashboardAdapter;
import id.situs.aturdana.rest.ApiAddressInterface;
import id.situs.aturdana.models.Dashboard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkLogin();
    }

    @Override
    public void onLogin() {

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_main);

        setToolbar();

        //drawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.inflateHeaderView(R.layout.drawer);

        SharedPreferences sp1 = this.getSharedPreferences("Login", 0);
        TextView fullName = (TextView) hView.findViewById(R.id.fullName);
        TextView username = (TextView) hView.findViewById(R.id.username);
        ImageView photo = (ImageView) hView.findViewById(R.id.imageView);

        fullName.setText(sp1.getString("fullName", "kamu"));
        username.setText(sp1.getString("username", "kamu"));

        String photoValue = sp1.getString("image", null);

        if (photoValue != null) {
            Context context = photo.getContext();
            Picasso.with(context).load(Uri.parse(photoValue)).into(photo);
        }


        //retrofit
        String baseUrl = getResources().getString(R.string.api_address);

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ApiAddressInterface service = retrofit.create(ApiAddressInterface.class);
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
                    DashboardAdapter dashboardAdapter = new DashboardAdapter(dashboard, retrofit, service);
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

    public void signOut(View view) {
        getSharedPreferences("Login", 0).edit().clear().commit();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void toggleDrawer(View view) {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public void setToolbar() {
        TextView leftButton = (TextView) findViewById(R.id.left_button);
        TextView rightButton = (TextView) findViewById(R.id.right_button);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        leftButton.setText("{fa-bars}");
        rightButton.setText("{fa-plus}");
        toolbarTitle.setText("DASHBOARD");

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer(v);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transactionDetailActivity(v);
            }
        });
    }
}