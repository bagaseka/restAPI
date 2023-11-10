package bagasekaz.projects.restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import bagasekaz.projects.restapi.databinding.ActivityDetailBinding;
import bagasekaz.projects.restapi.model.MainViewModel;

public class DetailActivity extends AppCompatActivity {
    public static final String RECRUITMENT_ID = "recruitment_id";
    private String id;
    private ActivityDetailBinding binding;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        id = getIntent().getStringExtra(RECRUITMENT_ID);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getRecruitmentData().observe(this, recruitment -> {
            if (recruitment != null) {
                binding.company.setText(recruitment.getCompany());
                binding.location.setText(recruitment.getLocation());
                binding.jobTitle.setText(recruitment.getTitle());
                binding.description.setText(recruitment.getDescription());

                if (recruitment.getTitle().equals("Full Time")) {
                    binding.type.setText("YES");
                } else {
                    binding.type.setText("NO");
                }

                Glide.with(DetailActivity.this)
                        .load(recruitment.getCompany_logo())
                        .into(binding.image);
            }
        });

        viewModel.getErrorMessage().observe(this, errorMessage -> {
            Toast.makeText(DetailActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
        });

        viewModel.getRecruitmentById(id);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}