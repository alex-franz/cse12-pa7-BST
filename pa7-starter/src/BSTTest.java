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
}
