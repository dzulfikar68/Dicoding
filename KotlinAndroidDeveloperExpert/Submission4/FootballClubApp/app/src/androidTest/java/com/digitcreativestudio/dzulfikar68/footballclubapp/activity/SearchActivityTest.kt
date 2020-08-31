package com.digitcreativestudio.dzulfikar68.footballclubapp.activity

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.digitcreativestudio.dzulfikar68.footballclubapp.R
import kotlinx.coroutines.delay
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppBehaviour() {
        Espresso.onView(ViewMatchers.withId(R.id.et_search))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.et_search)).perform(ViewActions.typeText("liverpool vs barcelona"))
        Espresso.onView(ViewMatchers.withId(R.id.btn_search)).perform(ViewActions.click())
        delay(3)

        Espresso.onView(ViewMatchers.withId(R.id.list_search))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay(3)
//        Espresso.onView(ViewMatchers.withId(R.id.list_search)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Espresso.onView(ViewMatchers.withId(R.id.list_search)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        delay(3)
//
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay(2)
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        delay(2)
        Espresso.pressBack()
        Espresso.pressBack()
        delay(3)

        Espresso.onView(ViewMatchers.withId(R.id.bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay(2)
        Espresso.onView(ViewMatchers.withId(R.id.favorites)).perform(ViewActions.click())
        delay(5)
    }

    private fun delay(second: Long = 1) {
        Thread.sleep(1000 * second)
    }
}