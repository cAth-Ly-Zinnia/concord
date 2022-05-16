package concord;

public class LvlThree implements LvlState {

	int lvl;
	int progress;
	
	public LvlThree() {
		lvl = 3;
		progress = 0;
	}
	
	@Override
	public int getLevelStatus() {
		int lvl = 3;
		return lvl;
	}

	public void addProgress() {
		progress = progress + 1;
	}
	@Override
	public int getProgress() {
		return progress;
	}

	@Override
	public boolean nextLvl() {
		return false;
	}

	@Override
	public LvlState lvlUp() {
		return null;
	}

}
