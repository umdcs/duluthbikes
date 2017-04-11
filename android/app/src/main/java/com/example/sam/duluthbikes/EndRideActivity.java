package com.example.sam.duluthbikes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Sam on 3/29/2017.
 */

public class EndRideActivity extends AppCompatActivity{

    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_ride);

        TextView rideDate = (TextView)findViewById(R.id.dateLabel);
        TextView dist = (TextView)findViewById(R.id.distance);
        TextView timeLapsed = (TextView) findViewById(R.id.timeLapsed);
        TextView avSpeed = (TextView)findViewById(R.id.averageSpeed);
        TextView startTime = (TextView)findViewById(R.id.startTime);
        TextView endTime = (TextView)findViewById(R.id.endTime);

        data = getIntent().getExtras();

        //retrieve current time and format start and current time
        Date fDate = new Date();
        SimpleDateFormat timef = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd");

        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String formattedEnd = df.format(fDate.getTime());
        //String formattedStart = df.format(data.getString("startTime"));

        Long sTime =  data.getLong("startTime");
        Long fTime = fDate.getTime();
        Long timelapse = fTime - sTime;

        int sec = (int) (timelapse / 1000) % 60 ;
        int min = (int) ((timelapse / (1000*60)) % 60);
        int hours   = (int) ((timelapse / (1000*60*60)) % 24);

        String dateOfRide = datef.format(fDate);
        String timeFinish = timef.format(fDate);
        String timeStart = timef.format(sTime);

        rideDate.setText(dateOfRide);
        dist.setText(Double.toString(data.getDouble("dis")));
        timeLapsed.setText(Integer.toString(hours)+":"+Integer.toString(min)+":"+Integer.toString(sec));
        startTime.setText(timeStart);
        endTime.setText(timeFinish);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}

