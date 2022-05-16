package concord;

public class LvlOne implements LvlState {

	int lvl;
	int progress;
	public LvlOne() {
		lvl = 1;
		progress = 0;
	}
	
	@Override
	public int getLevelStatus() {
		return lvl;
	}

	public void addProgress() {
		progress = progress + 3;
	}
	@Override
	public int getProgress() {
		return progress;
	}

	@Override
	public boolean nextLvl() {
		if (progress > 3) {
			return true;
		}
		return false;
	}

	@Override
	public LvlState lvlUp() {
		LvlTwo lvlTwo = new LvlTwo();
		return lvlTwo;
	}
}
