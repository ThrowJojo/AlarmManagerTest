package testing.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

class Helpers {

    companion object {

        fun setAlarm(context: Context, futureMillis: Long) {
            val intent = Intent(Constants.INTENT_SURPRISE)
            intent.setClass(context, Receiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + futureMillis, pendingIntent)
        }

    }

}