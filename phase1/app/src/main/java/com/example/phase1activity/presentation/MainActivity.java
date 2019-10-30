<<<<<<< HEAD
package com.example.phase1activity.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.phase1activity.R;
import com.example.phase1activity.presentation.LogIn.LogInActivity;
import com.example.phase1activity.presentation.Register.RegisterActivity;

public class MainActivity extends AppCompatActivity {
=======
<<<<<<< HEAD:phase1/app/src/main/java/com/example/phase1activity/presentation/LogIn/LogInActivity.java
package com.example.phase1activity.presentation.LogIn;
=======
package com.example.phase1activity.presentation;
>>>>>>> 28193c14bcdd69d96c4ac640c30bbabe0b74a093:phase1/app/src/main/java/com/example/phase1activity/presentation/MainActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.phase1activity.R;

public class LogInActivity extends AppCompatActivity {
>>>>>>> 28193c14bcdd69d96c4ac640c30bbabe0b74a093

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_main);

        Button logInBtn = findViewById(R.id.logInButton);

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogInActivity.class));
            }
        });

        Button regBtn = findViewById(R.id.createAccountButton);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
=======
        setContentView(R.layout.activity_log_in);
>>>>>>> 28193c14bcdd69d96c4ac640c30bbabe0b74a093
    }
}
