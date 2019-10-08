package com.juandiegovqdev.recipesandroidapp.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public final class JSONUtilsJava extends JSONObject {
    public JSONUtilsJava(URL url) throws IOException, JSONException {
        super(getServerData(url));
    }

    public static String getServerData(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader ir = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String text = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            text = ir.lines().collect(Collectors.joining("\n"));
        }
        return (text);
    }

    public static void getJsonObject(Context ctx) throws IOException {
        String sURL = "https://api.citybik.es/v2/networks/sevici"; //just a string

        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        URLConnection request = url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
        String zipcode = rootobj.get("href").getAsString(); //just grab the zipcode
        Toast.makeText(ctx, zipcode, Toast.LENGTH_SHORT).show();
    }
}
