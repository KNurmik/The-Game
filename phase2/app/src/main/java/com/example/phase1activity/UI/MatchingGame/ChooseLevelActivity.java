/*
 * Copyright 2019. Mark Vartolaş, Karl Hendrik Nurmeots, Olivia Li, Ramzi Dajani, Henry Leung.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.phase1activity.UI.MatchingGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;
import com.example.phase1activity.UI.MazeGame.MazeMenuActivity;
import com.example.phase1activity.UI.MenuScreens.StartActivity;

import java.util.ArrayList;
import java.util.List;

public class ChooseLevelActivity extends AbstractActivity {

    List<Integer> levels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);

        Button six = findViewById(R.id.level6);
        Button eight = findViewById(R.id.level8);
        Button ten = findViewById(R.id.level10);
        Button twelve = findViewById(R.id.level12);

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setMatchingGameLevel(6);
                startActivity(new Intent(ChooseLevelActivity.this, MatchingGameActivity.class));
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setMatchingGameLevel(8);
                startActivity(new Intent(ChooseLevelActivity.this, MatchingGameActivity.class));
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setMatchingGameLevel(10);
                startActivity(new Intent(ChooseLevelActivity.this, MatchingGameActivity.class));
            }
        });

        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.setMatchingGameLevel(12);
                startActivity(new Intent(ChooseLevelActivity.this, MatchingGameActivity.class));
            }
        });
    }


}
