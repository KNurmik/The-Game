Dear TA,

The complete project is located in the phase2 directory.


For this app to run successfully, you may need to install dependencies. Most likely this will not
be an issue, but just to be sure, here are step-by-step instructions:

1. Go to File -> Project Structure -> Dependencies -> app

2. Click on the '+' sign and select Library dependencies.

3. Search for "beandoc", this should provide a single result with Group ID "org.springframeworks"
   Select this row and click "OK".


This dependency is necessary for an imported stopwatch object, which was impossible to implement
using pre-existing Java and Android Studio packages.

4. Similarly, you may have to search for "dagger", and add the result with Group ID
   "com.google.dagger".

This dependency is necessary for implementing dependency injection.

Thank you for understanding!

####################################################################################################

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.