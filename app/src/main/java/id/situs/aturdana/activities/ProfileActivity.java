package id.situs.aturdana.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.HashMap;
import java.util.Map;

import id.situs.aturdana.R;
import id.situs.aturdana.adapters.DashboardAdapter;
import id.situs.aturdana.models.Dashboard;
import id.situs.aturdana.models.User;
import id.situs.aturdana.rest.ApiAddressInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MF on 5/20/16.
 */
public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Integer userId = intent.getIntExtra("userId", -1);

        //retrofit
        String baseUrl = getResources().getString(R.string.api_address);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiAddressInterface service = retrofit.create(ApiAddressInterface.class);
        Call<User> call = service.getUser(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Log.d("+++", "response = " + new Gson().toJson(user));

                    TextView name = (TextView)findViewById(R.id.name);
                    TextView username = (TextView)findViewById(R.id.username);
                    ImageView image = (ImageView)findViewById(R.id.imageView);

                    name.setText(user.getFullName());
                    username.setText(user.getUsername());


                    Context context = image.getContext();
                    String imageUrl = user.getImage().getOriginal();
                    Picasso.with(context).load(Uri.parse(imageUrl)).into(image);

                } else {
                    Log.d("+++", "Status Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("+++", t.getMessage());
            }

        });

    }

    public void back(View view) {
        this.finish();
    }
}
