/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.amazon.ask.helloworld;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.helloworld.handlers.*;
import com.amazon.ask.helloworld.handlers.multiturnDialogHandlers.CompletedCompareIntentHandler;
import com.amazon.ask.helloworld.handlers.multiturnDialogHandlers.InProgressCompareIntentHandler;
import com.amazon.ask.helloworld.handlers.multiturnDialogHandlers.StartedCompareIntentHandler;

public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new FallbackIntentHandler(),
                        new FeaturesIntentHandler(),
                        //new CompareIntentHandler(),
                        new StartedCompareIntentHandler(),
                        new InProgressCompareIntentHandler(),
                        new CompletedCompareIntentHandler()
                )
                // Add your skill id below
                .withSkillId("amzn1.ask.skill.da050a22-9b88-4fd6-9012-8e042d85af93")
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}
