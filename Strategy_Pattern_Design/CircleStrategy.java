package com.utad.mais.clase4.modelo2;

public class CircleStrategy implements AreaStrategy 
{

	@Override
	public Double calculaArea(Double parameter) {
		// TODO Auto-generated method stub
		return Math.PI*parameter*parameter;
	}

}
