package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import cs3500.model.IAnimator;
import cs3500.model.shape.IShape;
import cs3500.view.IVisual;

/**
 *
 */
public class Controller implements IController {
  private Timer timer;
  private List<IShape> mostRecentStatesOfShapes;
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
        view.incrementCurrentTick();
      }
    };

    this.timer = new Timer(1000 / model.getTickRate(), performTask);
    this.currentTick = 0;
    this.mostRecentStatesOfShapes = new ArrayList<>();
    this.view = view;
    this.model = model;
  }

  @Override
  public void start() {
    view.makeVisible();
    timer.start();
  }

  @Override
  public int getCurrentTick() {
    return currentTick;
  }
}
