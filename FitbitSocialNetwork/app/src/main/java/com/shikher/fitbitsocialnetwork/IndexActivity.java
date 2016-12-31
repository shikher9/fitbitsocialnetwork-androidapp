package com.shikher.fitbitsocialnetwork;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.shikher.fitbitsocialnetwork.utility.Utility;

public class IndexActivity extends AppCompatActivity {

    LinearLayout mainLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        mainLinearLayout = (LinearLayout) findViewById(R.id.activity_index);
    }

    public void onClick(View view) {

        String tag = String.valueOf(view.getTag());

        if (tag.equals("loginButton")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (tag.equals("signupButton")) {
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        }
    }
}
