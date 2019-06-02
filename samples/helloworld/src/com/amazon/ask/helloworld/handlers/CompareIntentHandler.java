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

public class CompareIntentHandler implements RequestHandler {
    String TOKEN="8927bbb542e9ab900e49a0b748723fbb950e2a0a312f0419";
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CompareIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        System.out.println("compare intent handler called");
        String speechText;//="compare intent handler";
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get 3 slots from user input.
        Slot device_solt_a = slots.get("device_name_a");
        String deviceSlotValue_a = device_solt_a.getValue();
        System.out.println("deviceSlotValue_a: "+deviceSlotValue_a );
        String[] arrOfStr_a = deviceSlotValue_a.split(" ", 2);
        String brand_name_a=arrOfStr_a[0].trim();
        String device_name_a=arrOfStr_a[1].trim();

        Slot device_solt_b = slots.get("device_name_b");
        String deviceSlotValue_b = device_solt_b.getValue();
        System.out.println("deviceSlotValue_b: "+deviceSlotValue_b );
        String[] arrOfStr_b = deviceSlotValue_b.split(" ", 2);
        String brand_name_b=arrOfStr_b[0].trim();
        String device_name_b=arrOfStr_b[1].trim();

        Slot features_slot = slots.get("feature");
        System.out.println("features_slot: "+ features_slot);
        String features_slot_value = features_slot.getValue();
        System.out.println("feature slot value: "+ features_slot_value);

        Map<String, String> parameters_a = new HashMap<>();
        Map<String, String> parameters_b = new HashMap<>();

        parameters_a.put("brand", brand_name_a);
        parameters_a.put("device", device_name_a);
        parameters_a.put("position", "0");
        parameters_a.put("token", TOKEN);
        StringBuffer content_json_a=URLConnect.request_get(parameters_a);

        parameters_b.put("brand", brand_name_b);
        parameters_b.put("device", device_name_b);
        parameters_b.put("position", "0");
        parameters_b.put("token", TOKEN);
        StringBuffer content_json_b=URLConnect.request_get(parameters_b);

        JSONArray jsonObjArray_a = new JSONArray(content_json_a.toString());
        JSONObject jsonObj_a = jsonObjArray_a.getJSONObject(0);
        String feature_a=(String) jsonObj_a.get(features_slot_value.toLowerCase());

        JSONArray jsonObjArray_b = new JSONArray(content_json_b.toString());
        JSONObject jsonObj_b = jsonObjArray_b.getJSONObject(0);
        String feature_b=(String) jsonObj_b.get(features_slot_value.toLowerCase());

        StringBuffer result=new StringBuffer();
        result.append(brand_name_a+" "+device_name_a+" has, "+feature_a+" "+features_slot_value+", whereas "
                +brand_name_b+" "+device_name_b+" has, "+feature_b+" "+features_slot_value);

        System.out.println("compare intent debug");
        System.out.println("brand_name_a: "+ brand_name_a.trim());
        System.out.println("device_name_a: "+ device_name_a.trim());
        System.out.println("brand_name_b: "+ brand_name_b.trim());
        System.out.println("device_name_b: "+ device_name_b.trim());

        speechText=result.toString();
        //speechText=brand_name_a+", "+brand_name_b+", "+", "+features_slot_value;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CompareIntent", speechText)
                .build();
    }
}