package concord;

public class LvlTwo implements LvlState {

	int lvl;
	int progress;
	
	public  LvlTwo() {
		lvl = 2;
		progress = 0;
	}
	
	@Override
	public int getLevelStatus() {
		return lvl;
	}

	public void addProgress() {
		progress = progress + 2;
	}
	@Override
	public int getProgress() {
		return progress;
	}

	@Override
	public boolean nextLvl() {
		if (progress > 4) {
			return true;
		}
		return false;
	}

	@Override
	public LvlState lvlUp() {
		LvlThree lvl = new LvlThree();
		return lvl;
	}

}
