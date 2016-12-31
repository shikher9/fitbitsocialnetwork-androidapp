package com.shikher.fitbitsocialnetwork;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.shikher.fitbitsocialnetwork.model.auth.LoginRequest;
import com.shikher.fitbitsocialnetwork.model.auth.LoginResponse;
import com.shikher.fitbitsocialnetwork.network.AuthService;
import com.shikher.fitbitsocialnetwork.utility.Utility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle(getResources().getString(R.string.Login));

        /**
         * Initialize widgets
         */
        initializeWidgets();
    }

    public void onClick(View view) {

        String tag = String.valueOf(view.getTag());

        if (tag.equals("loginButton")) {
            doLogin(emailEditText.getText().toString(), passwordEditText.getText().toString());
        }
    }

    public void initializeWidgets() {
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    /**
     * Does processing for login,
     * if email or password is wrong, "wrongcredentials" string
     * is returned in the message field.
     *
     * @param email
     * @param password
     * @return
     */
    public void doLogin(final String email, String password) {

        LoginRequest loginRequest = new LoginRequest(email, password);
        AuthService authService = Utility.retrofit.create(AuthService.class);
        Call<LoginResponse> loginResponseCall = authService.loginUser(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                String message = loginResponse.getMessage();

                if (message.equals("wrongcredentials")) {
                    Utility.showShortToast("Wrong email and password", LoginActivity.this);
                } else {

                    //store email, token in the shared preferences
                    SharedPreferences.Editor editor = Utility.getSharedPreferencesEditor(LoginActivity.this);
                    editor.putString("userEmail", email);
                    editor.putString("userToken", message);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, NewsFeedActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });


    }
}
