import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.*;

public class BSTTest {
	
	/* TODO: Add your own tests */
	@Test
	public void dummyTest() {
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
}
