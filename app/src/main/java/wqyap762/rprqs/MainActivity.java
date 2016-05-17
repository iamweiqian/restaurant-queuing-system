package wqyap762.rprqs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()) {
            operatingHours();
        } else {
            AlertDialog.Builder networkNotFound = new AlertDialog.Builder(MainActivity.this);
            networkNotFound.setTitle("Network Error");
            networkNotFound.setMessage("No Internet Connection.");
            networkNotFound.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    moveTaskToBack(true);
                }
            });
            networkNotFound.show();
        }
    }

    private void operatingHours() {
        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        final Date time = parseDate(hour + ":" + minute);
        String compareStartTime = "08:00";
        final Date startTime = parseDate(compareStartTime);
        String compareEndTime = "22:00";
        final Date endTime = parseDate(compareEndTime);

        new  Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (startTime.before(time) && endTime.after(time)) {
                    if (SaveSharedPreferences.getPrefHpno(MainActivity.this).length() == 0) {
                        goToLoginActivity();
                    } else {
                        goToCustomerMainActivity();
                    }
                } else {
                    showNotice();
                }
            }
        }, 2500);
    }

    private Date parseDate(String date) {
        final String inputFormat = "HH:mm";
        SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.getDefault());
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }

    public void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    public void goToCustomerMainActivity() {
        Intent intent = new Intent(this, CustomerMainActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    public void showNotice() {
        Intent intent = new Intent(this, NoticeActivity.class);
        startActivity(intent);
    }
}
