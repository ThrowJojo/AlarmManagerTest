package testing.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val jobIntent = Intent(context!!, SurpriseJobIntentService::class.java)
        SurpriseJobIntentService.enqueueWork(context, jobIntent)
    }

}