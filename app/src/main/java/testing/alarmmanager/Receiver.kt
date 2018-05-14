package testing.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class Receiver : BroadcastReceiver() {

    val LOG_TAG = Receiver::class.java.name

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.e(LOG_TAG, "HIT RECEIVER!")
        //val jobIntent = Intent(context!!, SurpriseJobIntentService::class.java)
        //SurpriseJobIntentService.enqueueWork(context, jobIntent)
        Helpers.setAlarm(context!!, Constants.INTERVAL_ALARM)
    }

}