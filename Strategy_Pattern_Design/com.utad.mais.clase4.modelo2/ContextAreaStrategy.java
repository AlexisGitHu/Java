package com.utad.mais.clase4.modelo2;

public class ContextAreaStrategy implements AreaStrategy
{
	AreaStrategy strategy;
	
	public ContextAreaStrategy(AreaStrategy strategy)
	{
		this.strategy = strategy;
	}
	public ContextAreaStrategy()
	{
		
	}
	public AreaStrategy getStrategy()
	{
		return strategy;
	}
	public void setStrategy(AreaStrategy strategy)
	{
		this.strategy = strategy;
	}
	@Override
	public Double calculaArea(Double parameter) {
		return this.strategy.calculaArea(parameter);
	}
	
	
}
