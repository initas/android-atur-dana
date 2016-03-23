package id.situs.aturdana;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.util.ArrayList;
import java.util.List;

import id.situs.aturdana.adapters.DashboardAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Iconify.with(new FontAwesomeModule());

        setContentView(R.layout.activity_main);

        //action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //transaction list
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.transaction_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DashboardAdapter dashboardAdapter = new DashboardAdapter(createDummyTransactionList());

        recyclerView.setAdapter(dashboardAdapter);
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

    private List<TransactionInfo> createDummyTransactionList() {

        List<TransactionInfo> result = new ArrayList<TransactionInfo>();

        //row 1
        TransactionInfo ci = new TransactionInfo();
        ci.setName("Hatsune Miku");
        ci.setImageUrl("http://i0.wp.com/aramajapan.com/wp-content/uploads/2014/08/aramajapan_rena-nounen.jpg?resize=150%2C150");
        ci.setTitle("Memakai dana ATM BCA");
        ci.setAmount("50,000 IDR");
        ci.setCategoryName("Telepon");
        ci.setCategoryHexColor("#4A90E2");
        ci.setCategoryIconClass("{fa-phone}");
        ci.setTimestamp("1 jam lalu ");
        result.add(ci);

        //row 2
        ci = new TransactionInfo();
        ci.setName("Inara Risyah");
        ci.setImageUrl("http://i0.wp.com/aramajapan.com/wp-content/uploads/2014/08/aramajapan_shiraishi-mai-nogizaka48-plastic-surgery-2-e1407995151115-150x150.jpg?resize=150%2C150");
        ci.setTitle("Menambah dana ATM BCA");
        ci.setAmount("250,000 IDR");
        ci.setCategoryName("Makan");
        ci.setCategoryHexColor("#F5A623");
        ci.setCategoryIconClass("{fa-cutlery}");
        ci.setTimestamp("2 jam lalu ");
        result.add(ci);

        //row 2
        ci = new TransactionInfo();
        ci.setName("Hatsune Miku");
        ci.setImageUrl("http://i0.wp.com/aramajapan.com/wp-content/uploads/2014/08/aramajapan_rena-nounen.jpg?resize=150%2C150");
        ci.setTitle("Transfer dana ATM BCA ke BCA");
        ci.setAmount("250,000 IDR");
        ci.setCategoryName("Transfer");
        ci.setCategoryHexColor("#E74C3C");
        ci.setCategoryIconClass("{fa-random}");
        ci.setTimestamp("2 jam lalu ");
        result.add(ci);

        return result;
    }
}
