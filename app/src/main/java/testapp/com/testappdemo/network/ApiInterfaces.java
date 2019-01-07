package testapp.com.testappdemo.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import testapp.com.testappdemo.models.MatrimonialModel;

public interface ApiInterfaces {

    @GET("api/")
    Observable<MatrimonialModel> getMatrimonialMatches(@Query("results") String results);

}
