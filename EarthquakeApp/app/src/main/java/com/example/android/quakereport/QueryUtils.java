package com.example.android.quakereport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.HandlerThread;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;


/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private static final int HTTP_STATUS_OK = 200;

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<Earthquake> extractEarthquakes(String url) throws IOException {

        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        String jsonResponse = "";

        jsonResponse  = makeHTTPRequest(createURL(url));

        try {
            earthquakes = getEarthquakes(jsonResponse);
        } catch (JSONException e){
            Log.e(LOG_TAG, "JSONException", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    private static String makeHTTPRequest(URL queryURL) throws IOException {
        // Create an empty ArrayList that we can start adding earthquakes to

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String response = "";

        try {
            urlConnection = (HttpURLConnection) queryURL.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            switch (urlConnection.getResponseCode()){
                case HTTP_STATUS_OK:
                    inputStream = urlConnection.getInputStream();
                    response = readFromStream(inputStream);
                    break;

            }

        } catch (IOException e ) {
                Log.e("QueryUtils", "Problem connecting to URL", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return response;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<Earthquake> getEarthquakes(String jsonResponse) throws JSONException {
        JSONObject earthquakeList = new JSONObject(jsonResponse);
        JSONArray feature = earthquakeList.getJSONArray("features") ;
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        for(int i = 0; i < feature.length(); i++){
            JSONObject holderObject = feature.getJSONObject(i).getJSONObject("properties");
            String location = holderObject.getString("place");
            long date = holderObject.getLong("time");
            double magnitude = holderObject.getDouble("mag");
            String url = holderObject.getString("url");
            earthquakes.add(new Earthquake(location, date, magnitude, url));
        }
        return earthquakes;
    }

    private static URL createURL(String queryURL) throws MalformedURLException {
        return new URL(queryURL);
    }

}
