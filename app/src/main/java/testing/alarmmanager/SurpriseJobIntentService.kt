package testing.alarmmanager

import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import android.util.Log
import io.paperdb.Paper

class SurpriseJobIntentService : JobIntentService() {

    val LOG_TAG = SurpriseJobIntentService::class.java.name

    override fun onHandleWork(intent: Intent) {
        val current = System.currentTimeMillis()
        Log.e(LOG_TAG, "SURPRISE EXECUTED AT: $current, SETTING ANOTHER ALARM!")

        val record = Record(current)
        var savedRecords = mutableListOf<Record>()
        if (Paper.book().contains(Constants.KEY_RECORDS)) {
            savedRecords = Paper.book().read<MutableList<Record>>(Constants.KEY_RECORDS)
        }
        savedRecords.add(record)
        Paper.book().write(Constants.KEY_RECORDS, savedRecords)
        Helpers.setAlarm(this, Constants.INTERVAL_ALARM)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(LOG_TAG, "WORK FINISHED")
    }

    companion object {

        private const val JOB_ID = 1000

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, SurpriseJobIntentService::class.java, JOB_ID, intent)
        }

    }

}