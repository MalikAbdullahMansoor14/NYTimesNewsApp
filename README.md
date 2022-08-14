# **About**

NY Times News Android App is a sample app that displays a list of 20 news. This app requires an api key that is available on New York Times developer console. User can select either 1 day, 7 or 30 days old news. The purpose of this project is to illustrate the usage of MVVM architecture design patterns that follow the best practices of Object Oriented Design Patterns using the following technology stack and dependencies.

# **Features**
List of 20 news from the New York Times for the past 1, 7 and 30 days.


# **App Architecture**
Based on MVVM architecture and repository pattern


# **App Includes**
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

# **Requirements**
- Android Studio (version 4.2+)
- Java SDK


# **Screenshots**

<p float="left">

<img src="https://user-images.githubusercontent.com/81286986/184523792-e97a1f11-9b0f-4710-920e-91c144d075cc.jpeg" width="175" height="320" />
<img src="https://user-images.githubusercontent.com/81286986/184523681-73f4398e-71b8-4192-a431-55ba8996f0fa.jpeg" width="175" height="320" />
</p>


# **How to install the project and run app and test cases**

Simply clone or download the project from github link.
Open project in Android Studio, wait for building and syncing sdk, install if anything missing and prompted.
After successful building the project, click on the play button on top and wait for the application to install on simulator or device.
To check the Junit test cases, right click on com.example.programmingtest (test) folder and choose 'Run tests with coverage'.
With coverage option will also show the coverage of the test cases in project.

![image](https://user-images.githubusercontent.com/81286986/184523580-08343f58-bd02-49ee-a7c3-46b156dbe6ce.png)


# **How to Generate Coverage Report**
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

![image](https://user-images.githubusercontent.com/81286986/184523570-142b3da0-377f-4727-abeb-8f26ef34182e.png)

 

 
