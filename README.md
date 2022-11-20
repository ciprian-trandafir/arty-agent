## Arty Agent 
Arty Agent is a game in which you are shown a small picture and you have to look for it among 3 other forgeries.

The game takes one minute and there are 14 pictures. Are you able to find them all before the time runs out?

#### Files Structure
* classes - classes that do not extend *JPanel* or *JFrame*.
* frames - contains the **main game window** and extends JFrame. 
* icons - here are the icons used by the entire game.
* interfaces - interfaces that help us correctly implement **design patterns**.
* panels - classes that extend *JPanel* class.
* pictures - the directory that is browsed by the *iterator design pattern* and contains the pictures that the player needs to find.
* sounds - contains the audio files that are played when the player chooses the correct picture or not.
* tests - two tests with JUnit:
    * Checking the correct implementation of the **Singleton** design pattern: the equality of two instances of the singleton object, using the getInstance() method;
    * Checking the correct implementation of the **Iterator** design pattern: if the iterator is correctly browsing the pictures collection by initializing it and getting the first directory with pictures;

#### Design patterns
* [Singleton](https://refactoring.guru/design-patterns/singleton) - Used when instantiating the *HomePanel* class.
* [Iterator](https://refactoring.guru/design-patterns/iterator) - Used to scroll through the collection of pictures from which the player must search for a specific one.
* [Memento](https://refactoring.guru/design-patterns/memento) - Store the player's score, and when the game ends, the value is written in a *settings.txt* file to to display it later on the homepage (*HomePanel* class).
* [State](https://refactoring.guru/design-patterns/state) - Manage game state: **playing** or **idle**. **Playing** state is when the player is looking for the given picture and **Idle** state is when the player is in *HomePanel* or *SettingsPanel* class.
* [Adapter](https://refactoring.guru/design-patterns/adapter) - With this pattern we can play several audio formats when the player chooses the searched picture correctly or not.
