package com.example.phase1activity.Infrastructure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phase1activity.Domain.UserAccess.LogInManager;
import com.example.phase1activity.Domain.UserAccess.SignupManager;
import com.example.phase1activity.R;

public class LogInActivity extends AbstractActivities {
    /**
     * The manager used for logging in.
     */
    SignupManager loginManager;

    /**
     * The text field for the username
     */
    EditText usernameText;

    /**
     * The text field for the password
     */
    EditText passwordText;

    /**
     * Instructions displayed to the user
     */
    TextView instructionText;

    /**
     * The button used to attempt to login
     */
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        loginManager = new LogInManager();

        //Initializes all buttons and Text boxes
        usernameText = findViewById(R.id.UsernameText);
        passwordText = findViewById(R.id.PasswordText);
        instructionText = findViewById(R.id.logInInstructionText);
        btn = findViewById(R.id.logInButton);

        final Activity thisActivity = this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = loginManager.signupAction(thisActivity, getUsername(), getPassword(), app);
                handleLogInResult(result);
            }
        });
    }

    //Gets the username and password typed into the text fields
    private String getUsername() {
        return usernameText.getText().toString();
    }

    private String getPassword() {
        return passwordText.getText().toString();
    }

    /**
     * Given a result, the method decides what happens after a login attempt.
     *
     * @param result A string representation of the login attempt result.
     */
    private void handleLogInResult(String result) {
        //If the username field is left blank
        if (result.equals("empty username")) {
            updateInstructionText("Username cannot be empty!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        //If the password field is left blank
        else if (result.equals("empty password")) {
            updateInstructionText("Password cannot be empty!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        //If there is no match with the username or password.
        else if (result.equals("incorrect username/password")) {
            updateInstructionText("Incorrect username/password.", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        //Successful login
        else {
            startActivity(new Intent(LogInActivity.this, StartActivity.class));
        }
    }

    /**
     * Updates the instructions given to the user.
     *
     * @param toThis The new message that will be displayed for the user
     * @param color  The color of the message that will be displayed.
     */
    public void updateInstructionText(String toThis, int color) {
        instructionText.setText(toThis);
        instructionText.setTextColor(color);
    }
}
