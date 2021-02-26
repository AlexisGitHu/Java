package com.utad.mais.clase4.modelo2;

import java.util.Comparator;

public class AscAreaComparator implements Comparator<GeometricArea> {

	@Override
	public int compare(GeometricArea o1, GeometricArea o2) {
		// TODO Auto-generated method stub
		return o2.getArea().compareTo(o1.getArea());
	}

}
