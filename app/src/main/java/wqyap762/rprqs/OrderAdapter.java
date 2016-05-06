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
 * Created by wqyap762 on 19/04/16.
 */
public class OrderAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public OrderAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Order order) {
        list.add(order);
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
        OrderHolder orderHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.view_row_layout, parent, false);
            orderHolder = new OrderHolder();
            orderHolder.orderIdText = (TextView) row.findViewById(R.id.orderIdText);
            orderHolder.orderInfoText = (TextView) row.findViewById(R.id.orderInfoText);
            row.setTag(orderHolder);
        } else {
            orderHolder = (OrderHolder) row.getTag();
        }
        Order order = (Order) this.getItem(position);
        orderHolder.orderIdText.setText(order.getOrder_id());
        orderHolder.orderInfoText.setText("Order: " + order.getFood_name() + "\n" +
                "Payment Status: " + Html.fromHtml("<font color=red>" + order.getPayment_status() + "</font>") + "\n" +
                "Ordered on: " + order.getOrdered_on());
        return row;
    }

    static class OrderHolder {
        TextView orderIdText, orderInfoText;
    }
}
