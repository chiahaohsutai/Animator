package cs3500.view;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import cs3500.controller.Clock;
import cs3500.controller.IInteractiveFeatures;
import cs3500.controller.TimeKeeper;
import cs3500.model.ReadAnimator;

/**
 * Represents the interactive view for the animation. The interactive view consists of different
 * buttons that allow the client to control the animation. It includes features such as
 * playing, pausing, restarting, enabling/disabling looping, increasing/decreasing speed,
 * and exiting the animation.
 */
public class InteractiveView extends JFrame implements IInteractiveVisual {
  private final ReadAnimator model;
  private final TimeKeeper clock;
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
   * Constructs the interactive view verison of the GUI. Sets the size, look and buttons.
   *
   * @param model is the read only animator model.
   */
  public InteractiveView(ReadAnimator model) {
    super();

    // initialize data
    this.model = model;
    this.clock = new Clock();

    // set up frame
    this.setTitle("The Easy Animator Interactive View");
    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setSize(new Dimension(900, 600));
    this.makeVisible();

    // set up animation panel
    VisualViewPanel animationPanel = new VisualViewPanel(model, clock);
    animationPanel.setPreferredSize(new Dimension(model.getCanvasWidth(),model.getCanvasHeight()));

    // add scroll
    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(scrollPane, BorderLayout.CENTER);

    // set up button panel
    JPanel buttonPanel = createButtonPanel();
    this.add(buttonPanel, BorderLayout.SOUTH);

    // set up label panel
    JPanel labelPanel = createLabelPanel();
    this.add(labelPanel, BorderLayout.NORTH);

    this.makeVisible();
  }

  /**
   * Creates a label for a panel component in the view.
   *
   * @return for each one for the speed, looping and current state label.
   */
  private JPanel createLabelPanel() {
    JPanel labels = new JPanel();
    labels.setBackground(Color.gray);
    labels.setLayout(new FlowLayout());

    this.animationPlayingLabel = new JLabel("[ Animation is OFF/PAUSED ]");
    this.speedLabel = new JLabel(String.format("[ Tick Rate: %d ]", model.getTickRate()));
    this.loopingLabel = new JLabel("[ Looping is OFF ]");

    labels.add(animationPlayingLabel);
    labels.add(speedLabel);
    labels.add(loopingLabel);

    return labels;
  }

  /**
   * Creates the panel holding all the buttons. Organizes the buttons in a row (flow layout).
   *
   * @return the populated panel golding the buttons.
   */
  private JPanel createButtonPanel() {
    JPanel buttons = new JPanel();
    buttons.setBackground(Color.gray);
    buttons.setLayout(new FlowLayout());

    pauseButton = new JButton("Play/Pause");
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

  @Override
  public void addFeatures(IInteractiveFeatures feat) {
    pauseButton.addActionListener(evt -> feat.pauseResume());
    pauseButton.addActionListener(evt -> setStart());
    restartButton.addActionListener(evt -> feat.restart());
    loopButton.addActionListener(evt -> feat.looping());
    loopButton.addActionListener(evt -> setLoop());
    increaseSpeedButton.addActionListener(evt -> feat.increaseSpeed());
    increaseSpeedButton.addActionListener(evt -> setSpeed());
    decreaseSpeedButton.addActionListener(evt -> feat.decreaseSpeed());
    decreaseSpeedButton.addActionListener(evt -> setSpeed());
    exitButton.addActionListener(evt -> feat.exitProgram());
  }

  @Override
  public void reset() {
    animationPlayingLabel.setText("[ Animation is ON/PLAYING ]");
    clock.reset();
  }

  /**
   * Sets the playing label to its opposite value. If OFF, the label is set to ON and vice versa.
   */
  private void setStart() {
    if (animationPlayingLabel.getText().equals("[ Animation is OFF/PAUSED ]")) {
      animationPlayingLabel.setText("[ Animation is ON/PLAYING ]");
    } else {
      animationPlayingLabel.setText("[ Animation is OFF/PAUSED ]");
    }
  }

  /**
   * Sets the looping label to the opposite value. If OFF, it's turned to ON.
   */
  private void setLoop() {
    if (loopingLabel.getText().equals("[ Looping is OFF ]")) {
      loopingLabel.setText("[ Looping is ON ]");
    } else {
      loopingLabel.setText("[ Looping is OFF ]");
    }
  }

  /**
   * Sets the speed label to the current speed in the animation.
   */
  private void setSpeed() {
    speedLabel.setText(String.format("[ Tick Rate: %d ]", model.getTickRate()));
  }
}
