package id.situs.aturdana.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import id.situs.aturdana.R;

/**
 * Created by MF on 5/20/16.
 */
public class TransactionDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_transaction_detail);

    }

    public void back(View view) {
        this.finish();
    }
}
