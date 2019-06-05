package com.amazon.ask.helloworld;

import org.apache.commons.codec.language.Metaphone;

import java.net.HttpURLConnection;
import java.net.URL;

public class testing{




    public static void main(String[] args) throws  Exception{
        //FeaturesIntentHandler featuresIntentHandler=new FeaturesIntentHandler();
        //Optional<Response> response=featuresIntentHandler.handle(null);
        //func();
        Metaphone m=new Metaphone();
        System.out.println(m.encode("Baatein"));
        System.out.println(m.encode("Nahin"));
    }



    public static void  func() throws Exception
    {
        try{
            /*
            StringBuilder url_str=new StringBuilder();
            url_str.append("https://fonoapi.freshpixl.com/v1/getdevice?");
            url_str.append("brand="+ brand_name.trim());
            url_str.append("&device="+ device_name.trim());
            //url_str.append("&position="+ "0");
            url_str.append("&token="+ TOKEN);*/
            URL url = new URL("https://fonoapi.freshpixl.com/v1/getdevice?brand=Samsung&token=8927bbb542e9ab900e49a0b748723fbb950e2a0a312f0419&position=0&device=galaxy%20m30");//url_str.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            /*
            Map<String, String> parameters = new HashMap<>();
            parameters.put("brand", brand_name);
            parameters.put("device", device_name);
            parameters.put("position", "0");
            parameters.put("token", TOKEN);
            con.setDoOutput(true);
            //DataOutputStream out = new DataOutputStream(con.getOutputStream());
            System.out.println("after defining dsoutputstream");

            //out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            System.out.println("after writing parameters to out ds stream");
            */
            //out.flush();
            //out.close();
            try {
                String cookie = con.getHeaderField( "Set-Cookie").split(";")[0];
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
                con.setRequestProperty("Cookie", cookie );
                //con.connect();
            }catch(Exception e){}

            System.out.println("before getting response code");

            int status = con.getResponseCode();
            System.out.println("status: "+ status);
            /*BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            con.disconnect();*/
        } catch(Exception e){
            //do nothing
            // content.append("exception");
            e.printStackTrace();
        }





    }


}