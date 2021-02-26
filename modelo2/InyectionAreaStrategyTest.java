package com.utad.mais.clase4.modelo2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InyectionAreaStrategyTest {

	public static void main(String[] args) 
	{
		List<GeometricArea> geometricAreaList = new ArrayList<GeometricArea>();
		InyectionContextAreaStrategy context = new InyectionContextAreaStrategy();
		Random rand = new Random();
		context.setStrategy(new SquareStrategy());
		for(int i = 0; i<10;i++)
		{
			Square square = new Square((double)rand.nextInt(11));
			square.setArea(context.calculaArea(square.getSide()));
			geometricAreaList.add(square);
		}
		context.setStrategy(new CircleStrategy());
		for(int i = 0; i<10;i++)
		{
			Circle circle= new Circle((double)rand.nextInt(11));
			circle.setArea(context.calculaArea(circle.getRadius()));
			geometricAreaList.add(circle);
		}
		context.setGeometricList(geometricAreaList);
		System.out.println("Ascendente:");
		context.sort(new AscAreaComparator());
		context.print();
		System.out.println("Descendente:");
		context.sort(new DescAreaComparator());
		context.print();

	}

}
