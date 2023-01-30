# Rock Paper Scissors Lab Discussion
#### Changmin Shin (cs521), Brandon Weiss (bmw60), Han Zhang (hz244)


### High Level Design Goals

- GameMode class, which would decide how many Hand implementations there would be.
    - public void makeImplementation(String input)
- Rulebook class, which would decide how 2 kinds of Hands interact depending on either user input or the rules from the file.
    - private Player simpleGame(Player first, Player second)
    - public Player multiGame(List<Player>)
- Player class, which would represent the Player and all of the variables associated with the player
    - public Hand getHand()
    - public void chooseHand(String type)
    - public void winRound()
    - public int getScore()

### CRC Card Classes

This class's purpose or value is to represent a player:

|Player| |
|---|---|
|public String getHand()         ||
|public void chooseHand(String type) | |
|public void winRound()    | |
| public int getScore() ||


This class's purpose or value is to represent a rulebook that decides win, lose, or tie:

|Rulebook| |
|---|---|
|private Player simpleGame(Player first, Player second)      |Player|
|public Player multiGame(List<Player>)   ||



This class's purpose or value is to represent the current GameMode

|Game| |
|---|---|
|        |Rulebook|
| |Player |
| ||

This class's purpose or value is to represent a Player:
```java
public class Player {
    // 
    public String getHand();
    // 
    public void chooseHand(String type);
    //
    public void winRound();
    //
    public int getScore();
    //

 }
 ```

This class's purpose or value is to represent a rulebook that decides win, lose, or tie:
```java
public class RuleBook {
    //
    private Player simpleGame(Player first, Player second);
    //
    public Player multiGame(List<Player>);
    //
    public void addRule(String rule);
 }
```

This class puts the other classes together and runs the game.
```java
public class Game{
    public void addPlayers(File players);
}
```


### Use Cases

* A new game is started with five players, their scores are reset to 0.
 ```java
 Game thing = new Game();
 thing.addPlayers(PLAYER_FILE);
 
 ```

* A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
 player.chooseHand(weapon);
 ```

* Given three players' choices, one player wins the round, and their scores are updated.
 ```java
rulebook.multiGame(players).winRound();
 
 ```

* A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
 Game.rulebook.addRule("Kirk commands Spock");
 ```

* A new game is added to the system, with its own relationships for its all its "weapons".
 ```java
    Game newGame = new Game;
    newGame.rulebook.addRule();    
 ```
