import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {
	
	@Test
	public void putAndGetTest() {
		BST<String,String> testTree = new BST<>();

		assertEquals(true,testTree.isEmpty());

		testTree.put("c", "three");
		testTree.put("b", "two");
		testTree.put("d", "four");
		testTree.put("a", "one");
		testTree.put("h", "eight");


		assertEquals(5, testTree.size());
		assertEquals("three",testTree.get("c"));
		assertEquals("two",testTree.get("b"));
		assertEquals("four",testTree.get("d"));
		assertEquals("one",testTree.get("a"));
		assertEquals("eight",testTree.get("h"));

	}
	@Test
	public void setTest() {
		BST<String,String> testTree = new BST<>();

		testTree.set("c", "three");
		testTree.set("b", "two");
		testTree.set("d", "four");
		testTree.set("d", "one");


		assertEquals(3, testTree.size());
		assertEquals("three",testTree.get("c"));
		assertEquals("two",testTree.get("b"));
		assertEquals("one",testTree.get("d"));

	}
	@Test
	public void replaceTest() {
		BST<String,String> testTree = new BST<>();

		testTree.set("c", "three");
		testTree.set("b", "two");
		testTree.set("d", "four");
		testTree.replace("d", "one");		
		boolean test = testTree.replace("e", "one");


		assertEquals(3, testTree.size());
		assertEquals("three",testTree.get("c"));
		assertEquals("two",testTree.get("b"));
		assertEquals("one",testTree.get("d"));
		assertEquals(false, test);
	} 
	@Test
	public void getKeys() {
		BST<Integer, String> testTree = new BST<>();

		testTree.put(3,"Chris");
		testTree.put(4,"Alex");
		testTree.put(1,"Trevor");
		testTree.put(9,"Bill");
		testTree.put(2,"Chris");

		List<Integer> expectedKeys = new ArrayList<Integer>();
		expectedKeys.add(1);
		expectedKeys.add(2);
		expectedKeys.add(3);
		expectedKeys.add(4);
		expectedKeys.add(9);

		List<Integer> keys = testTree.keys();
		assertEquals(expectedKeys.size(),keys.size());

		for ( int i = 0; i < expectedKeys.size(); i++ ) {
			assertEquals(expectedKeys.get(i),keys.get(i));
		}

	}

	@Test 
	public void removeTest() {		
		
		BST<Integer, String> testTree = new BST<>();

		testTree.put(3,"Chris");
		testTree.put(6,"Alex");
		testTree.put(1,"Trevor");
		testTree.put(9,"Bill");
		testTree.put(2,"Chris");
		testTree.put(5,"Orange");


		assertEquals(6, testTree.size());

		assertEquals(true,testTree.remove(6));

		List<Integer> expectedKeys = new ArrayList<Integer>();
		expectedKeys.add(1);
		expectedKeys.add(2);
		expectedKeys.add(3);
		expectedKeys.add(5);
		expectedKeys.add(9);
		List<Integer> keys = testTree.keys();
		assertEquals(5, testTree.size());
		
		for ( int i = 0; i < expectedKeys.size(); i++ ) {
			assertEquals(expectedKeys.get(i),keys.get(i));
		}

	}
}
