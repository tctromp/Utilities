package org.trompgames.utils;

public class Vector3 {
	private double x;
	private double y;
	private double z;
	
	public Vector3(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public Vector3 add(Vector3 v){
		return new Vector3(x + v.x, y + v.y, z + v.z);
	}
	
	public Vector3 add(double x, double y, double z){
		return add(new Vector3(x, y, z));
	}
	
	public Vector3 sub(Vector3 v){
		return new Vector3(x - v.x, y - v.y, z - v.z);
	}
	
	public Vector3 sub(double x, double y, double z){
		return sub(new Vector3(x, y, z));
	}
	
	public Vector3 mult(Vector3 v){
		return new Vector3(x*v.x, y*v.y, z*v.z);
	}
	
	public Vector3 mult(double x, double y, double z){
		return mult(new Vector3(x, y, z));
	}
	
	public Vector3 mult(double i){
		return new Vector3(x*i, y*i, z*i);
	}
	
	public Vector3 div(Vector3 v){
		return new Vector3(x/v.x, y/v.y, z/v.z);
	}
	
	public Vector3 div(double x, double y, double z){
		return div(new Vector3(x, y, z));
	}
	
	public Vector3 div(double i){
		return new Vector3(x/i, y/i, z/i);
	}
	
	public Vector3 normalize(){
		double c = Math.sqrt(x*x + y*y + z*z);
		return div(c);
	}
	
	public static double distance(Vector3 v1, Vector3 v2) {
		return Math.sqrt(Math.pow((v2.x - v1.x), 2) + Math.pow((v2.y - v1.y), 2) + Math.pow((v2.z - v1.z), 2));
	}
	
	public static Vector3 lerp(Vector3 v1, Vector3 v2, double t){
		return v1.mult(1 - t).add(v2.mult(t));
	}	
	
	public static double ease(double t, double a) {
		
		return (Math.pow(t, a)/(Math.pow(t,  a) + Math.pow(1 - t, a)));
		
	}
	
	@Override
	public String toString(){
		return "X: " + x + " Y: " + y + " Z: " + z; 
	}
	
}
