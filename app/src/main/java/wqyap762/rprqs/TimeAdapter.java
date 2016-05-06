package wqyap762.rprqs;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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
        timeHolder.orderTimeText.setText(time.getWaiting_time());
        return row;
    }

    static class TimeHolder {
        TextView orderIdText, orderInfoText, orderTimeText;
    }
}
