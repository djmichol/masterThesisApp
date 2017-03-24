package com.michal.elearning.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.michal.elearning.dao.UserKeystrokes;
import com.michal.elearning.modeldata.vectors.NGraphsFeatures;
import com.michal.elearning.modeldata.vectors.model.JavaScriptKeyWordsCodes;
import com.michal.elearning.modeldata.vectors.model.NGraph;

public class OtherKeywordsTest {
	
	@Test
	public void testGetKeydownsList() throws IllegalArgumentException, IllegalAccessException{
		List<UserKeystrokes> keystrokes = new ArrayList<UserKeystrokes>();
		//alert {65,76,69,82,84};
		keystrokes.add(new UserKeystrokes(65,472451453,"keydown"));
		keystrokes.add(new UserKeystrokes(65,472451515,"keyup"));
		keystrokes.add(new UserKeystrokes(76,472451553,"keydown"));
		keystrokes.add(new UserKeystrokes(76,472451615,"keyup"));
		keystrokes.add(new UserKeystrokes(69,472451653,"keydown"));
		keystrokes.add(new UserKeystrokes(69,472451715,"keyup"));
		keystrokes.add(new UserKeystrokes(82,472451753,"keydown"));
		keystrokes.add(new UserKeystrokes(82,472451815,"keyup"));
		keystrokes.add(new UserKeystrokes(84,472451853,"keydown"));
		keystrokes.add(new UserKeystrokes(84,472451915,"keyup"));
		
		//public static int[] CONSOLE_PLUS_WORD = {67,79,78,83,79,76,69};
		
		keystrokes.add(new UserKeystrokes(67,492451453,"keydown"));
		keystrokes.add(new UserKeystrokes(84,492451473,"keyup"));
		keystrokes.add(new UserKeystrokes(79,492451515,"keydown"));
		keystrokes.add(new UserKeystrokes(67,492451515,"keyup"));		
		keystrokes.add(new UserKeystrokes(79,492451615,"keyup"));
		keystrokes.add(new UserKeystrokes(78,492451653,"keydown"));
		keystrokes.add(new UserKeystrokes(78,492451715,"keyup"));
		keystrokes.add(new UserKeystrokes(83,492451753,"keydown"));
		keystrokes.add(new UserKeystrokes(83,492451815,"keyup"));
		keystrokes.add(new UserKeystrokes(79,492451853,"keydown"));
		keystrokes.add(new UserKeystrokes(79,492451915,"keyup"));
		keystrokes.add(new UserKeystrokes(76,492451953,"keydown"));
		keystrokes.add(new UserKeystrokes(76,492452015,"keyup"));
		keystrokes.add(new UserKeystrokes(69,492452053,"keydown"));
		keystrokes.add(new UserKeystrokes(69,492452115,"keyup"));

		NGraphsFeatures dd = new NGraphsFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(10, result.get(0).getKeystrokes().size());
		Assert.assertArrayEquals(JavaScriptKeyWordsCodes.ALERT_PLUS_WORD, result.get(0).getKeyList());
		Assert.assertEquals(15, result.get(1).getKeystrokes().size());
		Assert.assertArrayEquals(JavaScriptKeyWordsCodes.CONSOLE_PLUS_WORD, result.get(1).getKeyList());
	}
	
	@Test
	public void testAbc() throws IllegalArgumentException, IllegalAccessException{
		List<UserKeystrokes> keystrokes = new ArrayList<UserKeystrokes>();
		//public static int[] CONSOLE_PLUS_WORD = {67,79,78,83,79,76,69};
		
		keystrokes.add(new UserKeystrokes(67,492451453,"keydown"));
		keystrokes.add(new UserKeystrokes(84,492451473,"keyup"));
		keystrokes.add(new UserKeystrokes(79,492451515,"keydown"));
		keystrokes.add(new UserKeystrokes(67,492451515,"keyup"));		
		keystrokes.add(new UserKeystrokes(79,492451615,"keyup"));
		keystrokes.add(new UserKeystrokes(78,492451653,"keydown"));
		keystrokes.add(new UserKeystrokes(78,492451715,"keyup"));
		keystrokes.add(new UserKeystrokes(83,492451753,"keydown"));
		keystrokes.add(new UserKeystrokes(83,492451815,"keyup"));
		keystrokes.add(new UserKeystrokes(79,492451853,"keydown"));
		keystrokes.add(new UserKeystrokes(79,492451915,"keyup"));
		keystrokes.add(new UserKeystrokes(76,492451953,"keydown"));
		keystrokes.add(new UserKeystrokes(76,492452015,"keyup"));
		keystrokes.add(new UserKeystrokes(69,492452053,"keydown"));
		keystrokes.add(new UserKeystrokes(69,492452115,"keyup"));

		NGraphsFeatures dd = new NGraphsFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		Assert.assertEquals(1, result.size());
		Assert.assertEquals(15, result.get(0).getKeystrokes().size());
		Assert.assertArrayEquals(JavaScriptKeyWordsCodes.CONSOLE_PLUS_WORD, result.get(0).getKeyList());
		dd.calculateVectors(result);
		dd.getKeyDwell();
		dd.getPressToPress();
		Assert.assertEquals((int)dd.getWordDuration().get(0),662);
	}
	
	@Test
	public void testNull() throws IllegalArgumentException, IllegalAccessException{
		List<UserKeystrokes> keystrokes = new ArrayList<UserKeystrokes>();
		//public static int[] CONSOLE_PLUS_WORD = {67,79,78,83,79,76,69};		
		keystrokes.add(new UserKeystrokes(67,492451453,"keydown"));
		keystrokes.add(new UserKeystrokes(84,492451473,"keyup"));
		keystrokes.add(new UserKeystrokes(79,492451515,"keydown"));
		keystrokes.add(new UserKeystrokes(67,492451515,"keyup"));		
		keystrokes.add(new UserKeystrokes(79,492451615,"keyup"));
		keystrokes.add(new UserKeystrokes(78,492451653,"keydown"));
		keystrokes.add(new UserKeystrokes(78,492451715,"keyup"));
		keystrokes.add(new UserKeystrokes(65,492451725,"keydown"));
		keystrokes.add(new UserKeystrokes(65,492451735,"keyup"));
		keystrokes.add(new UserKeystrokes(76,492451745,"keydown"));
		keystrokes.add(new UserKeystrokes(76,492451750,"keyup"));
		keystrokes.add(new UserKeystrokes(83,492451753,"keydown"));
		keystrokes.add(new UserKeystrokes(83,492451815,"keyup"));
		keystrokes.add(new UserKeystrokes(79,492451853,"keydown"));
		keystrokes.add(new UserKeystrokes(79,492451915,"keyup"));
		keystrokes.add(new UserKeystrokes(76,492451953,"keydown"));
		keystrokes.add(new UserKeystrokes(76,492452015,"keyup"));
		keystrokes.add(new UserKeystrokes(69,492452053,"keydown"));
		keystrokes.add(new UserKeystrokes(69,492452115,"keyup"));

		NGraphsFeatures dd = new NGraphsFeatures();
		List<NGraph> result = dd.prepareVector(keystrokes);
		Assert.assertEquals(0, result.size());
	}

}
