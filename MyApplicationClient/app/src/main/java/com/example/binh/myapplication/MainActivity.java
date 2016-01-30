package com.example.binh.myapplication;
import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Person> data;
    private ListView lv;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<Person>();
        adapter = new MyAdapter(this, R.layout.row, data);
        lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);
        Firebase.setAndroidContext(this);
        // Create and point a new firebase class to a particular firebase account
        Firebase ref = new Firebase("https://incandescent-heat-2821.firebaseio.com/");
        // Create a database object of the account and name it as current time
        Firebase tempMessageRef = ref.child("Temperature Messages");
        tempMessageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String newTime = (String)dataSnapshot.child("time").getValue();
                Person message = new Person("23", newTime);
                Context context = getApplicationContext();
                CharSequence text = newTime;
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                data.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

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
