package com.example.binh.myapplication;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        // Create and point a new firebase class to a particular firebase account
        Firebase ref = new Firebase("https://incandescent-heat-2821.firebaseio.com/");
        // Create a database object of the account and name it as current time
        Firebase tempMessageRef = ref.child("Temperature Messages");
    }

    final CountDownTimer timer = new CountDownTimer(600000, 30000) {
        @Override
        public void onTick(long millisUntilFinished) {
            Calendar calendar = Calendar.getInstance();
            String time = calendar.getTime().toString();
            // Create and point a new firebase class to a particular firebase account
            Firebase ref = new Firebase("https://incandescent-heat-2821.firebaseio.com/");
            // Create a database object of the account and name it as current time
            Firebase tempMessageRef = ref.child("Temperature Messages").child(time);
            // Create a message object
            Temperature message = new Temperature(time, 23);
            // Add a message object to the database
            tempMessageRef.setValue(message);
        }

        @Override
        public void onFinish() {
            timer.start(); // start it again after it finishes
        }
    }.start();

    public class Temperature {
        private String time;
        private int tempValue;
        public Temperature() {}
        public Temperature(String time, int tempValue) {
            this.time = time;
            this.tempValue = tempValue;
        }
        public int getTempValue() {
            return tempValue;
        }
        public String getTime() {
            return time;
        }
    }
}
