/*
 *    Copyright 2021 ***REMOVED*** ***REMOVED***
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

import kopfkino.*;
import kopfkino.collision.CircleHitbox;
import kopfkino.collision.Collision;
import kopfkino.graphics.OvalEntityRenderer;
import kopfkino.gui.*;
import kopfkino.gui.Button;
import kopfkino.gui.Label;
import kopfkino.gui.ScrollPane;
import kopfkino.gui.TextArea;
import static kopfkino.Prelude.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TestScene extends Scene {
    public TestScene() {
        setGui(new Gui());
        Button quitButton = new Button(
            dimensions(300, 50),
            "Quit.",
            (cursor) -> Game.exit()
        );
        CheckBox checkBox = new CheckBox(
            dimensions(25, 25),
            false,
            getGui()::setDrawBounds
        );
        Label debugDraw = new Label(
            25,
            "Debug draw component boounds"
        );
        Label yourName = new Label(
            25,
            "Your name:"
        );
        TextArea nameInput = new TextArea(
            dimensions(300, 50),
            ""
        );
        Slider slider = new Slider(
            dimensions(300, 50),
            0.5f
        );
        Label sliderLabel = new Label(
            50,
            "0.5",
            Align.AlignX.LEFT
        );
        sliderLabel.setMinWidth(300);
        ProgressBar sliderBar = new ProgressBar(dimensions(200, 35), 0.5f);
        sliderLabel.setForegroundColor(Colors.CORN_FLOWER_BLUE);
        slider.setOnAction(s -> {
            Slider _slider = (Slider) s;
            sliderLabel.setText(String.valueOf(_slider.getValue()));
            sliderBar.setValue(1 - _slider.getValue());
        });

        RadioButton radioA;
        RadioButton radioB;
        List<RadioButton> radioGroup = new ArrayList<>();
        radioA = new RadioButton(
            dimensions(25, 25),
            false,
            a -> sliderLabel.setForegroundColor(Colors.DARK_GREEN_COLOR),
            radioGroup
        );
        radioB = new RadioButton(
            dimensions(25, 25),
            true,
            a -> sliderLabel.setForegroundColor(Colors.CORN_FLOWER_BLUE),
            radioGroup
        );
        radioGroup.add(radioA);
        radioGroup.add(radioB);
        Label green = new Label(
            25,
            "Green"
        );
        Label blue = new Label(
            25,
            "Blue"
        );
        Label right = new Label(
            25,
            "Right"
        );
        Label left = new Label(
            25,
            "Left"
        );
        WaitingIndicator waitingIndicator = new WaitingIndicator(dimensions(50, 50));
        BarsWaitingIndicator barsWaitingIndicator = new BarsWaitingIndicator(dimensions(100, 50));

        float gap = 50.0f;
        float vskip = 100.0f;
        new LineLayoutGenerator(Game.getDisplayBounds(), Align.AlignX.CENTRE)
            .addGroup(gap / 2f, checkBox, debugDraw)
            .newLine(vskip, Align.AlignX.CENTRE)
            .add(yourName)
            .newLine(vskip / 2f, Align.AlignX.CENTRE)
            .add(nameInput)
            .newLine(vskip, Align.AlignX.CENTRE)
            .add(slider)
            .alignX(Align.AlignX.LEFT)
            .gap(gap)
            .add(sliderLabel)
            .newLine(vskip, Align.AlignX.CENTRE)
            .addGroup(List.of(gap / 2, gap, gap / 2), radioA, green, radioB, blue)
            .newLine(vskip, Align.AlignX.CENTRE)
            .add(sliderBar)
            .newLine(vskip * 2f, Align.AlignX.CENTRE)
            .add(quitButton)
            .newLine(vskip, Align.AlignX.CENTRE)
            .addGroup(gap, waitingIndicator, barsWaitingIndicator)
            .finalAlign(Align.AlignY.CENTRE, 0);

        new LineLayoutGenerator(Game.getDisplayBounds(), Align.AlignX.CENTRE)
            .newLine(25, Align.AlignX.RIGHT)
            .add(right)
            .newLine(0, Align.AlignX.LEFT)
            .add(left)
            .finalAlign(Align.AlignY.TOP, 10);

        ScrollPane scrollPane = new ScrollPane(
            BoundingBox.relativeTo(
                Game.getDisplayBounds(),
                new Align(Align.AlignX.LEFT, Align.AlignY.TOP),
                dimensions(200, 100),
                vec(100, 100)
            )
        );
        LineLayoutGenerator scrollPaneLayout = new LineLayoutGenerator(scrollPane.getBounds(), Align.AlignX.CENTRE);
        scrollPaneLayout.alignY(Align.AlignY.TOP);
        for (int i = 0; i < 100; i++) {
            Label l = new Label(String.valueOf(i));
            scrollPaneLayout.add(l);
            scrollPaneLayout.newLine(l.getBounds().getHeight(), Align.AlignX.CENTRE);
            Block sep = new Block(dimensions(scrollPane.getBounds().getWidth(), 3));
            scrollPaneLayout.add(sep);
            scrollPaneLayout.newLine(sep.getBounds().getHeight(), Align.AlignX.CENTRE);
            scrollPane.add(l);
            scrollPane.add(sep);
        }
        scrollPaneLayout.finalAlign(Align.AlignY.TOP, -50);


        getGui().add(quitButton);
        getGui().add(checkBox);
        getGui().add(debugDraw);
        getGui().add(yourName);
        getGui().add(nameInput);
        getGui().add(slider);
        getGui().add(sliderLabel);
        getGui().addAll(radioGroup);
        getGui().add(green);
        getGui().add(blue);
        getGui().add(left);
        getGui().add(right);
        getGui().add(sliderBar);
        getGui().add(waitingIndicator);
        getGui().add(barsWaitingIndicator);
        getGui().add(scrollPane);

        getGui().setDrawBounds(false);
        getGui().setOutlineFocus(true);

        getPhysicsWorld().setFriction(0.008f);
        getPhysicsWorld().setStoppingThreshold(.1f);
        add(new TestEntity(new BoundingBox(160, 90, 100, 100)));
        add(new Entity(new BoundingBox(0, 0, 50, 50), new OvalEntityRenderer()) {
            {setAffectedByPhysics(false);}
            Vector2f lastCollisionVector = Vector2f.zero();
            @Override
            public void renderAfter(final KopfkinoGraphics graphics) {
                graphics.setColor(Colors.ACTIVE_GREEN);
                graphics.drawPoint(getBoundingBox().getCentre().plus(lastCollisionVector.times(new Vector2f(-100, -100))), 25);
            }

            @Override
            public void fixedUpdate() {
                setPosition(Input.cursorPosition().minus(getSize().divBy(Dimensions.two())));
            }

            @Override
            public void collisionStart(final Collision collision) {
                getRenderer().getRenderConfig().setColor(Color.RED);
            }

            @Override
            public void collisionEnd(final Entity partner) {
                getRenderer().getRenderConfig().setColor(Color.BLACK);
            }

            @Override
            public void collision(final Collision collision) {
                lastCollisionVector = collision.getCollisionNormal();
            }
        });
        entitySnapshot().get(1).setHitbox(new CircleHitbox(entitySnapshot().get(1).getBoundingBox()::getCentre, 25f));
        add(new Entity(new BoundingBox(-500, 1030, 3000, 50)){{
                setAffectedByPhysics(false);}});
        add(new Entity(new BoundingBox(-500, 0, 3000, 50)){{
            setAffectedByPhysics(false);}});
        add(new Entity(new BoundingBox(0, 0, 50, 3000)){{
            setAffectedByPhysics(false);}});
        add(new Entity(new BoundingBox(1870, 0, 50, 3000)){{
            setAffectedByPhysics(false);}});
        add(new KeyframeAnimationShowcase());
    }
}
