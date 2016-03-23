package wqyap762.rprqs;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wqyap762 on 24/03/16.
 */
public class OrderTask extends AsyncTask<String, Void, String> {
    Context context;
    OrderTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String order_url = "http://rprqs.16mb.com/OrderFood.php";
        String method = params[0];
        if (method.equals("order")) {
            String total_price = params[1];
            String quantity = params[2];
            String payment_status = params[3];
            String username = params[4];
            String menu_id = params[5];
            try {
                URL url = new URL(order_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("total_price", "UTF-8") + "=" + URLEncoder.encode(total_price, "UTF-8") + "&" +
                        URLEncoder.encode("quantity", "UTF-8") + "=" + URLEncoder.encode(quantity, "UTF-8") + "&" +
                        URLEncoder.encode("payment_status", "UTF-8") + "=" + URLEncoder.encode(payment_status, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("menu_id", "UTF-8") + "=" + URLEncoder.encode(menu_id, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                return "Registration Success";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
