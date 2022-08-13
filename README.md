**About**

NY Times News Android App is a sample app that displays a list of New York Times news. The purpose of this project is to illustrate the usage of MVVM architecture design patterns that follow the best practices of Object Oriented Design Patterns using the following technology stack and dependencies.

**Features**
List of 20 news from the New York Times for the past 1, 7 and 30 days.

**Included**
1. Architecture Design Pattern
2. MVVM
2. Material Design
3. Live Data
4. Coroutines
5. Retrofit
6. Unit Testing
7. Repository Pattern
8. AndroidX
9. Glide
11. NYT News API
12. JetPack Libraries

**Requirements**
- Android Studio (version 4.2+)
- Java SDK

**How to Generate Coverage Report**
1. Added this to build.gradle
android {
    jacoco {
        version = '0.7.9'
    }
}
2. Run this command on gradle
 ./gradlew createDebugCoverageReport
3. Report will be generated at 
/build/reports/
 

**Unit Testing on Android Studio**
1.	Select the unit testing file that is created.
2.	You can either run the tests one by one or you can also run all the tests. 
3.	It will also tell you that how many tests are passed.
 
