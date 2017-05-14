package com.michal.elearning.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.modeldata.vectors.TriGraphFeatures;
import com.michal.elearning.wekaDataModel.NGraph;
import com.michal.elearning.wekaDataModel.TriGraphsCodes;

public class TriGraphTest {

	/*@Test
	public void testGetKeydownsList() throws IllegalArgumentException, IllegalAccessException{
		List<UserKeystrokes> keystrokes = new ArrayList<UserKeystrokes>();
		//public static int[] FOR_TRI = {70,79,82};
		
		keystrokes.add(new UserKeystrokes(70,472451453,"keydown"));
		keystrokes.add(new UserKeystrokes(70,472451515,"keyup"));
		keystrokes.add(new UserKeystrokes(79,472451553,"keydown"));
		keystrokes.add(new UserKeystrokes(79,472451615,"keyup"));
		keystrokes.add(new UserKeystrokes(82,472451653,"keydown"));
		keystrokes.add(new UserKeystrokes(82,472451715,"keyup"));
		keystrokes.add(new UserKeystrokes(82,472451753,"keydown"));
		keystrokes.add(new UserKeystrokes(82,472451815,"keyup"));
		keystrokes.add(new UserKeystrokes(84,472451853,"keydown"));
		keystrokes.add(new UserKeystrokes(84,472451915,"keyup"));
		
		//public static int[] VAR_DI = {86,65,82};
		
		keystrokes.add(new UserKeystrokes(86,492451453,"keydown"));
		keystrokes.add(new UserKeystrokes(86,492451473,"keyup"));
		keystrokes.add(new UserKeystrokes(65,492451515,"keydown"));
		keystrokes.add(new UserKeystrokes(65,492451515,"keyup"));		
		keystrokes.add(new UserKeystrokes(82,492451615,"keydown"));
		keystrokes.add(new UserKeystrokes(82,492451653,"keyup"));
		keystrokes.add(new UserKeystrokes(78,492451715,"keyup"));
		keystrokes.add(new UserKeystrokes(83,492451753,"keydown"));
		keystrokes.add(new UserKeystrokes(83,492451815,"keyup"));
		keystrokes.add(new UserKeystrokes(79,492451853,"keydown"));
		keystrokes.add(new UserKeystrokes(79,492451915,"keyup"));
		keystrokes.add(new UserKeystrokes(76,492451953,"keydown"));
		keystrokes.add(new UserKeystrokes(76,492452015,"keyup"));
		keystrokes.add(new UserKeystrokes(69,492452053,"keydown"));
		keystrokes.add(new UserKeystrokes(69,492452115,"keyup"));

		TriGraphFeatures dd = new TriGraphFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(6, result.get(0).getKeystrokes().size());
		Assert.assertArrayEquals(TriGraphsCodes.FOR_TRI, result.get(0).getKeyList());
		Assert.assertEquals(6, result.get(1).getKeystrokes().size());
		Assert.assertArrayEquals(TriGraphsCodes.VAR_DI, result.get(1).getKeyList());
	}
	
	@Test
	public void testCalculateVectors() throws IllegalArgumentException, IllegalAccessException{
		List<UserKeystrokes> keystrokes = new ArrayList<UserKeystrokes>();
		//public static int[] FOR_TRI = {70,79,82};
		
		keystrokes.add(new UserKeystrokes(84,462451853,"keydown"));
		keystrokes.add(new UserKeystrokes(84,462451915,"keyup"));
		keystrokes.add(new UserKeystrokes(70,472451453,"keydown"));
		keystrokes.add(new UserKeystrokes(70,472451515,"keyup"));
		keystrokes.add(new UserKeystrokes(79,472451553,"keydown"));
		keystrokes.add(new UserKeystrokes(79,472451615,"keyup"));
		keystrokes.add(new UserKeystrokes(82,472451653,"keydown"));
		keystrokes.add(new UserKeystrokes(82,472451715,"keyup"));
		keystrokes.add(new UserKeystrokes(82,472451753,"keydown"));
		keystrokes.add(new UserKeystrokes(82,472451815,"keyup"));
		keystrokes.add(new UserKeystrokes(84,472451853,"keydown"));
		keystrokes.add(new UserKeystrokes(84,472451915,"keyup"));
		
		TriGraphFeatures dd = new TriGraphFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(6, result.get(0).getKeystrokes().size());
		Assert.assertArrayEquals(TriGraphsCodes.FOR_TRI, result.get(0).getKeyList());
		dd.calculateVectors(result);
		Assert.assertEquals((Integer)62,dd.getDwellFirst().get(0)); //62
		Assert.assertEquals((Integer)62,dd.getDwellSecond().get(0)); //62
		Assert.assertEquals((Integer)262,dd.getWordDuration().get(0)); //262
	}*/
	
}
