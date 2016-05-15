package wqyap762.rprqs;

import android.content.Context;
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
        TimeHolder timeHolder;
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

        if (ready_time.equals(current_time) || ready_time.before(current_time)) {
            timeHolder.orderTimeText.setText("Ready");
        } else if (ready_time.after(current_time)) {
            timeHolder.orderTimeText.setText(time.getReady_time());
        } else {
            timeHolder.orderTimeText.setText("N/A");
        }
        return row;
    }

    static class TimeHolder {
        TextView orderIdText, orderInfoText, orderTimeText;
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
}
