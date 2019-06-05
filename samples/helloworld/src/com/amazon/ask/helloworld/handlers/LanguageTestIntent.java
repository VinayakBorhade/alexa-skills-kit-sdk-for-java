package com.amazon.ask.helloworld.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class LanguageTestIntent implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("LanguageTestIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText;
        Request request = handlerInput.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        // Get the color slot from user input.
        Slot device_solt = slots.get("sentence_query");
        String deviceSlotValue = device_solt.getValue();
        speechText="song is "+deviceSlotValue;
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("LanguageTestIntent", speechText)
                .build();
    }
}