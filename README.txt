Overview:
This codebase is for creating the game of Reversi but on a hex board. The rules of reversi are
simple: Each player takes turns placing pieces on a board and if you surround an opponents row on
both sides by your pieces you capture all of them. A more detailed explanation can be found on:
https://course.ccs.neu.edu/cs3500/hw_reversi_01_assignment.html. Some background in hex grid
coordinate systems will also be helpful in understanding the code base. That can be found here:
https://www.redblobgames.com/grids/hexagons/.

The code has been designed to be extendable and scalable in several ways. New implementations can
be made with only around 30 lines of code to have different starting board shapes, starting piece
locations, and number of players. However, there is a limitation to this in that the shape of the
board must be any subset of an "odd-r" grid as found on the hex board's website.
There is also the ability to create various ways to view the game such as textual or GUI based.
Finally, a controller must be implemented to actually play the game.

Quick start:

    Create a basic hex board reversi game with side lengths of 3 and a view:
        ReversiModel basic = KlondikeCreator.create(GameType.BASIC, 3);
        Appendable out = new StringBuilder();
        ReversiView view = new TextReversiView(basic, out):
    Interact with it as follows:
        basic.startGame(); to start the game
        basic.placePiece(1, 2); Player one places a piece at row 1 column 2
        view.render(); to render the current state of the board to the output
        basic.placePiece(2, 1) Player two places a piece at row 2 column 1
        view.render(); to render the current state of the board to the output
        basic.pass(); for Player one to pass as they have no moves
        basic.pass(); for Player two to pass as they have no moves
        basic.getWinner(); returns the Player which won, null for a draw, and an error if no one won

Key components:
The center of the code base is the model package. Within it, is stored the state of
the game and the methods which drive the game. There are three parts of the model which are publicly
facing: The ReversiModel interface, the KlondikeCreator class, and the GameType enum. The
ReversiModel interface provides an interface through which any ReversiModel's data can be accessed
and interacted with. The GameType enum provides different ways the game can be played, with BASIC
being the game described in the above Overview section. The KlondikeCreator class is a factory class
which allows for different types of games to be created without exposing their constructors. The
view package defines ways the model can be viewed, and is driven by the ReversiModel interface. It
has a ReversiView interface which defines how a view renders a model and is abstracted away from any
specific view type. The controller package which is yet to be implemented will make queries to the
model and drive both the model and view. Finally, the Player interface describes how to interact
with various Player types which can be either Human, AI, or something else.
Key subcomponents:
    model package:
        ReversiModel - Describes the public interface to interact with any Reversi model
        AReversiModel - An abstract class representing the core of Reversi models and describing
                        common functionality within all Reversi games
        BasicReversi - A concrete implementation of the basic Reversi game which can be created
                       with varying side lengths and different starting Players
        GameType - Represents a type of game
        ReversiCreator - A factory for creating Reversi models without exposing their constructor
        CubeCoord - Represents a cube coordinate as described the hex board resource in the Overview
        Row - Represents a row in a hex board. It describes its direction, length, and starting
              coordinate
        UpLeft - A row which points up and to the left
        UpRight - A row which points up and to the right
        Right - A row which points to the right
        DownRight - A row which points down and to the right
        DownLeft - A row which points down and to the left
        Left - A row which points to the left
    view package:
        ReversiView - Describes how to interact with a Reversi view regardless of its type
        TextReversiView - A concrete implementation of a view which is text based
    Player - Describes an interface for Players which can be Human, AI, or something else
    HumanPlayer - A concrete implementation of a human player
Source organization:
src - All the source code for the game:
    cs3500 - Just a container for organization:
        reversi - Just a container for organization:
            model - Package containing all model components
            view - Package containing all view components
test - Contains all tests which test public facing code. ReversiExamples gives a small overview and
       ModelInterfaceTests do more in depth testing.
    cs3500 - Just a container for organization:
        reversi - Just a container for organization:
            model - Contains all test which are private to the model implementation

Changes for part 2:
    ReadOnlyReversiModel (interface)
        Added:
        -All the previous observation methods.
        ReversiModel getModel()
        -Gets a copy of the model and returns it to the caller. Useful for strategy implementation.
        -This has to be added to each implementation of ReversiModel, and not the Abstract class
         because it must return the appropriate type of model.
         int getPlayerScore(Player)
         -Returns the int score (num of tiles) for the given player.

    BasicReversi (class)
        Added:
        ReversiModel getModel()
        -Returns a copy of this model, for strategy implementation.
        BasicReversi(HashMap<CubeCoord, Player>, List<Player>)
        -Constructor for reconstructing the game at a specific stage.

    Player (interface)
        Added:
        int[] getMove(ReadOnlyReversiModel)
        -Gets the move from the player. This was needed because we previously
         had no way for the player to interact with the board.
        -Returns an int[] of row, col for the move to be made

    AbstractPlayer (class)