import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.foreign.Arena;
import java.util.Scanner;


public class FileSystem {

    BST<String, FileData> nameTree;
    BST<String, ArrayList<FileData>> dateTree;
    
    public FileSystem() {
        this.nameTree = new BST<String,FileData>();
        this.dateTree = new BST<String,ArrayList<FileData>>();
    }


    public FileSystem(String inputFile) {
        this.nameTree = new BST<String,FileData>();
        this.dateTree = new BST<String,ArrayList<FileData>>();
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                nameTree.put(data[0], new FileData(data[0], data[1], data[2]));
                ArrayList<FileData> dateEntry = new ArrayList<FileData>();
                dateEntry.add(new FileData(data[0], data[1], data[2]));
                dateTree.put(data[2],dateEntry);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);

        }
    }


    public void add(String name, String dir, String date) {
        // NameMap add
    	if ( !this.nameTree.containsKey(name) ) {
            this.nameTree.put(name,new FileData(name, dir, date));
        } else {
            int comp = date.compareTo(this.nameTree.get(name).lastModifiedDate);
            if ( comp < 0 && this.nameTree.get(name).dir.equals(dir) ) {
                this.nameTree.replace(name,new FileData(name, dir, date));
            }
        }
        // DateMap add
        if ( !this.dateTree.containsKey(date) ) {
            ArrayList<FileData> entry = new ArrayList<FileData>();
            entry.add(new FileData(name,dir,date));
            this.dateTree.put(date, entry);
        } else {
            ArrayList<FileData> existingEntry = this.dateTree.get(date);
            for (FileData node : existingEntry ) {
                if (!node.name.equals(name)) {
                    existingEntry.add(new FileData(name, dir, date));
                } 
            }
        }

    }


    public ArrayList<String> findFileNamesByDate(String date) {
        
        ArrayList<String> names = new ArrayList<String>();
        if ( this.dateTree.containsKey(date)) {
            ArrayList<FileData> files = this.dateTree.get(date);
            for ( int i = 0; i < files.size(); i++ ) {
                names.add(files.get(i).name);
            }
        }
        return names;
    }


    // TODO
    public FileSystem filter(String startDate, String endDate) {

        return null;
    }
    
    
    // TODO
    public FileSystem filter(String wildCard) {
        return null;
    }
    
    
    public List<String> outputNameTree(){
        ArrayList<String> str = new ArrayList<>();
        List<String> names = this.nameTree.keys();
        for ( int i = 0; i < names.size(); i++ ) {
            String key = names.get(i);
            str.add(key + this.nameTree.get(key).toString());
        }
        return str;
    }
    
    
    // TODO
    public List<String> outputDateTree(){
        return null; 
    }
    

}

