package concord;

import java.io.Serializable;

public class LvlOne implements LvlState, Serializable{

	/**
	 * @return the lvl
	 */
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
	private static final long serialVersionUID = -3668070780342159578L;
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
