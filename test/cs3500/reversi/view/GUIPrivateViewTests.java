package cs3500.reversi.view;

import org.junit.Assert;
import org.junit.Test;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Optional;

import javax.swing.*;

import cs3500.reversi.controller.ControllerWithLog;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;

/**
 * A class to tests view tests that are not exposed outside the cs3500.reversi.view package.
 */
public class GUIPrivateViewTests {
  @Test
  public void testSendsPassToObserver(){
    ControllerWithLog controller = new ControllerWithLog();
    ReversiModel model =  ReversiCreator.create(GameType.BASIC, 6);
    GUIReversiView view = new GUIReversiView(model);
    view.addObserver(controller);
    view.performAction("pass", null);
    Assert.assertEquals(controller.log, "pass()\n");

  }

  @Test
  public void testSendsMoveToObserverIllegalWithinBounds(){
    ControllerWithLog controller = new ControllerWithLog();
    ReversiModel model =  ReversiCreator.create(GameType.BASIC, 6);
    Hexagon hex = new Hexagon(0, 0, 100);
    GUIReversiView view = new GUIReversiView(model);
    view.getBoardMouseListeners()[0].mouseClicked(new MouseEvent(view, 0,
            0L, 0, -50, 220, 1, false));
    view.addObserver(controller);
    view.performAction("moveHere", null);
    Assert.assertEquals(controller.log, "");

  }

  @Test
  public void testSendsMoveToObserverIllegalOnPlayer(){
    ControllerWithLog controller = new ControllerWithLog();
    ReversiModel model =  ReversiCreator.create(GameType.BASIC, 6);
    Hexagon hex = new Hexagon(0, 0, 100);
    GUIReversiView view = new GUIReversiView(model);
    view.getBoardMouseListeners()[0].mouseClicked(new MouseEvent(view, 0,
            0L, 0, 304, 195, 1, false));
    view.addObserver(controller);
    view.performAction("moveHere", null);
    Assert.assertEquals(controller.log, "");

  }

  @Test
  public void testSendsMoveToObserverIllegalOutBounds(){
    ControllerWithLog controller = new ControllerWithLog();
    ReversiModel model =  ReversiCreator.create(GameType.BASIC, 6);
    Hexagon hex = new Hexagon(0, 0, 100);
    GUIReversiView view = new GUIReversiView(model);
    view.getBoardMouseListeners()[0].mouseClicked(new MouseEvent(view, 0,
            0L, 0, 96, 220, 1, false));
    view.addObserver(controller);
    view.performAction("moveHere", null);
    Assert.assertEquals(controller.log, "moveHere(-1, -4)\n");

  }

  @Test
  public void testSendsMoveToObserverLegal(){
    ControllerWithLog controller = new ControllerWithLog();
    ReversiModel model =  ReversiCreator.create(GameType.BASIC, 6);
    Hexagon hex = new Hexagon(0, 0, 100);
    GUIReversiView view = new GUIReversiView(model);
    view.getBoardMouseListeners()[0].mouseClicked(new MouseEvent(view, 0,
            0L, 0, 222, 218, 1, false));
    view.addObserver(controller);
    view.performAction("moveHere", null);
    Assert.assertEquals(controller.log, "moveHere(-1, -2)\n");

  }

  @Test
  public void testSendsNoMoveToObserver(){
    ControllerWithLog controller = new ControllerWithLog();
    ReversiModel model =  ReversiCreator.create(GameType.BASIC, 6);
    Hexagon hex = new Hexagon(0, 0, 100);
    GUIReversiView view = new GUIReversiView(model);
    view.addObserver(controller);
    view.performAction("moveHere", null);
    Assert.assertEquals(controller.log, "");
  }

}
