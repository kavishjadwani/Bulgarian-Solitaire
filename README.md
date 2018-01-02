# Bulgarian-Solitaire
The program models the game Bulgarian Solitaire. This program will run in the console window and does not have a GUI. 
The game starts with 45 cards. (They need not be playing cards. Unmarked index cards work just as well.) The cards are randomly divided
into some number of piles of random size. For example, the game might start with piles of size 20, 5, 1, 9, and 10. In each round of game
the program takes one card from each pile, forming a new pile with these cards. For example, the starting configuration would be 
transformed into piles of size 19, 4, 8, 9, and 5. The solitaire is over when the piles have size 1, 2, 3, 4, 5, 6, 7, 8, and 9, in some
order. (It can be shown that game always end up with such a configuration.)
In the normal mode of operation the program will produce a random starting configuration and print it. It then keeps applying the 
solitaire step and printing the results, and stops when the final configuration is reached.

The program runs in a few different modes, each of these controlled by a command-line argument. The user may supply one or both of 
the arguments, or neither.
-u : Prompts for the initial configuration from the user, instead of generating a random configuration.
-s : Stops between every round of the game. The game only continues when the user hits enter.

Sample output with -u option turned on. 

Number of total cards is 45
You will be entering the initial configuration of the cards (i.e., how many in each pile).
Please enter a space-separated list of positive integers followed by newline:
40 1 1 1 1
ERROR: Each pile must have at least one card and the total number of cards must be 45
Please enter a space-separated list of positive integers followed by newline:
44 b 1 x
ERROR: Each pile must have at least one card and the total number of cards must be 45
Please enter a space-separated list of positive integers followed by newline:
100 -55
ERROR: Each pile must have at least one card and the total number of cards must be 45
Please enter a space-separated list of positive integers followed by newline:
0 45
ERROR: Each pile must have at least one card and the total number of cards must be 45
Please enter a space-separated list of positive integers followed by newline:
40 1 1 1 1 1
Initial configuration: 40 1 1 1 1 1
[1] Current configuration: 39 6
[2] Current configuration: 38 5 2
[3] Current configuration: 37 4 1 3
. . .
[30] Current configuration: 10 2 3 4 5 6 7 8
[31] Current configuration: 9 1 2 3 4 5 6 7 8
Done!

Sample output with the -s option turned on (the -u option is not set in this example, so it uses a random initial configuration).
Only part of the run is shown here. After each "<Type return...>" the program blocks until the user hits the return key.

Initial configuration: 9 4 6 26
[1] Current configuration: 8 3 5 25 4
<Type return to continue>
[2] Current configuration: 7 2 4 24 3 5
<Type return to continue>
[3] Current configuration: 6 1 3 23 2 4 6
<Type return to continue>
. . .
[26] Current configuration: 2 3 4 5 6 7 8 10
<Type return to continue>
[27] Current configuration: 1 2 3 4 5 6 7 9 8
<Type return to continue>
Done!
If neither argument is set, then the program will take no user input, and just show the initial configuration followed by the numbered
result of each round until it finishes (i.e., output like the second example above, but without the lines that say "<Type return...>").
