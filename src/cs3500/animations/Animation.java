package cs3500.animations;

/**
 * Creates an animation that visualizes the behavior of an algorithm. The animation should be
 * compatible with the reader in this project.
 */
public interface Animation {

  /**
   * Exports the generated animation into the given location. The given string should be the name of
   * the file where the animation will be stored.
   */
  void export(String location);

  /**
   * Creates the animation. The animation will depend on the class type.
   */
  void create();
}
