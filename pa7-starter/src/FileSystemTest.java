import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class FileSystemTest {

    @Test 
    public void addTest() {
        FileSystem testSystem = new FileSystem();

        testSystem.add("mySample1.txt", "/home","2023/11/04");
        testSystem.add("mySample2.txt", "/home","2023/11/04");
        testSystem.add("mySample3.txt", "/home","2023/11/04");


        ArrayList<String> names = testSystem.findFileNamesByDate("2023/11/04");


        List<String> nameToString = testSystem.outputNameTree();
        for ( int i = 0; i < nameToString.size(); i++ ) {
            System.out.println(nameToString.get(i));
        }

        ArrayList<String> expected = new ArrayList<>();
        expected.add("mySample1.txt");
        expected.add("mySample2.txt");
        expected.add("mySample3.txt");

        for ( int i = 0; i < expected.size(); i++ ) {
            assertEquals(expected.get(i), names.get(i));
        }

    }
    @Test 
    public void addDuplicatesTest() {
        FileSystem testSystem = new FileSystem();

        testSystem.add("mySample1.txt", "/home","2023/11/04");
        testSystem.add("mySample1.txt", "/home","2023/11/04");
        testSystem.add("mySample1.txt", "/home","2023/11/05");

        List<String> dateKeys = testSystem.dateTree.keys();
        for ( int i = 0; i < dateKeys.size(); i++ ) {
            System.out.println(dateKeys.get(i));
        }

        ArrayList<String> files = testSystem.findFileNamesByDate("2023/11/04");
        assertEquals(0,files.size());
        assertEquals(1,testSystem.nameTree.size());
        assertEquals(1,testSystem.dateTree.size());
    }
    @Test 
    public void filterWildCardTest() {
        FileSystem testSystem = new FileSystem();

        testSystem.add("mySample.txt", "/home", "2021/01/01");
        testSystem.add("mySample1.txt", "/school", "2021/02/01");
        testSystem.add("mySample2.txt", "/root", "2019/02/20");
        testSystem.add("homework1.pdf", "/home", "2021/03/02");
        testSystem.add("HelloWorld.py", "/desktop", "2021/01/01");
        testSystem.add("homework2.pdf", "/home", "2020/04/20");

        FileSystem filtered = testSystem.filter("mySam");

        List<String> names = filtered.nameTree.keys();

        List<String> expectedNames = new ArrayList<>();

        expectedNames.add("mySample.txt");
        expectedNames.add("mySample1.txt");
        expectedNames.add("mySample2.txt");

        List<String> dates = filtered.dateTree.keys();

        List<String> expectedDates = new ArrayList<>();
        expectedDates.add("2019/02/20");        
        expectedDates.add("2021/01/01");
        expectedDates.add("2021/02/01");

        for ( int i = 0; i < expectedNames.size(); i++ ) {
            assertEquals(expectedNames.get(i), names.get(i));
        }

        for ( int i = 0; i < expectedDates.size(); i++ ) {
            assertEquals(expectedDates.get(i), dates.get(i));
        }


    }
    @Test
    public void filterDatesTest() {
        FileSystem testSystem = new FileSystem();

        testSystem.add("mySample.txt", "/desktop","2021/02/01");
        testSystem.add("mySample1.txt", "/root","2021/02/01");
        testSystem.add("mySample2.txt", "/user","2021/02/06");
        testSystem.add("homework1.pdf", "/home","2021/01/14");
        testSystem.add("homework2.pdf", "/home","2021/01/14");
        testSystem.add("resume.pdf", "/Documents","2021/01/27");
        testSystem.add("HelloWorld.py", "/.","1990/12/30");
        testSystem.add("3test.txt", "/home","1997/11/07");

        FileSystem dateFileSystem = testSystem.filter("2021/01/20", "2021/02/02");

        List<String> dates = dateFileSystem.dateTree.keys();
        List<String> expectedDates = new ArrayList<>();
        expectedDates.add("2021/01/27");
        expectedDates.add("2021/02/01");

        List<String> names = dateFileSystem.nameTree.keys();
        List<String> expectedNames = new ArrayList<>();
        expectedNames.add("mySample.txt");
        expectedNames.add("mySample1.txt");
        expectedNames.add("resume.pdf");

        for ( int i = 0; i < expectedNames.size(); i++ ) {
            assertEquals(expectedNames.get(i), names.get(i));
        }

        for ( int i = 0; i < expectedDates.size(); i++ ) {
            assertEquals(expectedDates.get(i), dates.get(i));
        }

        System.out.println(dateFileSystem.dateTree.size());
        System.out.println(dateFileSystem.nameTree.size());


    }

}        
