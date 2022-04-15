package cs3500.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.*;

import cs3500.model.IAnimator;
import cs3500.view.IInteractiveVisual;

/**
 *
 */
public class InteractiveController implements IInteractiveFeatures, ActionListener {
  private final Timer timer;
  private final IAnimator model;
  private final IInteractiveVisual view;

  public InteractiveController(IAnimator model, IInteractiveVisual view) {
    this.model = model;
    this.view = view;
    this.timer = new Timer(1000 / model.getTickRate(), this);

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    switch (cmd) {
      case "Start Button":
        this.start();
        break;
      case "Pause/Resume Button":
        break;
      case "Restart Button":
        break;
      case "Enable/Disable Looping Button":
        break;
      case "Increase Speed Button":
        break;
      case "Decrease Speed Button":
        break;
      case "Exit Button":
        break;
      default:
        //do nothing
    }
  }

  @Override
  public void start() {
    view.makeVisible();
    view.render();
  }

  @Override
  public void pauseResume() {

  }

  @Override
  public void restart() {

  }

  @Override
  public void looping() {

  }

  @Override
  public void increaseSpeed() {

  }

  @Override
  public void decreaseSpeed() {

  }

  @Override
  public void exitProgram() {

  }
}
