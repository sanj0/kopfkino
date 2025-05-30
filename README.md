<div align="center">
      <img src="https://github.com/sanj0/kopfkino/blob/main/logos/grayish/kopfkino_grayish_xs.png" alt="kopfkino" align="center">
</div>

# kopfkino
> _kopfkino_ (noun) processes, events that take place only or mainly in the imagination, in one's own power of imagination.
> (translated from https://www.duden.de/rechtschreibung/Kopfkino)

Kopfkino is a java2d game library that benefits from 5+ years of development on
its predecessor, [Salty Engine](https://www.github.com/sanj0/salty-engine).

## Get started
For information on how to use the engnie, consider taking a look at the [github wiki](https://github.com/sanj0/kopfkino/wiki)
### 1. How to get Kopfkino Engine

Kopfkino engine is built using maven. After cloning the repository, simply build
the project using `mvn clean install`. Now include kopfkino in your own maven
project by appending the following dependency declaration to your pom.xml:

```xml

<dependencies>
  <!--...-->
  <dependency>
    <groupId>io.github.sanj0</groupId>
    <artifactId>kopfkino</artifactId>
    <version>0.0.1-SNAPSHOT</version>
  </dependency>
  <!--...-->
</dependencies>
```

### 2. Start a simple kopfkino game

To get the bare minimum kopfkino running, consider the following code.

```java
import kopfkino.Game;
import scene.kopfkino.EmptyScene;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // initialise the game with a resolutino of 1920x1080 and a nice name
        // the latter is used for example in the window title
        Game.init(1920, 1080, "hello, kopfkino!");
        // Game is a singleton - Game.getInstance() retrieves the instance
        // ... to for example set the background (frame clear) color
        Game.getInstance().setBackgroundColor(Color.WHITE);
        // start the game after a splash screen of 3 seconds
        // into an empty scene,
        // with 5 milliseconds between fixed ticks
        // and 60 frames per second
        Game.start(3000, new EmptyScene(), 5, 60);
    }
}
```
