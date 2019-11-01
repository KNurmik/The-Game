package com.example.phase1activity.presentation.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phase1activity.Profile.AppManager;
import com.example.phase1activity.R;
import com.example.phase1activity.presentation.MainMenu.StartActivity;

// TODO: duplicate code in RegisterActivity and LoginActivity
public class RegisterActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;
    Button btn;
    TextView instructionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final RegisterManager registerManager = new RegisterManager();
        final AppManager app = (AppManager) getApplication();

        usernameText = findViewById(R.id.NewUsernameText);
        passwordText = findViewById(R.id.NewPasswordText);
        instructionText = findViewById(R.id.logInInstructionText);
        btn = findViewById(R.id.RegisterButton);

        final Activity thisActivity = this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = registerManager.registerAction(thisActivity, getUsername(), getPassword(), app);
                handleRegisterResult(result);
            }
        });
    }

    private void handleRegisterResult(String result) {
        if (result.equals("taken username")) {
            updateInstructionText("Username is taken!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        } else if (result.equals("password length error")) {
            updateInstructionText("Password length must be <16, and >0!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        } else if (result.equals("username length error")) {
            updateInstructionText("Username length must be <16, and >0!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        } else {
            startActivity(new Intent(RegisterActivity.this, StartActivity.class));
        }
    }

    private String getUsername(){
        return usernameText.getText().toString();
    }

    private String getPassword(){
        return passwordText.getText().toString();
    }

    public void updateInstructionText(String toThis, int color) {
        instructionText.setText(toThis);
        instructionText.setTextColor(color);
    }
}
