package com.utad.mais.clase4.modelo2;

public class Circle implements GeometricArea 
{
	private Double area;
	private Double radius;
	
	public Circle(Double radius)
	{
		this.radius = radius;
	}
	public Circle()
	{
		
	}
	public Double getRadius()
	{
		return radius;
	}
	public void setRadius(Double radius)
	{
		this.radius = radius;
	}
	public void setArea(Double area)
	{
		this.area = area;
	}
	@Override
	public Double getArea() {
		// TODO Auto-generated method stub
		return area;
	}
	public String toString()
	{
		return "Circle[radius( "+radius+"),area( "+area+")]";
	}
	
	public static void main(String args[])
	{
		Circle circleExample = new Circle(1.0);
		ContextAreaStrategy context = new ContextAreaStrategy(new CircleStrategy());
		circleExample.setArea(context.calculaArea(circleExample.getRadius()));
		System.out.println(circleExample);
	}
	
}
