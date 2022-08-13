package com.example.programmingtest.ui

import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class MainActivityTest {

    //new rule
    @Rule
    var mainActivityActivityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule<MainActivity>(
            MainActivity::class.java
        )
    private var mainActivity: MainActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mainActivity = mainActivityActivityTestRule.getActivity()
    }

    @After
    fun tearDown() {
        mainActivity = null
    }

    @Test
    fun testLaunch() {
    }
}