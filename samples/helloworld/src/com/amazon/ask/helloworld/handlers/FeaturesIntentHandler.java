package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.helloworld.URLConnect;
import com.amazon.ask.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class FeaturesIntentHandler implements RequestHandler {
    String TOKEN="8927bbb542e9ab900e49a0b748723fbb950e2a0a312f0419";
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("FeaturesIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get the color slot from user input.
        Slot device_solt = slots.get("device");
        String deviceSlotValue = device_solt.getValue();
        String[] arrOfStr = deviceSlotValue.split(" ", 2);
        String brand_name=arrOfStr[0];
        String device_name=arrOfStr[1];
        StringBuffer content_json=new StringBuffer();
        System.out.println("features intent debug");
        System.out.println("brand_name: "+ brand_name.trim());
        System.out.println("device_name: "+ device_name.trim());
        Map<String, String> parameters = new HashMap<>();
        parameters.put("brand", brand_name);
        parameters.put("device", device_name);
        parameters.put("position", "0");
        parameters.put("token", TOKEN);
        content_json=URLConnect.request_get(parameters);

        JSONArray jsonObjArray = new JSONArray(content_json.toString());
        JSONObject jsonObj = jsonObjArray.getJSONObject(0);
        String deviceName = (String) jsonObj.get("DeviceName");
        String primary_camera = " ";//(String) jsonObj.get("primary_");
        String sec_camera = " ";//(String) jsonObj.get("DeviceName");
        String chipset= (String) jsonObj.get("chipset");
        String cpu= (String) jsonObj.get("cpu");
        String os=(String) jsonObj.get("os");

        System.out.println("deviceName: "+ deviceName);
        System.out.println("primary_camera: "+ primary_camera);
        System.out.println("sec_camera: "+ sec_camera);
        System.out.println("chipset: "+ chipset);

        String content_str=deviceName+" has "+"chipset of "+chipset+", cpu of "+cpu.split(" ",2)[0]+", operating system "+os;

        speechText = content_str;

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("FeaturesIntent", speechText)
                .build();
    }

}