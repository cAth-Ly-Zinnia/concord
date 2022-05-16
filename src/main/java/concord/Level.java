package concord;

public class Level {
	int lvlProgress;
	int lvl;
	LvlState x;
	
	public Level() {
		//give initial state
		x = new LvlOne();
		lvlProgress = x.getProgress();
		lvl = x.getLevelStatus();
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
