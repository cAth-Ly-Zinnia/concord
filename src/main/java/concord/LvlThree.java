package concord;

import java.io.Serializable;

public class LvlThree implements LvlState, Serializable {
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param progress the progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -2777295707526327757L;
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
