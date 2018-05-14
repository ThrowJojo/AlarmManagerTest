package testing.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class Receiver : BroadcastReceiver() {

    val LOG_TAG = Receiver::class.java.name

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(LOG_TAG, "HIT RECEIVER!")
        executeWork(context!!)
        //Helpers.setAlarm(context!!, Constants.INTERVAL_ALARM)
    }

    // Example of enqueuing JobIntentService
    private fun executeWork(context: Context) {
        val jobIntent = Intent(context, SurpriseJobIntentService::class.java)
        SurpriseJobIntentService.enqueueWork(context, jobIntent)
    }

}