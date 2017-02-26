package com.michal.elearning.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.modeldata.vectors.DiGraphFeatures;
import com.michal.elearning.modeldata.vectors.Digraphs;
import com.michal.elearning.modeldata.vectors.NGraph;
import com.michal.elearning.utils.VectorsUtils;

public class DigraphTest {

	public static List<UserKeystrokes> keystrokes = new ArrayList<UserKeystrokes>();
	static{
		//++
		keystrokes.add(new UserKeystrokes(107,472451453,"keydown"));
		keystrokes.add(new UserKeystrokes(107,472451515,"keyup"));
		keystrokes.add(new UserKeystrokes(107,472451593,"keydown"));
		keystrokes.add(new UserKeystrokes(107,472451718,"keyup"));
		//if 3 
		keystrokes.add(new UserKeystrokes(107,480894356,"keydown"));
		keystrokes.add(new UserKeystrokes(73,480894406,"keydown"));
		keystrokes.add(new UserKeystrokes(107,480894456,"keyup"));
		keystrokes.add(new UserKeystrokes(70,480894530,"keydown"));	
		keystrokes.add(new UserKeystrokes(73,480894540,"keyup"));					
		keystrokes.add(new UserKeystrokes(70,480894640,"keyup"));
	}
	
	@Test
	public void reflectionGetFieldList() throws IllegalArgumentException, IllegalAccessException{
		List<int[]> list = VectorsUtils.getDigraphsList(Digraphs.class);
		int[] exeptedArray = {73,70};
		Assert.assertArrayEquals(list.get(0),exeptedArray);
		Assert.assertEquals(list.size(), 6);
	}
	
	@Test
	public void testGetKeydownsList() throws IllegalArgumentException, IllegalAccessException{
		List<UserKeystrokes> keystrokes = new ArrayList<UserKeystrokes>();
		//++
		keystrokes.add(new UserKeystrokes(107,472451453,"keydown"));
		keystrokes.add(new UserKeystrokes(107,472451515,"keyup"));
		keystrokes.add(new UserKeystrokes(107,472451593,"keydown"));
		keystrokes.add(new UserKeystrokes(107,472451718,"keyup"));
		//if
		keystrokes.add(new UserKeystrokes(73,470883406,"keydown"));
		keystrokes.add(new UserKeystrokes(73,470883500,"keyup"));
		keystrokes.add(new UserKeystrokes(70,470883531,"keydown"));
		keystrokes.add(new UserKeystrokes(70,470883640,"keyup"));
		//if 2 
		keystrokes.add(new UserKeystrokes(73,470884406,"keydown"));
		keystrokes.add(new UserKeystrokes(70,470884500,"keydown"));
		keystrokes.add(new UserKeystrokes(73,470884530,"keyup"));		
		keystrokes.add(new UserKeystrokes(70,470884640,"keyup"));
		//if 3 
		keystrokes.add(new UserKeystrokes(107,480894356,"keydown"));
		keystrokes.add(new UserKeystrokes(73,480894406,"keydown"));
		keystrokes.add(new UserKeystrokes(107,480894456,"keyup"));
		keystrokes.add(new UserKeystrokes(70,480894530,"keydown"));	
		keystrokes.add(new UserKeystrokes(73,480894540,"keyup"));					
		keystrokes.add(new UserKeystrokes(70,480894640,"keyup"));
		DiGraphFeatures dd = new DiGraphFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		Assert.assertEquals(4, result.size());
		Assert.assertEquals(4, result.get(0).getKeystrokes().size());
		Assert.assertEquals(4, result.get(1).getKeystrokes().size());
		Assert.assertEquals(4, result.get(2).getKeystrokes().size());
		Assert.assertArrayEquals(Digraphs.PLUS_PLUS_DI, result.get(2).getKeyList());
		Assert.assertEquals(5, result.get(3).getKeystrokes().size());
		Assert.assertArrayEquals(Digraphs.IF_DI, result.get(3).getKeyList());
	}
	
	@Test
	public void testComputeVectors() throws IllegalArgumentException, IllegalAccessException{
		DiGraphFeatures dd = new DiGraphFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		dd.calculateVectors(result);
		Assert.assertEquals(dd.getFirstDwell().size(),2);
		Assert.assertEquals((int)dd.getFirstDwell().get(0),62);
		Assert.assertEquals((int)dd.getFirstDwell().get(1),134);
		Assert.assertEquals(dd.getSecondDwell().size(),2);
		Assert.assertEquals((int)dd.getSecondDwell().get(0),125);
		Assert.assertEquals((int)dd.getSecondDwell().get(1),110);
	}
	
	@Test
	public void testComputeVectorsTimeBetweenFirsAndSecondPress() throws IllegalArgumentException, IllegalAccessException{
		DiGraphFeatures dd = new DiGraphFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		dd.calculateVectors(result);
		Assert.assertEquals(dd.getTimeBetweenFirsAndSecondPress().size(),2);
		Assert.assertEquals((int)dd.getTimeBetweenFirsAndSecondPress().get(0),140);
		Assert.assertEquals((int)dd.getTimeBetweenFirsAndSecondPress().get(1),124);
	}
	
	@Test
	public void testComputeVectorsTimeBetweenFirstUpAndSecondDown() throws IllegalArgumentException, IllegalAccessException{
		DiGraphFeatures dd = new DiGraphFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		dd.calculateVectors(result);
		Assert.assertEquals(dd.getTimeBetweenFirstUpAndSecondDown().size(),2);
		Assert.assertEquals((int)dd.getTimeBetweenFirstUpAndSecondDown().get(0),78);
		Assert.assertEquals((int)dd.getTimeBetweenFirstUpAndSecondDown().get(1),-10);
	}
	
	@Test
	public void testComputeVectorsDuration() throws IllegalArgumentException, IllegalAccessException{
		DiGraphFeatures dd = new DiGraphFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		dd.calculateVectors(result);
		Assert.assertEquals(dd.getDigraphDuration().size(),2);
		Assert.assertEquals((int)dd.getDigraphDuration().get(0),265);
		Assert.assertEquals((int)dd.getDigraphDuration().get(1),234);
	}
	
	@Test
	public void noDigraphTest() throws IllegalArgumentException, IllegalAccessException{
		List<UserKeystrokes> keystrokes = new ArrayList<UserKeystrokes>();
		//==
		keystrokes.add(new UserKeystrokes(107,472451453,"keydown"));
		keystrokes.add(new UserKeystrokes(70,472451515,"keydown"));
		keystrokes.add(new UserKeystrokes(107,472451593,"keyup"));
		keystrokes.add(new UserKeystrokes(70,472451718,"keyup"));
		keystrokes.add(new UserKeystrokes(68,472451818,"keydown"));
		keystrokes.add(new UserKeystrokes(68,472451918,"keyup"));
		DiGraphFeatures dd = new DiGraphFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		Assert.assertEquals(0, result.size());
	}
	
}
