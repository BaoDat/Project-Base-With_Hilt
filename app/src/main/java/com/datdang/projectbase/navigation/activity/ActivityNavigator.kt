package com.datdang.projectbase.navigation.activity

import android.app.Activity
import android.util.Log
import com.datdang.projectbase.base.BaseActivityNavigator
import com.datdang.projectbase.base.BaseActivityNavigatorImpl
import com.datdang.projectbase.navigation.activity.event.ActivityNavigationEvent
import javax.inject.Inject

interface ActivityNavigator : BaseActivityNavigator

class ActivityNavigatorImpl @Inject constructor(activity: Activity) :
    BaseActivityNavigatorImpl(activity), ActivityNavigator {

    override fun navigate(event: ActivityNavigationEvent) {
        when (event) {
            is ActivityNavigationEvent.TestNavigate -> navigateTest()
        }
    }

    private fun navigateTest() {
        activity.run {
            Log.d("Testtttt", "navigateTest")
        }
    }
}