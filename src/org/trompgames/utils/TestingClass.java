package org.trompgames.utils;

public class TestingClass {

	public static void main(String[] args) {
		
		Vector2 v1 = new Vector2(0, 0);
		Vector2 v2 = new Vector2(9, 0);
		
		Vector2 v = Vector2.lerp(v1, v2, 0.5);
		//System.out.println(v2.normalize());
		
		
		GameHandler handler = new GameHandler(60, 20, true){
			
			
							
			@Override
			public void update(int deltaTime){
				//System.out.println("update: " + deltaTime + "ms");
			
			}			
			
			@Override
			public void fixedUpdate(int deltaTime){
				//System.out.println("fixedUpdate: " + deltaTime + "ms");
			
			}
			
			
			
			
		};
				
		System.out.println("FrameMS: " + handler.getFrameMS());
		System.out.println("FixedMS: " + handler.getFixedMS());

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
