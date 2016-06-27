package id.situs.aturdana.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.List;

import id.situs.aturdana.R;
import id.situs.aturdana.adapters.TransactionCommentAdapter;
import id.situs.aturdana.models.TransactionComment;
import id.situs.aturdana.models.User;
import id.situs.aturdana.rest.ApiAddressInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MF on 6/21/16.
 */
public class TransactionCommentActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private TransactionCommentAdapter transactionCommentAdapter;
    private Retrofit retrofit;
    private ApiAddressInterface service;
    private Integer transactionId;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_transaction_comment);

        setToolbar();

        //edit text
        description = (EditText) findViewById(R.id.etDescription);
        description.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    postComment(view);
                    return true;
                }
                return false;
            }
        });

        //intent
        Intent intent = getIntent();
        transactionId = intent.getIntExtra("transactionId", -1);

        //retrofit
        String baseUrl = getResources().getString(R.string.api_address);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiAddressInterface.class);
        Call<TransactionComment> call = service.getTransactionComment(transactionId);

        call.enqueue(new Callback<TransactionComment>() {
            @Override
            public void onResponse(Call<TransactionComment> call, Response<TransactionComment> response) {
                if (response.isSuccessful()) {
                    TransactionComment transactionComment = response.body();
                    Log.d("+++", "response = " + new Gson().toJson(transactionComment));

                    //transaction list
                    recyclerView = (RecyclerView) findViewById(R.id.transaction_comment_list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(TransactionCommentActivity.this));
                    transactionCommentAdapter = new TransactionCommentAdapter(transactionComment.getData());
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

    public void postComment(View view) {
        service = retrofit.create(ApiAddressInterface.class);

        Log.d("description+++ : ", description.getText().toString());

        Call<TransactionComment> call = service.postTransactionComment(transactionId, description.getText().toString(), "debug", "admin");

        call.enqueue(new Callback<TransactionComment>() {
            @Override
            public void onResponse(Call<TransactionComment> call, Response<TransactionComment> response) {
                if (response.isSuccessful()) {
                    TransactionComment transactionComment = response.body();
                    Log.d("+++", "response = " + new Gson().toJson(transactionComment));

                    if (transactionComment.getStatus() == 1) {
                        description.setText("");
                        transactionCommentAdapter.insert(0, transactionComment);
                        recyclerView.scrollToPosition(0);
                    } else {
                        Log.d("+++", "Validation Errors = " + new Gson().toJson(transactionComment.getErrors()));
                    }
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
                postComment(v);
            }
        });
    }
}