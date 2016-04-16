package wqyap762.rprqs;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqyap762 on 13/04/16.
 */
public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://rprqs.16mb.com/LoginRequest.php";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
