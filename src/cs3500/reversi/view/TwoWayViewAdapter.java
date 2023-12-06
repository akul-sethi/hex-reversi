package cs3500.reversi.view;

import java.io.IOException;

import javax.swing.*;

import cs3500.provider.IReversiFeatures;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.provider.view.IReversiView;
import cs3500.reversi.controller.InputObserver;
import cs3500.reversi.controller.InputToFeatures;

public class TwoWayViewAdapter implements ReversiView, IReversiView {
  private final IReversiView adaptee;

  public TwoWayViewAdapter(IReversiView adaptee) {
    this.adaptee = adaptee;
  }


  @Override
  public void render() throws IOException {
    this.adaptee.repaint();
  }

  @Override
  public void addObserver(InputObserver inputObserver) {
    this.adaptee.addFeatureListener(new InputToFeatures(inputObserver, this));
  }

  @Override
  public void setVisible(boolean b) {
    this.adaptee.display(b);
  }

  @Override
  public void resetFocus() {
    this.adaptee.display(true);
  }

  @Override
  public void display(boolean show) {
    this.adaptee.display(show);
  }

  @Override
  public void setHotKey(KeyStroke key, String featuresName) {
    this.adaptee.setHotKey(key, featuresName);
  }

  @Override
  public void addFeatureListener(IReversiFeatures features) {
    this.adaptee.addFeatureListener(features);
  }

  @Override
  public void repaint() {
    this.adaptee.repaint();
  }

  @Override
  public Hexagon getSelectedHex() {
    return this.adaptee.getSelectedHex();
  }

  @Override
  public void deselect() {
    this.adaptee.deselect();

  }

  @Override
  public void showErrorMessage(String error) {
    this.adaptee.showErrorMessage(error);
  }

  @Override
  public void setTitle(HexagonState hexState) {

  }

  @Override
  public void alertMessage(String message) {
    this.adaptee.showErrorMessage(message);
  }
}
