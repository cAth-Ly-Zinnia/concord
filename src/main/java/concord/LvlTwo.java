package concord;

import java.io.Serializable;

public class LvlTwo implements LvlState, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3351011157063001508L;
	int lvl;
	int progress;
	
	public  LvlTwo() {
		lvl = 2;
		progress = 0;
	}
	
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
