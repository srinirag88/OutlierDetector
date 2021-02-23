OutlierDetector

Use openjdk 15 and Gradle 6.6.1 (Running in openjdk 15)
And use the latest Intellij to run the project in IDE

To run test 
```
./gradlew test
```
To build and generate the image
```
./gradlew build
./gradlew jlink
```
This would generate the modular image and JVM and full application

Usage
```
 ./build/images/bin/outlierdetector 
    --inputFile The Input CSV file to be processed 
    --outputFile The Output CSV file for the cleaned data"
   
```