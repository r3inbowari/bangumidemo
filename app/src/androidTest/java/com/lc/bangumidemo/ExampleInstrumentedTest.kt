package com.lc.bangumidemo

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lc.bangumidemo.Sqlite.Bookinsert
import com.lc.bangumidemo.Sqlite.Bookselect
import com.lc.bangumidemo.Sqlite.MyDatabaseHelper
import com.lc.bangumidemo.Sqlite.Nvdetil

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.lc.bangumidemo", appContext.packageName)

    }
}
