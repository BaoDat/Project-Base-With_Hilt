package com.datdang.projectbase.base

import android.app.Activity
import com.datdang.projectbase.navigation.event.ActivityNavigationEvent

interface BaseActivityNavigator {

    fun navigate(event: ActivityNavigationEvent)

}

abstract class BaseActivityNavigatorImpl(
    protected val activity: Activity
) : BaseActivityNavigator