# The Game

A three part Android game integrated with as many design patterns as we could ~~humanly~~ android-ly fit. The Game consists of a reaction game, a matching game, and an auto-generating maze game. 

## Project Location

The app can be run by opening the 'phase 2' folder as a project in Android Studio.

All of the interesting code can be found in https://github.com/KNurmik/The-Game/tree/final/phase2/app/src/main/java/com/example/phase1activity.

## Installing dependencies

For this app to run successfully, you may need to install dependencies. Most likely this will not be an issue, but just to be sure, here are some step-by-step instructions:

1. Go to File -> Project Structure -> Dependencies -> app

2. Click on the '+' sign and select Library dependencies.

3. Search for "beandoc", this should provide a single result with Group ID "org.springframeworks" Select this row and click "OK". This dependency is necessary for an imported stopwatch object.

4. Similarly, you may have to search for "dagger", and add the result with Group ID "com.google.dagger". This dependency is necessary for implementing dependency injection.
