package com.michal.elearning.test;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Test;

import com.michal.elearning.dao.UserMauseMove;
import com.michal.elearning.machineLearning.MathHelperUtils;

public class MathHelperTest {

	private static Vector<Integer> data;
	private static UserMauseMove start; 
	private static UserMauseMove stop;
	static{		
		data = new Vector<>();
		data.add(18);
		data.add(30);
		data.add(21);
		data.add(42);
		data.add(55);
		data.add(34);
		data.add(45);
		data.add(39);
		data.add(38);
		data.add(25);
		start = new UserMauseMove();
		stop = new UserMauseMove();
		start.setPosX(4);
		start.setPosY(6);
		stop.setPosX(28);
		stop.setPosY(13);
	}
	
	@Test
	public void claculateMean(){		
		double mean = MathHelperUtils.calculateMean(data);
		Assert.assertEquals(34.7, mean,0.0);
	}
	
	@Test
	public void claculateMeanOneInt(){		
		Vector<Integer> data = new Vector<>();
		data.add(18);
		double mean = MathHelperUtils.calculateMean(data);
		Assert.assertEquals(18, mean,0.0);
	}
	
	@Test
	public void claculateMeanEmptyData(){		
		Vector<Integer> data = new Vector<>();
		double mean = MathHelperUtils.calculateMean(data);
		Assert.assertEquals(0.0, mean,0.0);
	}
	
	@Test
	public void claculateMeanNull(){		
		double mean = MathHelperUtils.calculateMean(null);
		Assert.assertEquals(0.0, mean,0.0);
	}
	
	@Test
	public void claculateDev(){
		double dev = MathHelperUtils.calculateStandardDeviation(data);
		Assert.assertEquals(10.88, dev,0.01);
	}
	
	@Test
	public void claculateDistance(){		
		double dist = MathHelperUtils.calculateDistence(start, stop);
		Assert.assertEquals(25, dist,0.01);
	}
	
	@Test
	public void claculateDirectionRight(){		
		int direction = MathHelperUtils.calculateDirection(start, stop);
		Assert.assertEquals(MathHelperUtils.RIGTH, direction);
	}
	
	@Test
	public void claculateDirectionLeft(){	
		UserMauseMove start = new UserMauseMove();
		UserMauseMove stop = new UserMauseMove();
		stop.setPosX(4);
		stop.setPosY(6);
		start.setPosX(28);
		start.setPosY(13);
		int direction = MathHelperUtils.calculateDirection(start, stop);
		Assert.assertEquals(MathHelperUtils.LEFT, direction);
	}
	
	@Test
	public void claculateDirectionUp(){	
		UserMauseMove start = new UserMauseMove();
		UserMauseMove stop = new UserMauseMove();
		stop.setPosX(6);
		stop.setPosY(14);
		start.setPosX(2);
		start.setPosY(4);
		int direction = MathHelperUtils.calculateDirection(start, stop);
		Assert.assertEquals(MathHelperUtils.UP, direction);
	}
	
	@Test
	public void claculateDirectionDown(){	
		UserMauseMove start = new UserMauseMove();
		UserMauseMove stop = new UserMauseMove();
		start.setPosX(6);
		start.setPosY(14);
		stop.setPosX(2);
		stop.setPosY(4);
		int direction = MathHelperUtils.calculateDirection(start, stop);
		Assert.assertEquals(MathHelperUtils.DOWN, direction);
	}
	
}
