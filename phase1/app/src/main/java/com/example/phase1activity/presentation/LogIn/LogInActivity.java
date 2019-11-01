package com.example.phase1activity.presentation.LogIn;

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
import com.example.phase1activity.Profile.CustomizationActivity;
import com.example.phase1activity.R;
import com.example.phase1activity.presentation.MainActivity;
import com.example.phase1activity.presentation.MainMenu.MainMenuActivity;
import com.example.phase1activity.presentation.MainMenu.StartActivity;
import com.example.phase1activity.presentation.Register.RegisterActivity;

public class LogInActivity extends AppCompatActivity {

    LogInInterface logInInterface;
    EditText usernameText;
    EditText passwordText;
    TextView instructionText;
    Button btn;
    AppManager app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        logInInterface = new LogInManager();
        app = (AppManager) getApplication();

        usernameText = findViewById(R.id.UsernameText);
        passwordText = findViewById(R.id.PasswordText);
        instructionText = findViewById(R.id.logInInstructionText);
        btn = findViewById(R.id.logInButton);

        final Activity thisAcitivity = this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = logInInterface.logInAction(thisAcitivity, getUsername(),getPassword(), app);
                handleLogInResult(result);
            }


        });



    }

    private String getUsername(){
        return  usernameText.getText().toString();
    }

    private String getPassword(){
        return passwordText.getText().toString();
    }

    private void handleLogInResult(String result){
        if(result.equals("empty username")){
            updateInstructionText("Username cannot be empty!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        else if(result.equals("empty password")){
            updateInstructionText("Password cannot be empty!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        else if(result.equals("incorrect username/password")){
            updateInstructionText("Incorrect username/password.", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        else{
            startActivity(new Intent(LogInActivity.this, StartActivity.class));
            finish();
        }
    }

    public void updateInstructionText(String toThis, int color) {
        instructionText.setText(toThis);
        instructionText.setTextColor(color);
    }
}
