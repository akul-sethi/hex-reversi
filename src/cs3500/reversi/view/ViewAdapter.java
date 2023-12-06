package cs3500.reversi.view;

import cs3500.provider.model.IReversiModel;

public class ViewAdapter implements IReversiModel, ReversiView {
  cs3500.provider.view.ReversiView adaptee;
  public ViewAdapter(cs3500.provider.view.ReversiView adaptee) {
    this.adaptee = adaptee;
  }

}
