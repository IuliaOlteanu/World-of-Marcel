# World-of-Marcel

The task was completed over three weeks, and the degree of difficulty was medium to difficult (especially in the case of creating the graphic interface).

For the graphic interface of the game, I created several classes. The Logo class is the start page of the game, where there is an image with the name of the game and a start button. If you press the start button, a login window opens where you are expected to enter your email and password. If the LOGIN button is pressed and the authentication data are correct, a window appears where the text "Login successful" is displayed. When you press the ok button, a new window opens with information about the player (name, country and favorite games extracted from json), and two buttons Start game and Choose character. If the Start game button is pressed, a corresponding message appears (You didn't select a character). If the Choose character button is selected, another window opens where the characters from the character list for the respective account are displayed. Choose a character and the game begins. The login page and the selection of a character to start the game are made in the Authentication class, where there are two other internal classes NewPage and Selection. The NewPage class displays the current information about an account, and the Selection class represents the class in which the choice of a character will be made to start the game. The Final Page class represents the end page of the game where the experience gained, the accumulated level and the 3 attributes (power, charisma and dexterity) after winning the game are displayed.

**➢ Game Class**

➢ The run method receives as a parameter a string of characters that represents the way in which you want to start the game. If you want to play in the console (CLI), choose a character from the list of characters associated with the chosen account. If the mode is GUI, the graphical interface opens. The chooseAccount() method returns an account from the list of accounts, and the chooseCharacter() method returns a PlayableCharacter, which will be used in the createCharacter() method. In this method, a character of type Character is returned, using the Factory design pattern. In this method, the character has in his inventory two potions (ManaPotion and HealthPotion), which have the values of price, weight and regeneration value assigned randomly from a given interval. Similarly for the skills, I added the 3, Ice, Fire and Earth, with random values for the damage value and the mana cost. The character returned by the createCharacter() method returns a character that has the current life generated from an interval, the maximum life generated from an interval, the current hand and the maximum hand generated from a given interval, the values of true or false for the Ice protections, Fire and Earth are random data, using Random().nextBoolean(), the position on the map is 0(ox) and 0(oy), experience and current level are extracted from json, the values for charisma, power and dexterity are also generated randomly , and the profession is taken from json.

➢ The showStory() method displays a story depending on the cell type and an index\
➢ The displayPotionList() method displays, if the character is on a Shop cell, the list of available potions if one wants to buy one. If you want to buy a potion, it is added to the character's inventory and is removed from the Shop.\
➢ The displayAttack() method displays the combat methods (receiveDamage, useAbility, getDamage, regenerateLife, regenerateChackra). The first to attack is the current player, who chooses a method from those listed above, following which the enemy randomly chooses a value between 0 and 4 to counter the attack.

**➢ Account Class**

➢ In the Information class, we created the InformationBuilder class to instantiate an element using the Builder design pattern. The build method performs the construction, checking if the creation of an object without credentials or name is attempted.

**➢ Grid Class**

To instantiate a Grid object, I used the Singleton Pattern. I added an instance member of the initial null Grid type, and the getInstance() method checks if the instance is null and returns new Grid(). To generate the map, positioning enemies and random stores, initially I allocated space to the list of cell lists, the cell is Empty and unvisited. Depending on how many enemies and how many shops you want to be on the map, the length and width are randomly generated between 0 and longitude-1/width-1 and are added to the matrix. The generate2 method returns the map that corresponds to the hardcoded one in the enunt. The movements on the map are similar, simply move the character on Ox or Oy depending on the method. Also here there are 2 methods of displaying the map, a display() method that shows concretely where the enemy is, free cells, cells where you can buy potions and the finish cell, and the displayMap() method hides the cells displayed above, knowing only the current position of the player on the board. (if a cell is not visited, is it displayed?) (I tried to take into account the examples in the statement of the request). 

I also added Start to the list, to position the character at an initial position.
In addition to the required classes, I created the GenerateRandom class that generates a number given two limits for the number. If the formula generates a negative number, the value 0 is returned. The generation method from the class generates the desired number, which can also take the values of the given limits. Also, in the Inventory class I added a member weight_cr to keep the current weight of the inventory. In the Potion addition and Potion removal methods, the current weight changes depending on the method, and to calculate the remaining weight of the inventory, there is the calculateRemainingWeight() method. In the CharacterFactory class, the Factory design pattern is implemented, the getCharacter() method returns a Mage, Warrior, Rogue type character depending on the character's profession.

To read the stories from json, I created the Story class whose members are the cell type (Start, Enemy, Empty, Shop, Finish) and a character string for the story corresponding to the cell type. I did the same for reading the accounts, the PlayableCharacter class contains the name, profession, level and experience of the character from the json file. For reading data from json files, there is the ObjectReaderWrapper class, the readAccounts() method creates the list of accounts (an ObjectMapper type object is used), and the readStories() method returns the desired dictionary, the index being the cell, and the value corresponding to the index represents a list of strings of characters.

Game testing takes place in the Game Testing class. Depending on the user's preference (CLI or GUI), the user chooses which account he wants to play with. If CLI is chosen, after choosing the account, choose the character from the list of characters corresponding to the account and the game starts.

By pressing the P key, the options menu appears with the corresponding moves (N, S, E, V). If a wrong command is entered, an InvalidCommandException exception is thrown. If GUI is chosen, an account is chosen and the game start window opens.
