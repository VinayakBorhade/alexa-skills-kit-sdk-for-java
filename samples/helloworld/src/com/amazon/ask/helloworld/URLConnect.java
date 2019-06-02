package com.amazon.ask.helloworld;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class URLConnect{
    public static StringBuffer request_get(Map parameters){
        StringBuffer content_json = new StringBuffer();
        try{
            URL url = new URL("https://fonoapi.freshpixl.com/v1/getdevice?");//brand=Samsung&token=8927bbb542e9ab900e49a0b748723fbb950e2a0a312f0419&position=0&device=galaxy");//url_str.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");

            con.setRequestMethod("GET");
            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            System.out.println("after defining dsoutputstream");

            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            System.out.println("after writing parameters to out ds stream");


            System.out.println("before getting response code");
            int status = con.getResponseCode();
            System.out.println("status code dekho: "+ status);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content_json.append(inputLine);
            }
            in.close();

            con.disconnect();
        } catch(Exception e){
            //do nothing
            content_json.append("exception");
            e.printStackTrace();
        }
        return content_json;
    }
}