package wqyap762.rprqs;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.NotificationCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by wqyap762 on 06/05/16.
 */
public class TimeAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public TimeAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Time time) {
        list.add(time);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        final TimeHolder timeHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.track_row_layout, parent, false);
            timeHolder = new TimeHolder();
            timeHolder.orderIdText = (TextView) row.findViewById(R.id.orderIdText);
            timeHolder.orderInfoText = (TextView) row.findViewById(R.id.orderInfoText);
            timeHolder.orderTimeText = (TextView) row.findViewById(R.id.orderTimeText);
            row.setTag(timeHolder);
        } else {
            timeHolder = (TimeHolder) row.getTag();
        }
        Time time = (Time) this.getItem(position);
        timeHolder.orderIdText.setText(time.getOrder_id());
        timeHolder.orderInfoText.setText("Order: " + time.getFood_name() + "\n" +
                "Payment Status: " + Html.fromHtml("<font color=red>" + time.getPayment_status() + "</font>") + "\n" +
                "Ordered on: " + time.getOrdered_on());

        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);

        Date current_time = parseDate(hour + ":" + minute + ":" + second);
        Date ready_time = parseDate(time.getReady_time());
        long waiting_time = ready_time.getTime() - current_time.getTime();

        new CountDownTimer(waiting_time, 1000) {

            public void onTick(long millisUntilFinished) {
                String ready = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                timeHolder.orderTimeText.setText(ready);
            }

            public void onFinish() {
                readyAlert();
                timeHolder.orderTimeText.setText("Ready");
            }
        }.start();
        return row;
    }

    static class TimeHolder {
        TextView orderIdText, orderInfoText, orderTimeText;
    }

    private Date parseDate(String date) {
        final String inputFormat = "HH:mm:ss";
        SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.getDefault());
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }

    public void readyAlert() {
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent = new Intent(getContext(), TrackWaitingTimeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivities(getContext(), 0, new Intent[]{intent}, 0);
        Notification notification = new NotificationCompat.Builder(getContext())
                .setTicker("TickerTitle")
                .setSound(sound)
                .setContentTitle("Restaurant Parit Raja")
                .setContentText("Your food is ready.")
                .setSmallIcon(R.drawable.logo2)
                .setContentIntent(pendingIntent).getNotification();

        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        notification.flags = Notification.FLAG_ONLY_ALERT_ONCE;

        NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(getContext().NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
