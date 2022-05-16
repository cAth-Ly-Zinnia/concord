package concord;

public interface LvlState {
	public int getLevelStatus();
	public int getProgress();
	public boolean nextLvl();
	public void addProgress();
	public LvlState lvlUp();
}
