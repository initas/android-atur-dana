package id.situs.aturdana.rest;

import id.situs.aturdana.models.Dashboard;
import id.situs.aturdana.models.Transaction;
import id.situs.aturdana.models.TransactionComment;
import id.situs.aturdana.models.User;
import id.situs.aturdana.models.Wrapper;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MF on 3/30/16.
 */
public interface ApiAddressInterface {

    @Headers("auth-token : admin")
    @GET("collections/dashboard")
    Call<Dashboard> getDashboard();


    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);

    @GET("transaction-comments")
    Call<TransactionComment> getTransactionComment(@Query("transaction") int transactionId);

    @FormUrlEncoded
    @POST("transaction-comments")
    Call<TransactionComment> postTransactionComment(
            @Field("transaction_id") int transactionId,
            @Field("description") String description,
            @Header("access-token") String accessToken,
            @Header("auth-token") String authToken
    );

    @FormUrlEncoded
    @POST("users/{userId}/pin")
    Call<Wrapper> postUserPin(
            @Path("userId") int userId,
            @Field("transaction_id") int transactionId,
            @Header("access-token") String accessToken,
            @Header("auth-token") String authToken
    );

    @FormUrlEncoded
    @POST("users/login")
    Call<User> postLogin(
            @Field("email") String email,
            @Field("password") String password,
            @Header("access-token") String accessToken
    );
}
