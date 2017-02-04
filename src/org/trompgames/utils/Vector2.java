package org.trompgames.utils;

public class Vector2 {

	private double x;
	private double y;
	
	public Vector2(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public Vector2 add(Vector2 v){
		return new Vector2(x + v.x, y + v.y);
	}
	
	public Vector2 add(double x, double y){
		return add(new Vector2(x, y));
	}
	
	public Vector2 sub(Vector2 v){
		return new Vector2(x - v.x, y - v.y);
	}
	
	public Vector2 sub(double x, double y){
		return sub(new Vector2(x, y));
	}
	
	public Vector2 mult(Vector2 v){
		return new Vector2(x*v.x, y*v.y);
	}
	
	public Vector2 mult(double x, double y){
		return mult(new Vector2(x, y));
	}
	
	public Vector2 mult(double i){
		return new Vector2(x*i, y*i);
	}
	
	public Vector2 div(Vector2 v){
		return new Vector2(x/v.x, y/v.y);
	}
	
	public Vector2 div(double x, double y){
		return div(new Vector2(x, y));
	}
	
	public Vector2 div(double i){
		return new Vector2(x/i, y/i);
	}
	
	public Vector2 normalize(){
		double c = Math.sqrt(x*x + y*y);
		return div(c);
	}
	
	public static Vector2 lerp(Vector2 v1, Vector2 v2, double t){
		return v1.mult(1 - t).add(v2.mult(t));
	}	
	
	@Override
	public String toString(){
		return "X: " + x + " Y: " + y; 
	}
	
}
