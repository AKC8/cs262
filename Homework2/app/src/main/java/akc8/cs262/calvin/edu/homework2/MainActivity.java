package akc8.cs262.calvin.edu.homework2;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private GameAdapter listAdapter;
    private ArrayList<GameItem> gameItems;

    /**
     * Makes HTTP requests to the DB for players. The data is in the Monopoly API in JSON
     * and the app displays the data.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.edit_text);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = findViewById(R.id.list);
        gameItems = new ArrayList<>();
        listAdapter = new GameAdapter(this, gameItems);
        listView.setAdapter(listAdapter);
    }

    /**
     * Method to make toast messages
     * @param = ()
     * I hard coded the toast and never changed it.
     */
    private void displayToast() {
        Toast.makeText(this, "Couldn't find player", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method is called to allow the data to be retrieved from the DB. This part of the app should
     * be correct. The issue for the homework was somewhere in the network activity.
     * @param view
     */
    public void fetch(View view) {
        String url = "https://calvincs262-monopoly.appspot.com/monopoly/v1";
        if (mEditText.getText().toString().equals("")) {
            url += "/players";
        } else {
            try {
                url += "/player/" + Integer.parseInt(mEditText.getText().toString());
            } catch(NumberFormatException nfe) {
                return;
            }
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("RESPONSE", response.toString());
                        parseJsonFeed(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                displayToast();
            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
