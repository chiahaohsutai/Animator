package cs3500.view;

import java.awt.*;

import javax.swing.*;

import cs3500.controller.Clock;
import cs3500.controller.IInteractiveFeatures;
import cs3500.controller.TimeKeeper;
import cs3500.model.ReadAnimator;

/**
 *
 */
public class InteractiveView extends JFrame implements IInteractiveVisual {
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
    this.makeVisible();

    // set up animation panel
    this.animationPanel = new VisualViewPanel(model, clock);
    this.add(animationPanel, BorderLayout.CENTER);

    // set up button panel
    this.buttonPanel = createButtonPanel();
    this.add(buttonPanel, BorderLayout.SOUTH);

    // set up label panel
    this.labelPanel = createLabelPanel();
    this.add(labelPanel, BorderLayout.NORTH);

    this.makeVisible();
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

    startButton = new JButton("Start");
    startButton.setActionCommand("Start Button");
    buttons.add(startButton);

    pauseButton = new JButton("Pause/Resume");
    pauseButton.setActionCommand("Pause/Resume Button");
    buttons.add(pauseButton);

    restartButton = new JButton("Restart");
    restartButton.setActionCommand("Restart Button");
    buttons.add(restartButton);

    loopButton = new JButton("Enable/Disable Loop");
    loopButton.setActionCommand("Enable/Disable Loop Button");
    buttons.add(loopButton);

    increaseSpeedButton = new JButton("Increase Speed");
    increaseSpeedButton.setActionCommand("Increase Speed Button");
    buttons.add(increaseSpeedButton);

    decreaseSpeedButton = new JButton("Decrease Speed");
    decreaseSpeedButton.setActionCommand("Decrease Speed Button");
    buttons.add(decreaseSpeedButton);

    exitButton = new JButton("Exit");
    exitButton.setActionCommand("Exit Button");
    buttons.add(exitButton);

    return buttons;
  }

  @Override
  public void render() {
    this.repaint();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void moveFrame() {
    clock.increaseTime();
  }
}
