package com.amazon.ask.helloworld.handlers.multiturnDialogHandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class StartedCompareIntentHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return (handlerInput.matches(intentName("CompareIntent"))
            && intentRequest.getDialogState()== DialogState.STARTED);
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        System.out.println("Inside StartedCompareIntentHandler");
        Intent intent = intentRequest.getIntent();
        /*
        code for giving the default values to the empty slots
        * */
        /*
        Slot fromCitySlot = intent.getSlots().get(FROM_CITY_SLOT_NAME);
        if (fromCitySlot.getValue() == null){
            // getValue returns null if the user has not filled the
            // slot. In this example, getUserDefaultCity() retrieves
            // the user's default from persistent storage.
            Slot updateSlot = Slot.builder()
                    .withName(FROM_CITY_SLOT_NAME)
                    .withValue(getUserDefaultCity())
                    .build();

            // Push the updated slot into the intent object
            intent.getSlots().put(FROM_CITY_SLOT_NAME, updateSlot);
        }
        */
        // Return the Dialog.Delegate directive
        return handlerInput.getResponseBuilder()
                .addDelegateDirective(intent)
                .build();
    }
}