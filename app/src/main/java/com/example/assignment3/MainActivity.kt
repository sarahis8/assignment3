package com.example.assignment3

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()
        var flag:String="Mascara"
        val spinnerval: Spinner = findViewById(R.id.spinner)
        var options= arrayOf("Mascara","Foundation","Brushes",
            "Blusher","Fixing Powder","LipStick")
        spinnerval.adapter= ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)

        spinnerval.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                flag = options.get(p2) //p2 is the index of selected item
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val confirm: Button =findViewById(R.id.button)
        confirm.setOnClickListener(){
            sendNotification()
            intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("key", flag)
            intent.putExtra("key2",text)
            startActivity(intent)
        }



    }

    private val channelId = "channel id example"
    private val notifiId = 101

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "test_notification"
            val descriptionText = "description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    val text:String="Your Order Has Been Confirmed"
    private fun sendNotification() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Order")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        with(NotificationManagerCompat.from(this)) {
            notify(notifiId, builder.build())

        }
}}
