package com.example.sam.duluthbikes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DecimalFormat;
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

        //data format definitions
        //SimpleDateFormat timef = new SimpleDateFormat("HH:mm:ss"); //military time
        SimpleDateFormat timef = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat datef = new SimpleDateFormat("MM-dd-yyyy");
        DecimalFormat df = new DecimalFormat("#.###");

        Long sTime =  data.getLong("startTime");
        Long fTime = fDate.getTime();
        Long timelapse = fTime - sTime;

        int sec = (int) (timelapse / 1000) % 60 ;
        int min = (int) ((timelapse / (1000*60)) % 60);
        int hours = (int) ((timelapse / (1000*60*60)) % 24);

        Double distance = data.getDouble("dis");
        distance = distance/1000;
        Double average = (distance/(timelapse/1000))*3600;

        //format data entries
        Double distKM = Double.valueOf(df.format(distance));
        Double averKmH = Double.valueOf(df.format(average));
        String dateOfRide = datef.format(fDate);
        String timeFinish = timef.format(fDate);
        String timeStart = timef.format(sTime);

        rideDate.setText(dateOfRide);
        dist.setText(Double.toString(distKM));
        timeLapsed.setText(Integer.toString(hours)+"h "+Integer.toString(min)+"min "+Integer.toString(sec)+ "sec ");
        avSpeed.setText(Double.toString(averKmH));
        startTime.setText(timeStart);
        endTime.setText(timeFinish);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}

