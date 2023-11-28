import java.io.IOException;

import javax.swing.*;

import cs3500.reversi.controller.InputObserver;
import cs3500.reversi.view.ReversiView;

/**
 * A mock implementation of ReversiView which logs all methods calls to it.*/
public class MockViewLogging implements ReversiView {
  public String log;

  MockViewLogging() {
    this.log = "";
  }
  @Override
  public void render() throws IOException {
      this.log += "render()\n";
  }

  @Override
  public void addObserver(InputObserver features) {
      this.log += "addObserver()\n";
  }

  @Override
  public void setVisible(boolean b) {
      this.log += "setVisible(" + b + ")\n";
  }

  @Override
  public void resetFocus() {
      this.log += "resetFocus()\n";
  }

  @Override
  public void setHotKey(KeyStroke keyStroke, String featureName) {
      this.log += String.format("setHotKey(%s, %s)\n", keyStroke, featureName);
  }

  @Override
  public void alertMessage(String message) {
    this.log += String.format("alertMessage(%s)\n", message);
  }
}
