package app.jorge.mobile.com.transportalert.service;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by koke on 10/01/2016.
 */
public interface TaskService {
    @GET("Mode/tube/Status")
    Call<List<StatusLine>> login(@Query("app_id") String app_id,
                                 @Query("app_key") String app_key);
}