package testing.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kotlin.concurrent.thread

class Receiver : BroadcastReceiver() {

    val LOG_TAG = Receiver::class.java.name

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(LOG_TAG, "HIT RECEIVER!")
        executeWork(context!!)
        //executeAsyncWork(context!!)
    }

    // Example of enqueuing JobIntentService
    private fun executeWork(context: Context) {

        if (!SurpriseJobIntentService.isScheduled(context)) {
            Log.d(LOG_TAG, "SCHEDULE NEW JOB")
            val jobIntent = Intent(context, SurpriseJobIntentService::class.java)
            SurpriseJobIntentService.enqueueWork(context, jobIntent)
        } else {
            Log.d(LOG_TAG, "JOB ALREADY SCHEDULED, SKIP")
        }

        Helpers.setAlarm(context, Constants.INTERVAL_ALARM)
    }

    // Executes the work inside BroadcastReceiver instead
    // Must return/finish within 10 seconds
    private fun executeAsyncWork(context: Context) {
        val result = goAsync()
        thread(start = true) {
            val current = System.currentTimeMillis()
            Log.d(LOG_TAG, "SURPRISE EXECUTED AT: $current, SETTING ANOTHER ALARM!")
            Helpers.addNewRecord(current)
            Helpers.setAlarm(context, Constants.INTERVAL_ALARM)
            result.finish()
        }
    }

}