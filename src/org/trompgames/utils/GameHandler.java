package org.trompgames.utils;

public abstract class GameHandler {
	
	private final int FPS;
	private final int TPS;
	private boolean lockFPS;
	
	private double currentFPS;
	private double currentTPS;
	
	private GameThread gameThread;
	
	/*
	 * FPS: Frames Per Second
	 * TPS: Fixed update time
	 * lockFPS: Lock FPS
	 */
	public GameHandler(int FPS, int TPS, boolean lockFPS){
		this.FPS = FPS; 
		this.TPS = TPS;
		this.lockFPS = lockFPS;
		this.gameThread = new GameThread(this);
	}
	
	public int getFPS(){
		return FPS;
	}
	
	public int getFixedMS(){
		int fixedMS = (int) Math.round(1000.0/TPS);
		if(fixedMS == 0) fixedMS = 1;
		return fixedMS;
	}
	
	public int getFrameMS(){
		int msBetweenFrames = (int) Math.round(1000.0/FPS);
		if(msBetweenFrames == 0) msBetweenFrames = 1;
		return msBetweenFrames;
	}
	
	public int getFrameNano() {
		int nanoBetweenFrames = (int) Math.round(1000000000.0/FPS);
		if(nanoBetweenFrames == 0) nanoBetweenFrames = 1;
		return nanoBetweenFrames;
	}
	
	public int getTPS(){
		return TPS;
	}
	
	public boolean isFPSLocked(){
		return lockFPS;
	}
	
	protected void startGameThread() {
		gameThread.start();

	}
	
	public abstract boolean update(double deltaTime);

	
	public abstract void fixedUpdate(double deltaTime);
	
	
		
	
	public static class GameThread extends Thread{
		
		private GameHandler gameHandler;
		private boolean fixedUpdateEnabled = true;
		private boolean frameUpdateEnabled = true;
		
		
		public GameThread(GameHandler gameHandler){
			this.gameHandler = gameHandler;
		}
		
		@Override
		public void run(){
			
			long lastFixedTime = System.currentTimeMillis();
			long lastFrameTime = System.currentTimeMillis();
			
			long lastFixedNanoTime = System.nanoTime();
			long lastFrameNanoTime = System.nanoTime();
			
			
			while(true){
				if(fixedUpdateEnabled){					
					if((lastFixedTime + gameHandler.getFixedMS() <= System.currentTimeMillis())){					
						gameHandler.fixedUpdate(1.0 * (System.nanoTime() - lastFixedNanoTime)/1000000000);
						lastFixedNanoTime = System.nanoTime();
						lastFixedTime = System.currentTimeMillis();
					}
				}if(frameUpdateEnabled){
					if(!gameHandler.lockFPS || (lastFrameNanoTime + gameHandler.getFrameNano() < System.nanoTime())){				
						//boolean updateSuccess = gameHandler.update(1.0 * (System.nanoTime() - lastFrameNanoTime)/1000000000);
						boolean updateSuccess = gameHandler.update(1.0 * (System.currentTimeMillis() - lastFrameTime)/1000);

						if(updateSuccess) {
							lastFrameNanoTime = System.nanoTime();
							lastFrameTime = System.currentTimeMillis();
						}
					}
					
				}
				
			}
			
			
			
		}		
		
		public GameHandler getGameHandler(){
			return gameHandler;
		}	
		
		public boolean fixedUpdateEnabled(){
			return fixedUpdateEnabled;
		}
		
		public void setFixedUpdateEnabled(boolean fixedUpdateEnabled){
			this.fixedUpdateEnabled = fixedUpdateEnabled;
		}

		public boolean frameUpdateEnabled(){
			return this.frameUpdateEnabled;
		}
		
		public void setFrameUpdateEnabled(boolean frameUpdateEnabled){
			this.frameUpdateEnabled = frameUpdateEnabled;
		}
	}
	

	
}
