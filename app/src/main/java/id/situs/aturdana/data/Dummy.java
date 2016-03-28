package id.situs.aturdana.data;

import java.util.ArrayList;
import java.util.List;

import id.situs.aturdana.models.Source;
import id.situs.aturdana.models.Transaction;

/**
 * Created by MF on 3/25/16.
 */
public class Dummy {

    public static List<Transaction> createTransactionList() {

        List<Transaction> result = new ArrayList<Transaction>();

        //row 1
        Transaction ci = new Transaction();
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
        ci = new Transaction();
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
        ci = new Transaction();
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

    public static List<Source> createSourceList() {

        List<Source> result = new ArrayList<Source>();

        //row 1
        Source info = new Source();
        info.setName("ATM BCA");
        info.setTotalAmount("150,000,000,000 IDR");
        info.setTotalTransaction("121 Transaksi");
        info.setTotalUser("77 User");
        info.setIconClass("{fa-credit-card-alt}");
        info.setHexColor("#E74C3C");
        result.add(info);

        //row 2
        info = new Source();
        info.setName("Dompet");
        info.setTotalAmount("27,000,000,000 IDR");
        info.setTotalTransaction("37 Transaksi");
        info.setTotalUser("77 User");
        info.setIconClass("{fa-money}");
        info.setHexColor("#3497D9");
        result.add(info);

        //row 2
        info = new Source();
        info.setName("ATM Mandiri");
        info.setTotalAmount("100,000,000 IDR");
        info.setTotalTransaction("1,891 Transaksi");
        info.setTotalUser("77 User");
        info.setIconClass("{fa-university}");
        info.setHexColor("#f39c12");
        result.add(info);

        return result;
    }
}
