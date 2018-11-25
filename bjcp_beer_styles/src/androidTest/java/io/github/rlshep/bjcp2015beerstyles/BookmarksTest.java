package io.github.rlshep.bjcp2015beerstyles;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BookmarksTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Test
    public void setup_bookmarks_and_persist() {
        onData(anything()).inAdapterView(withId(R.id.categoryListView)).atPosition(0).perform(longClick());
        onView(withId(R.id.pager)).perform(swipeLeft());    // Bookmarks

        onData(anything()).inAdapterView(withId(R.id.bookmarkedListView)).atPosition(1).check(matches(anything()));

        onView(withId(R.id.pager)).perform(swipeLeft());    // Color Chart
        onView(withId(R.id.pager)).perform(swipeRight());    // Back to Bookmarks

        onData(anything()).inAdapterView(withId(R.id.bookmarkedListView)).atPosition(1).check(matches(anything()));
    }
}