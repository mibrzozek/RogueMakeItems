# Rogue Make Items

Creates ItemFactory class based on text file for roguelike project. Makes adding items to the game easier, and since there are going to be a lot of items I have streamlined this process early on.

You can find the roguelike project [here](github.com/mibrzozek/RogueOne).

# Design

An item currently takes in 8 parameters; glyh, color, type, name, description, attack, defense, and typeValue. Along with the methodName, these parameters are listed each on their own line in a seperate text file. To add new items to the game all i have to do is modify this file, and appropriate factory methods will be appended to the ItemFactory class.




