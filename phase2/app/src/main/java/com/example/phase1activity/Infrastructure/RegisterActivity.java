package com.example.phase1activity.Infrastructure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.phase1activity.Domain.Overseers.AppManager;
import com.example.phase1activity.R;
import com.example.phase1activity.Domain.UserAccess.RegisterManager;

public class RegisterActivity extends AbstractActivities {
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
                String result = registerManager.signupAction(thisActivity, getUsername(), getPassword(), app);
                handleRegisterResult(result);
            }
        });
    }

    /**
     * Given a result, the method decides what happens after a registration attempt.
     *
     * @param result A string representation of the registration attempt result.
     */
    private void handleRegisterResult(String result) {
        //Username already in database
        if (result.equals("taken username")) {
            updateInstructionText("Username is taken!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        //Password is not in valid format
        else if (result.equals("password error")) {
            updateInstructionText("Password length must be <16, and >0! No commas!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        //Password is not in valid format
        else if (result.equals("username error")) {
            updateInstructionText("Username length must be <16, and >0! No commas!", Color.RED);
            usernameText.setText("");
            passwordText.setText("");
        }
        //Registration successful
        else {
            startActivity(new Intent(RegisterActivity.this, StartActivity.class));
        }
    }

    //Gets the username and password typed into the text fields
    private String getUsername() {
        return usernameText.getText().toString();
    }

    private String getPassword() {
        return passwordText.getText().toString();
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
