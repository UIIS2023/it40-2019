package file;

public class SaveStrategyContext {
	
	private SaveStrategy strategy;
	
	public void setStrategy(SaveStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void executeStrategy() {
		strategy.save();
	}

}
