package cs3500.reversi.controller;

import cs3500.provider.IReversiFeatures;
import cs3500.reversi.model.ModelAdapter;
import cs3500.reversi.player.Player;
import cs3500.reversi.view.ViewAdapter;

public class FeaturesAdaptor extends BasicReversiController implements IReversiFeatures {

  public FeaturesAdaptor(Player p, ViewAdapter view, ModelAdapter model) {
    super(p, view, model);
  }

  @Override
  public void start() {

  }

  @Override
  public void movePlay() {

  }

  @Override
  public void movePass() {

  }

  @Override
  public void quit() {

  }

  @Override
  public void goMove() {

  }
}
