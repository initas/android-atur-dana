package id.situs.aturdana.rest;

import id.situs.aturdana.models.Dashboard;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by MF on 3/30/16.
 */
public interface ApiAddressInterface {

    @GET("dashboard.json")
    Call<Dashboard> getDashboard();
}
