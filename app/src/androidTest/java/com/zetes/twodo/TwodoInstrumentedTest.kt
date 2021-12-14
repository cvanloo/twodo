package com.zetes.twodo

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zetes.twodo.controller.AlarmScheduler

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TwodoInstrumentedTest {

    private lateinit var alarmScheduler: AlarmScheduler
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        alarmScheduler = AlarmScheduler(context)
    }

    @Test
    fun useAppContext() {
        assertEquals("com.zetes.twodo", context.packageName)
    }

    @Test
    fun scheduleTest() {
        // assert that no exception is thrown
        try {
            alarmScheduler.schedule(0)
        } catch (ex: Exception) {
            assert(false)
        }
    }

    @Test
    fun deleteScheduleTest() {
        // assert that no exception is thrown
        try {
            alarmScheduler.delete(0)
        } catch (ex: Exception) {
            assert(false)
        }
    }

    @Test
    fun scheduleGeo() {
        // assert that no exception is thrown
        try {
            alarmScheduler.schedule(0f, 5f)
        } catch (ex: Exception) {
            assert(false)
        }
    }

    @Test
    fun deleteScheduleGeo() {
        // assert that no exception is thrown
        try {
            alarmScheduler.delete(0f, 5f)
        } catch (ex: Exception) {
            assert(false)
        }
    }
}