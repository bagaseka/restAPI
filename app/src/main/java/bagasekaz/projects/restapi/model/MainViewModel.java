package bagasekaz.projects.restapi.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import bagasekaz.projects.restapi.api.ApiConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Recruitment> recruitmentData = new MutableLiveData<>();
    private MutableLiveData<List<Recruitment>> recruitmentListData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<List<Recruitment>> getRecruitmentListData() {
        return recruitmentListData;
    }
    public LiveData<Recruitment> getRecruitmentData() {
        return recruitmentData;
    }
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void searchRecruitmentByDescription(String description) {
        Call<List<Recruitment>> recruitmentResponseCall = ApiConfig.getApiService().searchRecruitmentByDescription(description);
        recruitmentResponseCall.enqueue(new Callback<List<Recruitment>>() {
            @Override
            public void onResponse(Call<List<Recruitment>> call, Response<List<Recruitment>> response) {
                if (response.isSuccessful()) {
                    recruitmentListData.setValue(response.body());
                } else {
                    errorMessage.setValue("Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<List<Recruitment>> call, Throwable t) {
                errorMessage.setValue("Network error");
            }
        });
    }
    public void getRecruitmentList() {
        Call<ArrayList<Recruitment>> recruitmentResponseCall = ApiConfig.getApiService().getRecruitment();
        recruitmentResponseCall.enqueue(new Callback<ArrayList<Recruitment>>() {
            @Override
            public void onResponse(Call<ArrayList<Recruitment>> call, Response<ArrayList<Recruitment>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Recruitment> recruitmentResponse = response.body();
                    recruitmentListData.setValue(recruitmentResponse);
                } else {
                    errorMessage.setValue("Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Recruitment>> call, Throwable t) {
                errorMessage.setValue("Network error");
            }
        });
    }
    public void getRecruitmentById(String id) {
        Call<Recruitment> client = ApiConfig.getApiService().getIdRecruitment(id);
        client.enqueue(new Callback<Recruitment>() {
            @Override
            public void onResponse(Call<Recruitment> call, Response<Recruitment> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        recruitmentData.setValue(response.body());
                    }
                } else {
                    errorMessage.setValue("Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<Recruitment> call, Throwable t) {
                errorMessage.setValue("Network error");
            }
        });
    }

}
