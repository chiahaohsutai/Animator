package cs3500.controller;

import javax.swing.Timer;

import cs3500.model.IAnimator;
import cs3500.view.visualview.IInteractiveVisual;

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

  /**
   * Constructs a controller that handles the user interaction with buttons in the interactive
   * view. This includes the handling of when the play/pause, restart, enable/disable looping,
   * increase speed, decrease speed, and exit buttons are pressed. The controller keeps track and
   * manages the time accordingly.
   * @param model is the populated model to generate the animation.
   * @param view is the view for the animation. In this case, it is an interactive view.
   */
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
  public void toggleFillOutline() {
    model.toggleFill();
  }
}
