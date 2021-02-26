package com.utad.mais.clase4.modelo2;

import java.util.Comparator;

public class DescAreaComparator implements Comparator<GeometricArea> 
{

	@Override
	public int compare(GeometricArea o1, GeometricArea o2) {
		// TODO Auto-generated method stub
		return o1.getArea().compareTo(o2.getArea());
	}

}
