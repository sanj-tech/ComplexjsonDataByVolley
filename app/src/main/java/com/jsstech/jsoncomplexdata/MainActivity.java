package com.jsstech.jsoncomplexdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String url = "https://jsonplaceholder.typicode.com/users";


    //json Data format
    //  [
    //   {
    //    "id": 1,
    //    "name": "Leanne Graham",
    //    "username": "Bret",
    //    "email": "Sincere@april.biz",
    //    "address": {
    //      "street": "Kulas Light",
    //      "suite": "Apt. 556",
    //      "city": "Gwenborough",
    //      "zipcode": "92998-3874",
    //      "geo": {
    //        "lat": "-37.3159",
    //        "lng": "81.1496"
    //      }
    //    },
    //    "phone": "1-770-736-8031 x56442",
    //    "website": "hildegard.org",
    //    "company": {
    //      "name": "Romaguera-Crona",
    //      "catchPhrase": "Multi-layered client-server neural-net",
    //      "bs": "harness real-time e-markets"
    //    }
    //  },

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d("TAG", response.toString());

                        try {
                            // Parsing json array response
                            // loop through each json object

                            for (int i = 0; i < response.length(); i++) {

                                JSONObject person = (JSONObject) response.get(i);
                                String name = person.getString("name");
                                String email = person.getString("email");


                                JSONObject addre = person.getJSONObject("address");
                                String Street = addre.getString("street");
                                String ZipCode = addre.getString("zipcode");
                                String City = addre.getString("city");

                                JSONObject Geo = person.getJSONObject("address").getJSONObject("geo");

                                String Lat = Geo.getString("lat");
                                String Lng = Geo.getString("lng");


                                String Phone = person.getString("phone");
                                String Website = person.getString("website");


                                JSONObject Company = person.getJSONObject("company");
                                String cname = Company.getString("name");
                                String CatchPhrase = Company.getString("catchPhrase");
                                String Bs = Company.getString("bs");
//display in console
                                Log.d("Name",name);
                                Log.d("Email",email);
                                Log.d("Street",Street);
                                Log.d("ZipCode",ZipCode);
                                Log.d("City",City);

                                Log.d("Lat",Lat);
                                Log.d("Lng",Lng);

                                Log.d("Phone",Phone);
                                Log.d("Website",Website);

                                Log.d("cname ",cname);
                                Log.d("CatchPhrase",CatchPhrase);
                                Log.d("Bs",Bs);


//                                jsonResponse += "Name: " + name + "\n\n";
//                                jsonResponse += "Email: " + email + "\n\n";
//                                jsonResponse += "Street: " + Street + "\n\n";
//                                jsonResponse += "Zipcode: " + Zipcode + "\n\n\n";

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }


                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });


//Add in queue

        queue.add(req);
    }

}