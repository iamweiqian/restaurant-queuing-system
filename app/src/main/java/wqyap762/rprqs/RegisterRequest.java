package wqyap762.rprqs;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqyap762 on 13/04/16.
 */
public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://188.166.178.66/register_request.php";
    private Map<String, String> params;

    public RegisterRequest(String hpno, String password, String name, int user_state, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("hpno", hpno);
        params.put("password", password);
        params.put("name", name);
        params.put("user_state", user_state + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
