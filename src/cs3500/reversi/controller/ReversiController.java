package cs3500.reversi.controller;

import cs3500.reversi.view.InputObserver;

/**
 * Represents an object which can control a Reversi view, model, and player. It behaves like an
 * InputObserver and ModelObserver so that it can receive  input requests from Players and Views,
 * and be notified when game level events that take place such as player turn switching
 * or the game being over (by the model).
 */
public interface ReversiController extends InputObserver, ModelObserver {
  //THIS IS EMPTY AS IT IS SIMPLY SERVING AS A WAY TO COMBINE TWO TYPES
}
