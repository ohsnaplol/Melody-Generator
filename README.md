# Melody-Generator
## Can't think of a tune? Let the computer do it for you!

This program randomizes a set of music notes with a given key signature and plays it back for you using the JFugue API.

## How it works
JFugue uses something called a pattern to create and play music on the fly. Here's an example of one:
```
T200 I\[Honkey_Tonk\] G C E E B E A B G
```
This pattern will play the notes G, C, E, etc. using the built-in Midi intrument Honkey Tonk at 200 beats per minute.
We modify this string pattern depending on what the user wants. When we generate a new melody, we use RNG to select notes from an enumeration that contains the notes in the key signature requested by the user. 
If the user wants a tonic melody, the first and last notes generated will be the same.
The user can save the generated pattern (essentially a text file with the string pattern in it) and load it again into the program.

## Notes
Bit buggy here and there but considering doing a re-write with a different API to alleviate the primary issue (strange Midi playback after playing the melody for the first time.)

### Credits
Worked on this in a group in one of my classes and it was a great experience!

Programmer - Marcello Galvan

Music theory consulting - Yukun Pei

Unit testing - Tony Gavidia

This is a project from my days at UM. Archived for posterity 😎
