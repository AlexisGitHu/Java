package com.utad.mais.clase4.modelo2;

public class SquareStrategy implements AreaStrategy {

	@Override
	public Double calculaArea(Double parameter) 
	{
		return parameter*parameter;
		
	}

}
