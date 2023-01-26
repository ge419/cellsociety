### Power-up

This superclass's purpose as an abstraction:
```java
 public class PowerUp {
     public int something ()
     // no implementation, just a comment about its purpose in the abstraction 
 }
```

#### Subclasses

This subclass's high-level behavorial differences from the superclass:
```java
 public class X extends PowerUp {
     public int something ()
     // no implementation, just a comment about what it does differently 
 }
```

#### Affect on Game/Level class

Makes it easier to create powerups with different effects. Helps reduce duplication.