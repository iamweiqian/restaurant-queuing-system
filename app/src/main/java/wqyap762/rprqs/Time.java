package wqyap762.rprqs;

/**
 * Created by wqyap762 on 06/05/16.
 */
public class Time {

    private String order_id, payment_status, food_name, ordered_on, waiting_time;

    public Time(String order_id, String food_name, String payment_status, String ordered_on, String waiting_time) {
        this.setOrder_id(order_id);
        this.setFood_name(food_name);
        this.setPayment_status(payment_status);
        this.setOrdered_on(ordered_on);
        this.setWaiting_time(waiting_time);
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getOrdered_on() {
        return ordered_on;
    }

    public void setOrdered_on(String ordered_on) {
        this.ordered_on = ordered_on;
    }

    public String getWaiting_time() {
        return waiting_time;
    }

    public void setWaiting_time(String waiting_time) {
        this.waiting_time = waiting_time;
    }
}
