Dear TA,

For this app to run successfully, you may need to install a dependency.

To do so, follow these instructions:

1. Go to File -> Project Structure -> Dependencies -> app

2. Click on the '+' sign and select Library dependencies.

3. Search for "beandoc", this should provide a single result with Group ID "org.springframeworks"
   Select this row and click "OK".


This dependency is necessary for an imported stopwatch object, which was impossible to implement
using pre-existing Java and Android Studio packages.