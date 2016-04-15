package wqyap762.rprqs;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqyap762 on 14/04/16.
 */
public class OrderRequest extends StringRequest{
    private static final String ORDER_REQUEST_URL = "http://rprqs.16mb.com/OrderFood2.php";
    private Map<String, String> params;

    public OrderRequest(Double total_price, int quantity, String payment_status, String username, String menu_id, Response.Listener<String> listener) {
        super(Request.Method.POST, ORDER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("total_price", String.valueOf(total_price));
        params.put("quantity", String.valueOf(quantity));
        params.put("payment_status", payment_status);
        params.put("username", username);
        params.put("menu_id", menu_id + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
