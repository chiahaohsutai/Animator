package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import cs3500.model.IAnimator;
import cs3500.view.IVisual;

/**
 *
 */
public class Controller implements IController {
  private Timer timer;
  private IVisual view;
  private IAnimator model;

  /**
   *
   */
  public Controller(IVisual view, IAnimator model) {
    // set up the action listener to be performed every delay.
    ActionListener performTask = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        view.render();
        view.moveFrame();
      }
    };

    this.timer = new Timer(1000 / model.getTickRate(), performTask);
    this.view = view;
    this.model = model;
  }

  @Override
  public void start() {
    view.makeVisible();
    timer.start();
  }
}
