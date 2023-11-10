package bagasekaz.projects.restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import bagasekaz.projects.restapi.adapter.ListAdapter;
import bagasekaz.projects.restapi.databinding.ActivityMainBinding;
import bagasekaz.projects.restapi.model.MainViewModel;
import bagasekaz.projects.restapi.model.Recruitment;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Recruitment> recruitments = new ArrayList<>();
    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRv();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getRecruitmentListData().observe(this, recruitmentList -> {
            if (recruitmentList != null) {
                ListAdapter listAdapter = new ListAdapter(recruitmentList);
                binding.rvRecruitment.setAdapter(listAdapter);
            }
        });

        viewModel.getErrorMessage().observe(this, errorMessage -> {
            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
        });

        viewModel.getRecruitmentList();

        findRecuitmentList();
    }

    private void findRecuitmentList(){
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchRecruitmentByDescription(newText);
                return false;
            }
        });

    }
    private void initRv() {
        binding.rvRecruitment.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRecruitment.setHasFixedSize(true);
    }
}