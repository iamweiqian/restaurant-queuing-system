package wqyap762.rprqs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class BeveragesOrderActivity extends AppCompatActivity {

    String menu_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Ice Coffee
        Button iceCoffeeButton = (Button) findViewById(R.id.iceCoffeeButton);
        assert iceCoffeeButton != null;
        iceCoffeeButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B001";
                        goToItemActivity();
                    }
                }
        );

        // Ice Lemon Tea
        Button iceLemonTeaButton = (Button) findViewById(R.id.iceLemonTeaButton);
        assert iceLemonTeaButton != null;
        iceLemonTeaButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B002";
                        goToItemActivity();
                    }
                }
        );

        // Ice Milo
        Button iceMiloButton = (Button) findViewById(R.id.iceMiloButton);
        assert iceMiloButton != null;
        iceMiloButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B003";
                        goToItemActivity();
                    }
                }
        );

        // Mango Jelly
        Button mangoJellyButton = (Button) findViewById(R.id.mangoJellyButton);
        assert mangoJellyButton != null;
        mangoJellyButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        menu_id = "B004";
                        goToItemActivity();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beverages_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToItemActivity() {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("menu_id", menu_id);
        startActivity(intent);
    }
}
