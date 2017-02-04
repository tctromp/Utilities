package org.trompgames.utils;

public abstract class GameHandler {
	
	private final int FPS;
	private final int TPS;
	private boolean lockFPS;
	
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
		gameThread.start();
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
	
	public int getTPS(){
		return TPS;
	}
	
	public boolean isFPSLocked(){
		return lockFPS;
	}
	
	public abstract void update(int deltaTime);

	
	public abstract void fixedUpdate(int deltaTime);
	
	
		
	
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
			
			while(true){
				if(fixedUpdateEnabled){					
					if((lastFixedTime + gameHandler.getFixedMS() <= System.currentTimeMillis())){					
						gameHandler.fixedUpdate((int) (System.currentTimeMillis() - lastFixedTime));
						lastFixedTime = System.currentTimeMillis();
					}
				}if(frameUpdateEnabled){
					if((lastFrameTime + gameHandler.getFrameMS() <= System.currentTimeMillis())){				
						gameHandler.update((int) (System.currentTimeMillis() - lastFrameTime));
						lastFrameTime  = System.currentTimeMillis();
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
