package com.amazon.ask.helloworld.handlers.multiturnDialogHandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class InProgressCompareIntentHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return (handlerInput.matches(intentName("CompareIntent"))
                && intentRequest.getDialogState() == DialogState.IN_PROGRESS);
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        System.out.println("Inside the InProgressCompareIntentHandler");
        //Just return Dialog.Delegate for all requests.
        return handlerInput.getResponseBuilder()
                .addDelegateDirective(intentRequest.getIntent())
                .build();
    }
}