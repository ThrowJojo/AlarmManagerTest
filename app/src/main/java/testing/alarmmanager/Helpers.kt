package testing.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import io.paperdb.Paper

class Helpers {

    companion object {

        // Sets the next alarm from now
        fun setAlarm(context: Context, futureMillis: Long) {
            val intent = Intent(Constants.INTENT_SURPRISE)
            // setClass is important, won't run on Oreo+ otherwise
            intent.setClass(context, Receiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + futureMillis, pendingIntent)
        }

        // Adds new record for the passed timestamp(milliseconds) value
        fun addNewRecord(timestamp: Long) {
            val record = Record(timestamp)
            var savedRecords = mutableListOf<Record>()
            if (Paper.book().contains(Constants.KEY_RECORDS)) {
                savedRecords = Paper.book().read<MutableList<Record>>(Constants.KEY_RECORDS)
            }
            savedRecords.add(record)
            Paper.book().write(Constants.KEY_RECORDS, savedRecords)
        }

    }

}