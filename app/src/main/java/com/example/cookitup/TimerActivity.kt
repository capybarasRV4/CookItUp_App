package com.example.cookitup

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cookitup.databinding.ActivityTimerBinding
import java.util.Locale
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.core.app.NotificationCompat

class TimerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimerBinding
    private lateinit var app: MyApplication

    private var mEditTextInput: EditText? = null
    private var mTextViewCountDown: TextView? = null
    private var mButtonSet: Button? = null
    private var mButtonStartPause: Button? = null
    private var mButtonReset: Button? = null

    private var mCountDownTimer: CountDownTimer? = null

    private var mTimerRunning = false

    private var mStartTimeInMillis: Long = 0
    private var mTimeLeftInMillis: Long = 0
    private var mEndTime: Long = 0

    companion object {
        private const val CHANNEL_ID = "timer_channel"
        private const val CHANNEL_NAME = "Timer Channel"
        private const val NOTIFICATION_ID = 1
        const val ACTION_TIMER_FINISHED = "com.example.cookitup.ACTION_TIMER_FINISHED"
    }

    private val timerReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ACTION_TIMER_FINISHED) {
                // Handle the timer finished event here
                showNotification()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = application as MyApplication
        binding = ActivityTimerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mEditTextInput = findViewById(R.id.edit_text_input)
        mTextViewCountDown = findViewById(R.id.text_view_countdown)

        mButtonSet = findViewById(R.id.button_set)
        mButtonStartPause = findViewById(R.id.button_start_pause)
        mButtonReset = findViewById(R.id.button_reset)

        mButtonSet?.setOnClickListener(View.OnClickListener {
            val input = mEditTextInput?.text.toString()
            if (input.isEmpty()) {
                Toast.makeText(this@TimerActivity, "Field can't be empty", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            val millisInput = input.toLong() * 60000
            if (millisInput == 0L) {
                Toast.makeText(
                    this@TimerActivity,
                    "Please enter a positive number",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            setTime(millisInput)
            mEditTextInput?.setText("")
        })

        mButtonStartPause?.setOnClickListener {
            if (mTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        mButtonReset?.setOnClickListener { resetTimer() }

        binding.imageButton5.setOnClickListener {
            finish()
        }
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(ACTION_TIMER_FINISHED)
        registerReceiver(timerReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(timerReceiver)
    }

    private fun setTime(milliseconds: Long) {
        mStartTimeInMillis = milliseconds
        resetTimer()
        closeKeyboard()
    }

    private fun startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                mTimerRunning = false
                updateWatchInterface()
                showNotification()
            }
        }.start()
        mTimerRunning = true
        updateWatchInterface()
    }

    private fun showNotification() {
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.sand_clock)
            .setContentTitle("Timer Expired")
            .setContentText("Your timer has finished.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun pauseTimer() {
        mCountDownTimer!!.cancel()
        mTimerRunning = false
        updateWatchInterface()
    }

    private fun resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis
        updateCountDownText()
        updateWatchInterface()
    }

    private fun updateCountDownText() {
        val hours = (mTimeLeftInMillis / 1000).toInt() / 3600
        val minutes = (mTimeLeftInMillis / 1000 % 3600).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted: String = if (hours > 0) {
            String.format(
                Locale.getDefault(),
                "%d:%02d:%02d", hours, minutes, seconds
            )
        } else {
            String.format(
                Locale.getDefault(),
                "%02d:%02d", minutes, seconds
            )
        }
        mTextViewCountDown!!.text = timeLeftFormatted
    }

    private fun updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput!!.visibility = View.INVISIBLE
            mButtonSet!!.visibility = View.INVISIBLE
            mButtonReset!!.visibility = View.INVISIBLE
            mButtonStartPause!!.text = "Pause"
        } else {
            mEditTextInput!!.visibility = View.VISIBLE
            mButtonSet!!.visibility = View.VISIBLE
            mButtonStartPause!!.text = "Start"
            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause!!.visibility = View.INVISIBLE
            } else {
                mButtonStartPause!!.visibility = View.VISIBLE
            }
            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset!!.visibility = View.VISIBLE
            } else {
                mButtonReset!!.visibility = View.INVISIBLE
            }
        }
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
    override fun onStop() {
        super.onStop()
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putLong("startTimeInMillis", mStartTimeInMillis)
        editor.putLong("millisLeft", mTimeLeftInMillis)
        editor.putBoolean("timerRunning", mTimerRunning)
        editor.putLong("endTime", mEndTime)
        editor.apply()
    }

    override fun onStart() {
        super.onStart()
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000)
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis)
        mTimerRunning = prefs.getBoolean("timerRunning", false)
        updateCountDownText()
        updateWatchInterface()
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0)
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis()
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0
                mTimerRunning = false
            }
            updateCountDownText()
            updateWatchInterface()
            if (mTimeLeftInMillis > 0) {
                startTimer()
            }
        }
    }

}