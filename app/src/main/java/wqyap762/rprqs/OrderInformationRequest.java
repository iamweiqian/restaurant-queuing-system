package wqyap762.rprqs;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqyap762 on 16/04/16.
 */
public class OrderInformationRequest extends StringRequest {
    private static final String ORDER_INFORMATION_REQUEST_URL = "http://188.166.178.66/information_request.php";
    private Map<String, String> params;

    public OrderInformationRequest(String order_id, String hpno, Response.Listener<String> listener) {
        super(Request.Method.POST, ORDER_INFORMATION_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("order_id", order_id);
        params.put("hpno", hpno + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
