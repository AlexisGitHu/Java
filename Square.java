package com.utad.mais.clase4.modelo2;

public class Square implements GeometricArea 
{
	private Double area;
	private Double side;

	public Square(Double side)
	{
		this.side = side;
	}
	public Square()
	{
		
	}
	public Double getSide()
	{
		return side;
	}
	public void setSide(Double side)
	{
		this.side = side;
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
		return "Square[side( "+side+"),area( "+area+")]";
	}

}
