/*
 *    Copyright 2022 ***REMOVED*** ***REMOVED***
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

import kopfkino.BoundingBox;
import kopfkino.Entity;
import kopfkino.Game;
import kopfkino.Vector2f;
import kopfkino.animation.KeyframeAnimation;
import kopfkino.animation.TransitionFunction;
import kopfkino.KopfkinoGraphics;
import kopfkino.graphics.OvalEntityRenderer;
import kopfkino.graphics.RenderConfig;
import kopfkino.Colors;
import kopfkino.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class KeyframeAnimationShowcase extends Entity {
    private List<TransitionFunction> transitions = new ArrayList<>();
    private List<String> transitionNames = new ArrayList<>();
    private String transitionName = "";
    private KeyframeAnimation animation;
    public KeyframeAnimationShowcase() {
        super(new BoundingBox(0, 475, 50, 50), new OvalEntityRenderer(RenderConfig.forColor(Colors.CORAL_RED)));
        transitions.add(TransitionFunction.easeInSine());
        transitionNames.add("ease in sine");
        transitions.add(TransitionFunction.easeInSine().inverse());
        transitionNames.add("ease out sine");
        transitions.add(TransitionFunction.easeInOutSine());
        transitionNames.add("ease in/out sine");
        transitions.add(TransitionFunction.easeOutBounce());
        transitionNames.add("ease out bounce");
        transitions.add(TransitionFunction.easeOutBounce().inverse());
        transitionNames.add("ease in bounce");
        transitions.add(TransitionFunction.easeInOutBounce());
        transitionNames.add("ease in/out bounce");
        transitions.add(TransitionFunction.easeInElastic());
        transitionNames.add("ease in elastic");
        transitions.add(TransitionFunction.easeInElastic().inverse());
        transitionNames.add("ease out elastic");
        transitions.add(TransitionFunction.easeInOutElastic());
        transitionNames.add("ease in/out elastic");
        transitions.add(TransitionFunction.easeInExpo());
        transitionNames.add("ease in expo");
        transitions.add(TransitionFunction.easeInExpo().inverse());
        transitionNames.add("ease out expo");
        transitions.add(TransitionFunction.easeInOutExpo());
        transitionNames.add("ease in/out expo");
        animation = new KeyframeAnimation(null);
        animation.appendFrame(0, 250);
        animation.appendFrame(1000, Game.getInstance().getResolutionW() - 250);
        animation.appendFrame(1500, 250);
        animation.setLoop(true);
        nextTransition();
    }

    private void nextTransition() {
        final int n = MathUtils.randomInt(0, transitions.size() - 1);
        animation.setTransition(transitions.get(n));
        transitionName = transitionNames.get(n);
    }

    @Override
    public void fixedUpdate() {
        setX(animation.nextValue());
        if (animation.ended()) nextTransition();
    }

    @Override
    public void renderAfter(final KopfkinoGraphics graphics) {
        graphics.drawString(transitionName, new Vector2f(Game.getInstance().getResolutionW() / 2f, 450), KopfkinoGraphics.TextAnchor.CENTRE);
        graphics.setColor(Colors.SAD_GRAY);
    }
}
