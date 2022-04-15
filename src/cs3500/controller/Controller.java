package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import cs3500.model.IAnimator;
import cs3500.view.IVisual;

/**
 * Represents the implementation of the controller for the visual view.
 */
public class Controller implements IBasicController, ActionListener {
  private final IAnimator model;
  private final IVisual view;
  private final Timer timer;

  /**
   * Constructs a controller
   * @param view
   * @param model
   */
  public Controller(IVisual view, IAnimator model) {
    this.model = model;
    this.view = view;
    this.timer = new Timer(1000 / model.getTickRate(), this);
  }

  @Override
  public void start() {
    view.makeVisible();
    timer.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    view.render();
    view.moveFrame();
  }
}
