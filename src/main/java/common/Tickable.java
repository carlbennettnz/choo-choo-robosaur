package common;

public interface Tickable {
	/**
	 * @param delta Time elapsed since last tick, in seconds.
	 */
	void tick(double delta, GameController game);
}
