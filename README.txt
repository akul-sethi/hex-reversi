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

Added this so that the view can not mutate the model. Split all previous methods between this
and ReversiModel.
ReadOnlyReversiModel (interface)
    - getLeftCol()
    - getRightCol()
    - getTopRow()
    - getBotRow()
    - boolean validMove(LinearCoord coord)
    - Player getWinner()
    - Player nextToPlay()
    - Player playerAt(LinearCoord coord)
    -int getPlayerScore(Player p)
        - Added so that there is a way to retrieve the score of a player.
    - ReversiModel getModel()
        - Returns a shallow mutable copy of the model. Allows for the strategies to experiment
          with different moves and see their effects
        - Must be implemented in concrete implementations and not the Abstract class as it
          needs to return the correct type.
This is a complete version of a model
ReversiModel (interface) extends ReadOnlyReversiModel
    - pass()
    - placePiece(LinearCoord coord)

This was created as a way to express a move as an object.
Model methods were refactored to use this.
LinearCoord (class)
     - row()
     - column()

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

View Design:
    Usage:
        Create view using: new GUIReversiView(ReadOnlyReversiModel model)
        Select cell by clicking on it
        Send a request to move to selected cell by typing m
        Send a request to pass by typing p
    Key components:
        ReversiView - Describes the interface to interact with all views
        BoardView - Describes the interface to interact with the view of a Reversi board
        Features - Describes the features which all Reversi games must support. Includes a request
                   to move to a location and a request to pass turn.
        GUIReversiView - A concrete implementation of a ReversiView which uses a BasicBoardView
        BasicBoardView - A concrete implementation of a BoardView which supports move previewing
        Hexagon - Describes a Hexagon with its location and Shape
        TextReversiView - Describes a text based view
        Tile - Describes a tile in a game of Reversi which includes its shape, location, and
               occupying Player
Modified Source Organization:

EXTRA CREDIT:
        If you look in the strategy package, you can see we implemented all four of the suggested
    strategies.
        We also designed the strategies to be perfectly modular. We did this by having the actual
    individual strategies be represented as FallibleReversiStrategies, meaning they don't always
    return a move, and they return a list of moves, instead of always a singular move.
    A FallibleReversiStrategy is turned into a strategy that always returns one move, an
    InfallibleReversiStrategy, by going through the entire strategy, then sorting the returned
    moves (if there are more than one) by upmost leftmost coordinates, then returning the first
    move in the list.
        The mechanism used to combine strategies is the class TryTwo. TryTwo takes in two strategies.
    It first attempts the first strategy, and if there is no decisive outcome (one move), given by
    the first strategy, then it passes the list of moves (or empty list of moves) to the second
    strategy, which will determine which of the moves is better (if it can).
        Multiple TryTwo's can be combined, to create many layer deep strategies. For example, as you
    can see in our player package, we have created a SuperStrategyPlayer. This player contains a
    custom strategy, made with four TryTwo's. The strategy will first run the board through the
    CaptureCornersStrategy. If it doesn't find a singular corner to capture, it will pass the list
    of moves it found (either empty or multiple) to the AvoidNextToCorners strategy.
    In the case that there were multiple corners to capture, the AvoidNextToCorners strategy will
    find that none of the possible moves it was passed are next to corners, and will pass the list
    of moves on to the next strategy. In the case that there weren't any corners to capture, the
    AvoidNextToCorners strategy will now rescan the board for all possible moves, eliminate the
    moves next to the corners from the list, and return that list. This process continues, with the
    minimax and then capture max strategies. If in the end, there are still multiple moves returned
    from the last strategy (capture max in this case), then the FallibleReversiStrategy,
    in our case, we created CompleteReversiStrategyFromFallible, will return the upper leftmost
    move from the list.
        Any of the strategies we created can be combined in any order, and given to a player. The
    player will then turn the FallibleStrategy given to it into an InfallibleStrategy.
        The InfallibleStrategy, also, if it can't find a single possible move, will throw an
    IllegalStateException, which we intend the constructor to interpret, and call pass() on the
    model.