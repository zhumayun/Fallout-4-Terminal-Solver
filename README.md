# Fallout-4-Terminal-Solver

## About
The Fallout 4 Terminal Solver Android app helps player's of the game [Fallout 4](https://en.wikipedia.org/wiki/Fallout_4) solve ('Hack')
[Terminals](http://fallout.wikia.com/wiki/Terminal). Terminals are essential to finding loot, unlocking new areas, and leveling up your character.

## How Does Terminal Hacking Work?
A typical locked Terminal in fallout 4 will look something like this:

![Locked Terminal](https://i.ytimg.com/vi/eTytWIEnW20/maxresdefault.jpg)

The player is presented with a screen filled with random characters, and in bewteen the random characters (or 'noise'). 
Exactly one of these words is the password to the terminal, and as the player your job is the find the password with the given
amount of attempts (indicated at the top of the screen). 

As the player when you select a word one of three things will happen:
* You guessed the correct password, and the terminal becomes unlocked
* You've ran out of attempts, and the terminal locks itself. The player must try again later, with a new set of words.
* You've guessed the incorrect word, however the terminal gives you something called a "Likeness".

### Likeness
After the player has guessed an incorrect word, a likeness number is given to the player. The number dicates the number of
characters in the word guessed that are in the same position (location) as the terminal's password. 
For example, a likeness of 0 means that **no characters** of the word just guessed are in the same location as the password.

Here are a few more examples. Assume the terminal password is **GUESS**

```
Guessed Word: GHOST - Likeness = 2
```
```
Guessed Word: GUESS - Likeness = 5
```
```
Guessed Word: SSEUG - Likeness = 1 
```

From this information, the terminal password can be narrowed down.

## How Does the App Help?
The Android app eliminates words based on the given likeness of a word, so that players do not have to keep track of this in their head.
By eliminating a large number of words at a time, the app helps narrow down in terminal password in just a few guesses. 
The application also supports undo and restart actions.
