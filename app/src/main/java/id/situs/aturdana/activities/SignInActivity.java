package id.situs.aturdana.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import id.situs.aturdana.R;

/**
 * Created by MF on 6/24/16.
 */
public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_sign_in);

        setToolbar();
    }

    public void back(View view) {
        Intent intent = new Intent(this, LaunchActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
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
