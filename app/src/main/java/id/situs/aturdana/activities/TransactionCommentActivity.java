package id.situs.aturdana.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.adapters.TransactionCommentAdapter;
import id.situs.aturdana.models.TransactionComment;
import id.situs.aturdana.rest.ApiAddressInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MF on 6/21/16.
 */
public class TransactionCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_transaction_comment);

        setToolbar();

        Intent intent = getIntent();
        final Integer transactionId = intent.getIntExtra("transactionId", -1);

        //retrofit
        String baseUrl = getResources().getString(R.string.api_address);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiAddressInterface service = retrofit.create(ApiAddressInterface.class);
        Call<TransactionComment> call = service.getTransactionComment(transactionId);

        call.enqueue(new Callback<TransactionComment>() {
            @Override
            public void onResponse(Call<TransactionComment> call, Response<TransactionComment> response) {
                if (response.isSuccessful()) {
                    TransactionComment transactionComment = response.body();
                    Log.d("+++", "response = " + new Gson().toJson(transactionComment));

                    //transaction list
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.transaction_comment_list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(TransactionCommentActivity.this));
                    TransactionCommentAdapter transactionCommentAdapter = new TransactionCommentAdapter(transactionComment.getData());
                    recyclerView.setAdapter(transactionCommentAdapter);
                } else {
                    Log.d("+++", "Status Code = " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TransactionComment> call, Throwable t) {
                Log.d("+++", t.getMessage());
            }

        });
    }

    public void back(View view) {
        this.finish();
    }

    public void setToolbar() {
        TextView leftButton = (TextView) findViewById(R.id.left_button);
        TextView rightButton = (TextView) findViewById(R.id.right_button);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        leftButton.setText("{fa-arrow-left}");
        rightButton.setText("{fa-check}");
        toolbarTitle.setText("KOMENTAR");

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(v);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signIn(v);
            }
        });
    }
}