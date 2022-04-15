package cs3500.view;

import java.awt.*;

import javax.swing.*;

import cs3500.controller.Clock;
import cs3500.controller.TimeKeeper;
import cs3500.model.ReadAnimator;

/**
 *
 */
public class InteractiveView extends JFrame implements IInteractiveFeatures {
  private final ReadAnimator model;
  private final TimeKeeper clock;
  private int speed;
  private boolean isPlaying;
  private boolean isLooping;

  private final VisualViewPanel animationPanel;
  private final JPanel buttonPanel;
  private final JPanel labelPanel;

  private JButton startButton;
  private JButton pauseButton;
  private JButton restartButton;
  private JButton loopButton;
  private JButton increaseSpeedButton;
  private JButton decreaseSpeedButton;
  private JButton exitButton;

  private JLabel animationPlayingLabel;
  private JLabel speedLabel;
  private JLabel loopingLabel;

  /**
   *
   * @param model
   */
  public InteractiveView(ReadAnimator model) {
    super();

    // initialize data
    this.model = model;
    this.clock = new Clock();
    this.speed = model.getTickRate();
    this.isLooping = false;
    this.isPlaying = false;

    // set up frame
    this.setTitle("The Easy Animator Visual View");
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // set up animation panel
    this.animationPanel = new VisualViewPanel(model, clock);
    this.add(animationPanel, BorderLayout.CENTER);

    // set up button panel
    this.buttonPanel = createButtonPanel();
    this.add(buttonPanel, BorderLayout.SOUTH);

    // set up label panel
    this.labelPanel = createLabelPanel();
    this.add(labelPanel, BorderLayout.NORTH);
  }

  /**
   *
   * @return
   */
  private JPanel createLabelPanel() {
    JPanel labels = new JPanel();
    labels.setBackground(Color.gray);
    labels.setLayout(new FlowLayout());

    this.animationPlayingLabel = new JLabel(getAnimationPlayingString());
    this.speedLabel = new JLabel("[ Animation speed : " + this.speed + " ]");
    this.loopingLabel = new JLabel(getIsLoopingString());

    labels.add(animationPlayingLabel);
    labels.add(speedLabel);
    labels.add(loopingLabel);

    return labels;
  }

  /**
   *
   * @return
   */
  private String getIsLoopingString() {
    if (isLooping) {
      return "[ Animation looping is on ]";
    } else {
      return "[ Animation looping is off ]";
    }
  }

  /**
   *
   * @return
   */
  private String getAnimationPlayingString() {
    if (this.isPlaying) {
      return "[ Animation is playing ]";
    } else {
      return "[ No animation is playing ]";
    }
  }

  /**
   *
   * @return
   */
  private JPanel createButtonPanel() {
    JPanel buttons = new JPanel();
    buttons.setBackground(Color.gray);
    buttons.setLayout(new FlowLayout());

    this.startButton = new JButton("Start");
    this.pauseButton = new JButton("Pause/Resume");
    this.restartButton = new JButton("Restart");
    this.loopButton = new JButton("Enable/Disable Loop");
    this.increaseSpeedButton = new JButton("Increase Speed");
    this.decreaseSpeedButton = new JButton("Decrease Speed");
    this.exitButton = new JButton("Exit");

    buttons.add(startButton);
    buttons.add(pauseButton);
    buttons.add(restartButton);
    buttons.add(loopButton);
    buttons.add(increaseSpeedButton);
    buttons.add(decreaseSpeedButton);
    buttons.add(exitButton);

    return buttons;
  }

  @Override
  public void render() {

  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void moveFrame() {

  }

  @Override
  public void startAnimation() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void resume() {

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
}
