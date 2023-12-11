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
    LinearCoord getMove(ReadOnlyReversiModel)
    -Gets the move from the player. This was needed because we previously
    had no way for the player to interact with the board.
    -Returns a LinearCoord of row, col for the move to be made, or throws IllegalStateException
    if no move to be made

Strategy Design:
    Usage:
        Create a strategy implementing fallibleReversiStrategy or InfallibleReversiStrategy
        Combine strategies using TryTwo, passing it strategy 1 and 2.
        Get a move from a player by calling chooseMove, which uses the strategy that the player has
    Key Components:
        FallibleReversiStrategy - Describes the interface to create an incomplete strategy
        InfallibleReversiStrategy - Describes the interface to create a complete strategy
        TryTwo - Describes a combinator for two strategies, tries the first strategy then the second
        Utils - Contains helper methods for the strategy methods.
        CompleteReversiStrategyFromFallible - Converts a fallibleReversiStrategy to a complete,
            infallible reversi strategy, throwing an error if can't find a move from the strategy.
        CaptureCornersStrategy, CaptureMaxStrategy, AvoidNextToCornersStrategy, MiniMaxStrategy -
            individual strategies that do as their titles describe.

Player Design:
    Usage:
        Create a player implementing the Player interface, pass it a strategy, and a name
        get a move from a player by calling getMove, which calls the strategies chooseMove method.
    Key components:
        Player - Describes the interface that represents a player. Has methods to getMove from strat
        AbstractPlayer - Describes the abstract class that represents a player with a strategy and
            a name. Hashes out all the necessary methods. Creates a CompleteStrategy from the
            FallibleStrategy that is passed in.
        CaptureMaxPlayer, HumanPlayer, MiniMaxPlayer, StrategyPlayer, SuperStrategyPlayer -
            The player classes with corresponding strategies. These players will always have the
            strategies that they are named. The strategyPlayer can take in a strategy, and the
            SuperStrategyPlayer has a custom, combined strategy that we created. The humanPlayer
            also has a custom strategy that always returns empty, which we intend to have the
            controller interpret.

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

Changes for part 3
New interfaces:
    ModelObserver - Represents an object which observers ReversiModels and allows the model to
                    notify it when a players turn changes or the game is over.
    InputObserver - Represents an object which observes an input source such as a Player or
                    ReversiView and allows the source to notify it about move requests it would like
                    to make.
    ReversiController - Represents an object which can orchestrate how ReversiModels, ReversiViews,
                        and Players interact. It is simply both an InputObserver and ModelObserver.
New classes:
    BasicReversiController -  A concrete implementation of a ReversiController where each controller
                              is in charge of its own ReversiView and Player. It is designed so that
                              they all share one model.
    Name(enum) - Describes the name of a player and restricts the values to "X" and "O" so that
                 players can not be created with alternate names.

Model changes:
    addObserver(ModelObserver obs) - This method allows models to attach observers to themselves
                                     and make publications when they deem fit. Publications are
                                     made when the turn changes and when the game is over.
    startGame() - This creates a distinction between setting up the game and starting it. When
                  this method is called, the model becomes open to input, and also notifies its
                  observers whose turn it is. Side-effect methods were modified to throw errors
                  if the game has not started.
    gameOver() - Added to check when the game is over rather than relying on an error being
                 thrown.
View changes:
    The view can now not be resized, as per feedback we received.
    addObserver(InputObserver obs) - This method allows views to attach observers to themselves
                                     and make publications when a user interacts with them.
    alertMessage(String s) - This method allows a controller to request a view to display an error
                             message if an input to the model throws an error. This allows users to
                             see errors graphically, while not crashing the program.
Player change:
    addObserver(InputObserver obs) - This method allows players to attach observers to themselves
                                     and make move requests to these observers.
    startTurn(ReadOnlyModel model) - This method provides a player with a snapshot of the model, and
                                     signals to it that its turn has begun. If it is a human player,
                                     this will do nothing, as humans utilize the view to send
                                     requests. If it is a machine, it will likely use some strategy,
                                     which utilizes the model, and then send a move request to its
                                     observer.
    Removed all players other than HumanPlayer and MachinePlayer which takes in a strategy to
    request its moves.

Program Usage:
     A Reversi game is played by provided two command line arguments describing
     the types of players in the game. The order of play is determined by the order in which the
     arguments are provided. If an incorrect number of arguments or invalid ones are provided, the
     program prints a relevant message to the console. It allows the following arguments:

     human -> A human player
     capture-max -> A machine player which follows the strategy of capturing the max number of pieces
                     on its turn
     corners -> A machine player which follows the strategy of capturing corners if it can on its
                 turn.
     avoid-corners -> A machine player which tries to avoid the spots next to a corner on its turn
     minimax -> A machine player which tries to minimize the max capture the opponent can do on its
                 turn.

     The program creates two views, one for each player and labels them by number according to the
     order in which they move. Black always goes first.

     Controls:
        - Click on a cell to preview a move. Click on it again to deselect it.
        - Type m to move in the selected cell
        - Type p to pass turn

     Input to a view when it is not your turn will be ignored other than previewing. Additionally,
     we would now like to specify that clicking on a tile which contains a player is prohibited.
     Finally, the game notifies both players when the game is over and provides them with the score.

Changes for Extra Credit:
    Model:
        The model required some abstraction, but minimal refactoring to enable square reversi.
        Since we already had an abstract AReversiModel class that our hex reversi implementation was
        using, all we had to do was create a new BasicSquareReversiModel class, with a different
        configuration of starting tiles.
        We also added a List<Direction> parameter to the abstract model's constructor, which allows
        it to determine the radiating rows from a point dynamically.
    Direction (interface):
        We created a new direction interface that is implemented by both Hex and SquareDirection
        enumerations. These enums have their unique directions in them. The direction interface
        promises that its implementors will be able to tell you:
        1.) The number of axes in the direction (square = 2, hex = 3)
        2.) The change in each of these axes for a given direction
    Row:
        We changed the row class to check the number of axes and use a switch statement to
        determine which type of coordinate constructor to use. This is dynamic, as it could work for
        any type of three or two axis coordinate system, not only square and hex.
    MyShape:
        We created an abstract myShape class to represent the GUI shape we want to construct the
        board with. Hexagon and Square both extend this class. This can easily be used to create any
        other shape with any given number of vertices.
    ABoardView:
        We abstracted our previous HexBoardView class, enabling us to create another board view and
        use it simultaneously with our GUI. The new board views that implement this only need some
        geometric values.
    GUIReversiView:
        We parameterized GUIReversiView to take in a GameType, which for now is either Square or
        hex, and chooses either a square or hex board view. Nothing else was changed.
    ReversiCreator:
        ReversiCreator now takes in a GameType to determine which type of game to create.
    GameType:
        Hex and Square were both added to the GameType enum
    Strategies:
        There was a slight change in our getCorners utility methods. Other than this, we added a
        method in utils to get adjacent points to a given point, and tweaked our nextToCorners utils
        method. No other changes were needed for strategies.

    Controller:
        There were ZERO changes done to our controller.

    Testing:
        We have the following classes for tests:
            - InDepthSquareReversiTests    Tests square reversi edge cases
            - SquareStrategyTests          Tests for the strategies, applied to square games
            - RowTests                     Tests for the new SquareDirection and row features
            - SquareReversiTests           Some very basic large tests for square reversi and text
            - StrategyMockTests            Includes some new tests that mock square strategies
