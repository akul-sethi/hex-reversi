package cs3500.reversi.controller;

import cs3500.provider.IReversiFeatures;
import cs3500.provider.model.Hexagon;
import cs3500.provider.view.IReversiView;
import cs3500.reversi.model.AdapterUtils;
import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.CubeCoord;

/**
 * An adapter which adapts an InputObserver into an IReversiFeatures.
 * Necessary so that our controller can subscribe itself as a listener to the provider view as
 * it was designed to take an IReversiFeatures.
 */
public class InputToFeatures implements IReversiFeatures {

  private final InputObserver delegate;
  private final IReversiView view;

  public InputToFeatures(InputObserver adaptee, IReversiView view) {
    this.delegate = adaptee;
    this.view = view;
  }

  @Override
  public void start() {
    //THIS HAS NO EQUIVALENT IN INPUTOBSERVER
  }

  @Override
  public void movePlay() {
    try {
      Hexagon selected = this.view.getSelectedHex();
      CubeCoord cube = AdapterUtils.hexagonToCubeCoord(selected);
      this.delegate.moveHere(new BasicPoint(cube.row(), cube.column()));
    } catch (IllegalStateException e) {
      this.view.showErrorMessage("No tile selected");
    }
  }

  @Override
  public void movePass() {
    this.delegate.pass();
  }

  @Override
  public void quit() {
    //THIS HAS NO EQUIVALENT IN INPUTOBSERVER
  }

  @Override
  public void goMove() {
    //THIS HAS NOT EQUIVALENT IN INPUTOBSERVER
  }
}
