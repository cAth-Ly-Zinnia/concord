package concord;

import java.io.Serializable;

public class Level implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4073685067447413897L;
	int lvlProgress;
	int lvl;
	LvlState x;
	
	public Level() {
		//give initial state
		x = new LvlOne();
		lvlProgress = x.getProgress();
		lvl = x.getLevelStatus();
	}

	/**
	 * @return the x
	 */
	public LvlState getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(LvlState x) {
		this.x = x;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param lvlProgress the lvlProgress to set
	 */
	public void setLvlProgress(int lvlProgress) {
		this.lvlProgress = lvlProgress;
	}

	/**
	 * @param lvl the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public void gainProgress() {
		System.out.println("printing statement");
		x.addProgress();
		if(x.nextLvl()) {
			System.out.println("level up");
			x = x.lvlUp();
		}
		
		lvl = x.getLevelStatus();
		lvlProgress = x.getProgress();
	}
	
	public int getLvlProgress(){
		return x.getProgress();
	}
	
	public int getLvl() {
		return x.getLevelStatus();
	}
}
