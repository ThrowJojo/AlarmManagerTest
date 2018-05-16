package testing.alarmmanager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.paperdb.Paper

class MainActivity : AppCompatActivity() {

    val LOG_TAG = MainActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Helpers.setAlarm(this, Constants.INTERVAL_ALARM)
        val records = Paper.book().read<List<Record>>(Constants.KEY_RECORDS, mutableListOf())

        // Print out some record information
        Log.d(LOG_TAG, "NUMBER OF RECORDS: ${records.size}")
        if (!records.isEmpty()) {
            Log.d(LOG_TAG, "*** LAST EXECUTIONS ***")
            val tailRecords = records.takeLast(10)
            tailRecords.forEach {it ->
                Log.d(LOG_TAG, "${records.indexOf(it)}: ${it.timestamp}")
            }
        }
    }

}
