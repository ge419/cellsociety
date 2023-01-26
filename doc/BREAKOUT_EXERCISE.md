# Breakout Abstractions Lab Discussion
#### Brandon Weiss, Han Zhang, Changmin Shin


### Block

This superclass's purpose as an abstraction:
```java
 public class Block {
     public int something ()
     // no implementation, just a comment about its purpose in the abstraction 
 }
```

#### Subclasses

This subclass's high-level behavorial differences from the superclass:
```java
 public class X extends Block {
     public int something ()
     // no implementation, just a comment about what it does differently 
 }
```

#### Affect on Game/Level class

Makes it easier to create types of blocks and edit the behavior of different types of blocks. Helps reduce duplication and long columns if statements.


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

### Level

This superclass's purpose as an abstraction:
```java
 public class Level {
     public int something ()
     // no implementation, just a comment about its purpose in the abstraction 
 }
```

#### Subclasses

This subclass's high-level behavorial differences from the superclass:
```java
 public class X extends Level {
     public int something ()
     // no implementation, just a comment about what it does differently 
 }
```

#### Affect on Game class

Makes it easier to create different levels with different settings of the game.

### Others?

Different types of balls or paddles can be implemented with inheritance.