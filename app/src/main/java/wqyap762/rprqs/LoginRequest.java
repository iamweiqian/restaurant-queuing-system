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
    private static final String LOGIN_REQUEST_URL = "http://188.166.178.66/login_request.php";
    private Map<String, String> params;

    public LoginRequest(String hpno, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("hpno", hpno);
        params.put("password", password + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
