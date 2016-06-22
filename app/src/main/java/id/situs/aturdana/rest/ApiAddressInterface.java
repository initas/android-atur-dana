package id.situs.aturdana.rest;

import id.situs.aturdana.models.Dashboard;
import id.situs.aturdana.models.TransactionComment;
import id.situs.aturdana.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MF on 3/30/16.
 */
public interface ApiAddressInterface {

    @GET("collections/dashboard")
    Call<Dashboard> getDashboard();


    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);


    @GET("transaction-comments")
    Call<TransactionComment> getTransactionComment(@Query("transaction") int transactionId);
}
