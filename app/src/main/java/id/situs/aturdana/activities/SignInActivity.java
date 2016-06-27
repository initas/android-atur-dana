package id.situs.aturdana.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.squareup.picasso.Picasso;

import id.situs.aturdana.R;
import id.situs.aturdana.models.User;
import id.situs.aturdana.rest.ApiAddressInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MF on 6/24/16.
 */
public class SignInActivity extends BaseActivity {
    private TextView vEmail;
    private TextView vPassword;
    private Retrofit retrofit;
    private ApiAddressInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_sign_in);

        vEmail = (EditText) findViewById(R.id.email);
        vPassword = (EditText) findViewById(R.id.password);

        setToolbar();

        //retrofit
        String baseUrl = getResources().getString(R.string.api_address);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiAddressInterface.class);
    }

    @Override
    public void back(View view) {
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }

    public void signIn(final View view) {
        Call<User> call = service.postLogin(vEmail.getText().toString(), vPassword.getText().toString(), "debug");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Log.d("+++", "response = " + new Gson().toJson(user));

                    if(user.getStatus() == 1){
                        mainActivity(view);
                    }
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

    public void mainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void setToolbar() {
        TextView leftButton = (TextView) findViewById(R.id.left_button);
        TextView rightButton = (TextView) findViewById(R.id.right_button);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        leftButton.setText("{fa-arrow-left}");
        rightButton.setText("{fa-check}");
        toolbarTitle.setText("ATUR DANA");

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(v);
            }
        });
    }
}
