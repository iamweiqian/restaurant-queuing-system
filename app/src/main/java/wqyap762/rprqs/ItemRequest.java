package wqyap762.rprqs;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqyap762 on 15/04/16.
 */
public class ItemRequest extends StringRequest {
    private static final String ITEM_REQUEST_URL = "http://188.166.178.66/item_request.php";
    private Map<String, String> params;

    public ItemRequest(String menu_id, Response.Listener<String> listener) {
        super(Request.Method.POST, ITEM_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("menu_id", menu_id + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
