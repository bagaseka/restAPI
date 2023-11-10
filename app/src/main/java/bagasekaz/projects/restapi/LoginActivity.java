package bagasekaz.projects.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import bagasekaz.projects.restapi.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private static final String mUserName = "Bagaseka";
    private static final String mPassword = "123456";

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.inputUserName.getText().toString();
                String password = binding.inputPassword.getText().toString();

                if (isValidLogin(userName,password)){
                    mSnackbar("Login Success!!");
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else if (isInvalidUsername(userName)){
                    mSnackbar("Username Not Found");
                }else{
                    mSnackbar("Wrong Password");
                }
            }
        });
    }

    private boolean isValidLogin(String username, String password) {
        return username.equals(mUserName) && password.equals(mPassword);
    }

    private boolean isInvalidUsername(String username) {
        return !username.equals(mUserName);
    }

    private void mSnackbar(String message){
        Snackbar mySnackbar = Snackbar.make(binding.layout,
                message, Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }
}