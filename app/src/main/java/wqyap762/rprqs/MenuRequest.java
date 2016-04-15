package wqyap762.rprqs;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqyap762 on 15/04/16.
 */
public class MenuRequest extends StringRequest {
    private static final String MENU_REQUEST_URL = "http://rprqs.16mb.com/SetMenu.php";
    private Map<String, String> params;

    public MenuRequest(String menu_id, Response.Listener<String> listener) {
        super(Request.Method.POST, MENU_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("menu_id", menu_id);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
