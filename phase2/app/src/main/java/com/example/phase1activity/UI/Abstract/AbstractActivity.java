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
package com.example.phase1activity.UI.Abstract;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.phase1activity.Core.Transmission.Overseers.AppManager;
import com.example.phase1activity.R;
import com.example.phase1activity.Core.Transmission.Overseers.AppManager;

/**
 * Base activity, every Activity in the application inherits this class
 */
public abstract class AbstractActivity extends AppCompatActivity {
    public AppManager app;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (AppManager) getApplication();
    }

    public AppManager getAppManager() {
        return app;
    }

    public void colourButton(View btn, @DrawableRes int red, @DrawableRes int blue, @DrawableRes int green){
        int colour = app.getProfileColour();

        if (colour == Color.RED){
            btn.setBackgroundResource(red);
        }
        else if (colour == Color.BLUE){
            btn.setBackgroundResource(blue);
        }
        else{
            btn.setBackgroundResource(green);
        }
    }


}
