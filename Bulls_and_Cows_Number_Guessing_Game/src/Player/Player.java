package Player;


public interface Player {
		
	public int move();
	
	public void getHistory();
	
	public void clearHistory();
		
	public void addValue(String value);

	public void addHistory(int num);
	
	public void setNum(int num);
	
	public void win(Player player);
}
