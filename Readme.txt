Welcome to Dugeon of Doom(DoD).

DoD is played on a rectangular grid, which serves as the game's board. You will control a Player tile which can move and pick up gold.
To win the game you must collect the required gold and then exit the dungeon. There is a bot player who acts like a brainless zombie,
the brainless zombie's moves are random, unpredictable and he always moves first. If the brainless zombie touches you (is on the same tile as you) you lose
the game. Every turn you send a command and if the command is successful, an action takes place.


To Start, input the file path of your Map Folder in the mapFolderPath attribute in the GameLogic constructor. This can be found at the top of the
GameLogic class.



Possible player actions:

"HELLO"           - This will display the total amount of gold required for your player to win.

"GOLD"            - This will display your players current gold owned.

"MOVE <direction>" - This will move your player tile in a direction (E = East, S = South, W = West and N = North). Players cannot move
                    into walls, if you try to the game will reply with "UNSUCCESSFUL". However if your move is valid it will reply with
                    "SUCCESSFUL". 
                  
"PICKUP"          - This will pick up 1 gold if you are on a gold tile. If you are not on a gold tile the game will reply with UNSUCCESSFUL
                    (Once you pick up a gold on a gold tile, the gold tile becomes a normal tile)

"LOOK"            - This will display a 5x5 grid showing the map around your player, with your player at the CENTER.

"QUIT"            - Depending on whether your player has met the requirements to exit (I.E. the player has the required gold for the map and is on a exit tile)
		    you will win the game. However if you haven't met the requirements you will exit and lose the game with your players progress being deleted.


If you try to type in a unknown command the game will reply with "INVALID". All commands take up a players turn, regardless of whether they were SUCCESSFUL or not.



BOARD TILES:

"P" - Player

"B" - Bot

"." - Empty tile

"G" - Gold

"E" - Exit

"#" - Wall 



Custon Maps

To create your custom map it must be done in a Notepad file similiar to this README file. Gold tiles must be represented
with a capital G. and exit tile must be represented with a capital E. the border of the maps should be represented with "#"
and empty tiles should be represented with ".". maps must have 1-5 Gold tiles and 1-2 Exit tiles. but can be as large or
small as you like. please format your map file like below, staring with the name of the map, how many gold you need to
win then the map design it self, failure to follow map file format will result in errors!

name: your Map Name
win: number referring to how many gold a player needs to win
###################
#.................#
#.....Insert......#
#.......map.......#
#......design.....#
#......here.......#
#.................#
#.................#
###################

Here is an Example of a pre-made map file.

name: exmaple Map
win: 2
####################
#....G.............#
#..................#
#..E...............#
#...............G..#
#..................#
#........G.........#
#..................#
#...........E......#
####################




Implentation of Code

The Main method starts the code and immediately finds a start location for the player and the bot (by randomely generating numbers in a certain range),
making sure they dont start on gold tiles or walls. The player IS allowed to spawn on a exit tile. It then checks for input.

when a player looks i created a seperate 5x5map attribute which gets player location in terms of index and based on player relative position 
in the normal map the code takes a index values in a 5x5 raidus of the players location and loads them into the 5x5 map whilst including 
try catch conditions so that when player is right next to a border the 5x5 maps shows extra walls '#'.

I set up my code so that depending on the direction of the move command the code would change the char variable in the next element North, East,
South or West to "P" and change the char variable in the element it just occupied back to "." which would represent a empty tile.
However i quickly ran into a problem in the event that a player went onto a gold tile but didnt pick it up then moved onto a new tile,
When this would happen the Gold tile would be replaced with a empty tile making it appear as if there was one less gold tile. 
To fix this i created attributes that would contain the location of special tiles.

I created attribute for the x location and y location of all the special attribute. I done this so i could easily create condidtions such as 
whether a move would be SUCCESSFUL or not, Or when a player would use the "PICKUP" command, i would simply use a getter for player x and y
location use another getter for gold tiles and check if the players x and y location would match any of the gold tiles x and y location
to see if the move would be SUCCESSFUL if not move would be UNSUCCESSFUL. Also it meant i could create  attributes which would be set to a 
certain number which would represent if a gold tile was successfully picked up meaning if this attribute still suggested that the gold hadn't 
been picked up after the player move it would reset that gold tile.

The bot is programmed to be a mindless zombie which aimlessly walks around the map avoiding gold and exit tiles, to make things less easy the
bot moves first so in a situation where the player is right next to the bot since the bot move first theres a chance player will be caught.
