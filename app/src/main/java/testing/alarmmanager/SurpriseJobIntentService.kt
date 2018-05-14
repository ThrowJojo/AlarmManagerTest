package testing.alarmmanager

import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import android.util.Log

class SurpriseJobIntentService : JobIntentService() {

    val LOG_TAG = SurpriseJobIntentService::class.java.name

    // Work is handled here, similar to to onHandleIntent from IntentService
    // Work is done on new thread
    override fun onHandleWork(intent: Intent) {
        val current = System.currentTimeMillis()
        Log.e(LOG_TAG, "SURPRISE EXECUTED AT: $current, SETTING ANOTHER ALARM!")
        Helpers.addNewRecord(current)
        Helpers.setAlarm(this, Constants.INTERVAL_ALARM)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(LOG_TAG, "WORK FINISHED")
    }

    companion object {

        private const val JOB_ID = 1000

        // Enqueues new work
        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, SurpriseJobIntentService::class.java, JOB_ID, intent)
        }

    }

}