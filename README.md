# game-of-three

## Running the app

First you need to run the server, to do this run `mvn clean package` it will generate a spring-boot runnable jar 
inside target directory, after this run `java -jar gameofthree-0.0.1-SNAPSHOT.jar` the game serve is ready to receive connections.

To run the client, run `mvn clean package` it will generate a spring-boot runnable jar inside target directory, then you can
run the client with `java -jar GameOfThreeClient.jar`

You need to instances of the client running to be able to play.

Also you can set the environment variable `game_mode_automatic=true` so that one or both players plays automatically as soon as
one of them send the first number.

## Rules

When a player starts, it incepts a random (whole) number and sends it to the second
player as an approach of starting the game. The receiving player can now always choose
between adding one of {-1, 0, 1} to get to a number that is divisible by 3. Divide it by three. The
resulting whole number is then sent back to the original sender.
The same rules are applied until one player reaches the number 1(after the division).
