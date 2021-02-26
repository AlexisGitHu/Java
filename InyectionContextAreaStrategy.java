package com.utad.mais.clase4.modelo2;

import java.util.Comparator;
import java.util.List;

public class InyectionContextAreaStrategy implements AreaStrategy
{
	private AreaStrategy contextStrategy;
	private List<GeometricArea> geometricAreaList;
	
	public InyectionContextAreaStrategy(ContextAreaStrategy contextStrategy, List<GeometricArea> geometricAreaList)
	{
		this.contextStrategy = contextStrategy;
		this.geometricAreaList = geometricAreaList;
	}
	public InyectionContextAreaStrategy()
	{
		
	}
	public void sort(Comparator<GeometricArea> comparator)
	{
		this.geometricAreaList.sort(comparator);
		
	}
	public List<GeometricArea> getGeometricList()
	{
		return geometricAreaList;
	}
	public void setGeometricList(List<GeometricArea> geometricList)
	{
		this.geometricAreaList = geometricList;
	}
	public AreaStrategy getStrategy()
	{
		return this.contextStrategy;
	}
	public void setStrategy(AreaStrategy contextStrategy)
	{
		this.contextStrategy = contextStrategy;
	}
	@Override
	public Double calculaArea(Double parameter) {
		// TODO Auto-generated method stub
		return this.contextStrategy.calculaArea(parameter);
	}
	public void print()
	{
		for(GeometricArea geometry: geometricAreaList)
		{
			System.out.println(geometry);
		}
	}

}
