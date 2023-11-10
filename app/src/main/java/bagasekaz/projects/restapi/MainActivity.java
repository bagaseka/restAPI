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

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRv();
        getRecruitments();
        findRecruitmentList();

    }

    private void getRecruitments(){
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
    }
    private void findRecruitmentList(){
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchRecruitmentByDescription(newText);
                return true;
            }
        });

    }
    private void initRv() {
        binding.rvRecruitment.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRecruitment.setHasFixedSize(true);
    }
}