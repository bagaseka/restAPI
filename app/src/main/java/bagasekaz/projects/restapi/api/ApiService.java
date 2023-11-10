package bagasekaz.projects.restapi.api;

import java.util.ArrayList;
import java.util.List;

import bagasekaz.projects.restapi.model.Recruitment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("positions.json")
    Call<ArrayList<Recruitment>> getRecruitment();

    @GET("positions.json")
    Call<Recruitment> getListRecruitment(@Query("page") String page);

    @GET("positions.json")
    Call<List<Recruitment>> searchRecruitmentByDescription(@Query("description") String description);

    @GET("positions/{id}")
    Call<Recruitment> getIdRecruitment(@Path("id") String id);
}
