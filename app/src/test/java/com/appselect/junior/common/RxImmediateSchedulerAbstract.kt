package com.appselect.junior.common

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appselect.junior.rule.RxRule
import org.junit.ClassRule
import org.junit.Rule

abstract class RxImmediateSchedulerAbstract {
    companion object{
        @ClassRule
        @JvmField
        val schedulers = RxRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
}