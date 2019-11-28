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
package com.example.phase1activity.UI.MazeGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phase1activity.R;
import com.example.phase1activity.UI.Abstract.AbstractActivity;
import com.example.phase1activity.UI.MenuScreens.StartActivity;

/**
 * Interface that shows the congratulations screen after user wins the maze
 */
public class MazeFinishActivity extends AbstractActivity implements View.OnClickListener{

    public Button menuButton;
    public TextView nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze_finish);
        nickName = findViewById(R.id.textView4); // get the nickname from the profile customization
        String statement = app.getProfileNickname() + " finished the game."; // adds the nick name to the output statement
        nickName.setText(statement); // sets the text to statement

        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(this);
        colourButton(menuButton, R.drawable.main_red, R.drawable.main_blue, R.drawable.main_green);
    }

    /**
     *
     * @param view The view of the maze finish xml
     */
    @Override
    public void onClick(View view) {
        app.setProfileGameLevel(this,0);
        startActivity(new Intent(MazeFinishActivity.this, StartActivity.class));
    }
}
