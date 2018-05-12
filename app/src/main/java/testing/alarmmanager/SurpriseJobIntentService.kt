package testing.alarmmanager

import android.content.Context
import android.content.Intent
import android.support.v4.app.JobIntentService
import android.util.Log

class SurpriseJobIntentService : JobIntentService() {

    val LOG_TAG = SurpriseJobIntentService::class.java.name

    override fun onHandleWork(intent: Intent) {
        Log.e(LOG_TAG, "SURPRISE EXECUTED AT: ${System.currentTimeMillis()}, SETTING ANOTHER ALARM!")
        Helpers.setAlarm(this, 5000)
    }

    companion object {

        private const val JOB_ID = 1000

        fun enqueueWork(context: Context, intent: Intent) {
            enqueueWork(context, SurpriseJobIntentService::class.java, JOB_ID, intent)
        }

    }

}