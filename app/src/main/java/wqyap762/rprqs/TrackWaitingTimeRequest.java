package wqyap762.rprqs;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wqyap762 on 06/05/16.
 */
public class TrackWaitingTimeRequest extends StringRequest {
    private static final String TRACK_ORDER_REQUEST_URL = "http://188.166.178.66/track_request.php";
    private Map<String, String> params;

    public TrackWaitingTimeRequest(String hpno, Response.Listener<String> listener) {
        super(Request.Method.POST, TRACK_ORDER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("hpno", hpno);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
