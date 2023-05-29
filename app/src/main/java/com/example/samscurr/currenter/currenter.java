package com.example.samscurr.currenter;
import android.os.StrictMode;
import android.widget.TextView;

import com.example.samscurr.apis.CurrencySearchService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class currenter
{
    final String strURL = "https://api.exchangerate.host/latest";

    public String getCurrencyInfo(String vallet) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        URL url = new URL(strURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        int responceCode = httpURLConnection.getResponseCode();
        if (responceCode == HttpURLConnection.HTTP_OK)
        {
            inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            Gson gson = new Gson();
            String allinfo = bufferedReader.readLine();
            bufferedReader.close();

            StringBuffer currinfo = new StringBuffer(allinfo);
            currinfo.delete(0, 265);
            String ic = currinfo.toString();
            ic = ic.substring(0, ic.length()-1);
            CurrencySearchService getdateinfo = gson.fromJson(allinfo, CurrencySearchService.class);
            CurrencySearchService getcurrinfo = gson.fromJson(ic, CurrencySearchService.class);
            String nums;
            if (vallet == "date")
                return (getdateinfo.getDate());
            if (vallet == "dollar")
            {
                nums = String.format("%.2f", getcurrinfo.getUSD()).replace(",", ".");
                return nums;
            }
            if (vallet == "rouble") {
                nums = String.format("%.2f", getcurrinfo.getRUB()).replace(",", ".");
                return nums;
            }
            if (vallet == "juan") {
                nums = String.format("%.2f", getcurrinfo.getCNY()).replace(",", ".");
                return nums;
            }
            if (vallet == "lira") {
                nums = String.format("%.2f", getcurrinfo.getTRY()).replace(",", ".");
                return nums;
            }
        }
        return "err";
    }
}
