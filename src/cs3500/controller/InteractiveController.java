package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

import cs3500.model.IAnimator;
import cs3500.view.IInteractiveVisual;

/**
 * Represents a controller for an interactive view. Responds to button presses and inputs
 * from the user. The controller handles the user input and propagates the changes to the view.
 * By default, the looping is set to false.
 */
public class InteractiveController implements IInteractiveFeatures {
  private final TimeKeeper clock;
  private final Timer timer;
  private final IAnimator model;
  private final IInteractiveVisual view;
  private boolean loop;

  public InteractiveController(IAnimator model, IInteractiveVisual view) {
    this.clock = new Clock();
    this.loop = false;
    this.model = model;
    this.view = view;
    int lastTick = model.getEndingTick();
    this.timer = new Timer(1000 / model.getTickRate(), e -> {
      view.render();
      view.moveFrame();
      clock.increaseTime();
      if (clock.getTime() == lastTick && loop) {
        clock.reset();
        view.reset();
        model.resetShapes();
      }
    });
    setView(view);
  }

  @Override
  public void start() {
    view.makeVisible();
  }

  @Override
  public void setView(IInteractiveVisual view) {
    view.addFeatures(this);
  }

  @Override
  public void pauseResume() {
    if (timer.isRunning()) {
      timer.stop();
    } else {
      timer.start();
    }
  }

  @Override
  public void restart() {
    timer.restart();
    view.reset();
    clock.reset();
    model.resetShapes();
  }

  @Override
  public void looping() {
    loop = !loop;
  }

  @Override
  public void increaseSpeed() {
    model.setTickRate(model.getTickRate() + 1);
    timer.setDelay(1000 / model.getTickRate());
  }

  @Override
  public void decreaseSpeed() {
    if (model.getTickRate() - 1 <= 0) {
      return;
    }
    model.setTickRate(model.getTickRate() - 1);
    timer.setDelay(1000 / model.getTickRate());
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void play() {
    timer.start();
    view.render();
  }
}
