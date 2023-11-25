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
        if ( name == null || dir == null || date == null ) {
            return;
        }
        // If there isn't a file in the system with the same name 
    	if ( !this.nameTree.containsKey(name) ) {
            // Add to nameTree normally
            this.nameTree.put(name,new FileData(name, dir, date));
            // Check if dateTree has an entry with the same date
            if ( !this.dateTree.containsKey(date) ) {
                ArrayList<FileData> entry = new ArrayList<FileData>();
                entry.add(new FileData(name,dir,date));
                this.dateTree.put(date, entry);
            } else {
                this.dateTree.get(date).add(new FileData(name, dir, date));
            }
        } else {
            // If the maps contain a file with the same name 
            String oldDate = this.nameTree.get(name).lastModifiedDate;
            int comp = oldDate.compareTo(date);
            if ( comp < 0 ) {
                ArrayList<FileData> entry = this.dateTree.get(oldDate);
                this.nameTree.replace(name, new FileData(name, dir, date));
                // Adding to dateTree
                FileData toBeRemoved = null;
                // Finding file to be removed
                for ( FileData file : entry ) {
                    if ( file.name.equals(name) ) {
                        toBeRemoved = file;
                    }
                }
                entry.remove(toBeRemoved);
                // Remove the node if the arraylist is empty
                if ( entry.size() == 0 ) {
                    this.dateTree.remove(oldDate);
                }
                ArrayList<FileData> newEntry = new ArrayList<>();
                newEntry.add(new FileData(name, dir, date));
                this.dateTree.put(date, newEntry);
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


    public FileSystem filter(String startDate, String endDate) {
        FileSystem result = new FileSystem();
        List<String> dateKeys = this.dateTree.keys();
        for ( int i = 0 ; i < dateKeys.size(); i++ ) {
            String currDate = dateKeys.get(i);
            int startComp = currDate.compareTo(startDate);
            int endComp = currDate.compareTo(endDate);
            if ( startComp >= 0 && endComp < 0 ) {
                ArrayList<FileData> toBeAdded = this.dateTree.get(currDate);
                for ( FileData file : toBeAdded ) {
                    result.add(file.name,file.dir,file.lastModifiedDate);
                }
            }
        }
        return result;
    }
    
    
    public FileSystem filter(String wildCard) {
        FileSystem result = new FileSystem();
        List<String> nameKeys = this.nameTree.keys();
        for ( int i = 0; i < nameKeys.size(); i++ ) {
            if ( nameKeys.get(i).contains(wildCard) ) {
                FileData toBeAdded = this.nameTree.get(nameKeys.get(i));
                result.add(toBeAdded.name, toBeAdded.dir, toBeAdded.lastModifiedDate);
            }
        }
        return result;
    }
    
    
    public List<String> outputNameTree(){
        ArrayList<String> str = new ArrayList<>();
        if ( !this.nameTree.isEmpty()) {
            List<String> names = this.nameTree.keys();
            for ( int i = 0; i < names.size(); i++ ) {
                String key = names.get(i);
                str.add(key + ": " +this.nameTree.get(key).toString());
            }
        }
        return str;
    }    
    
    public List<String> outputDateTree(){

        ArrayList<String> str = new ArrayList<>();
        if ( !this.dateTree.isEmpty()) {
            List<String> dates = this.dateTree.keys();
            for ( int i = dates.size() - 1; i >= 0; i--  ) {
                ArrayList<FileData> files = this.dateTree.get(dates.get(i));
                for ( int j = files.size() - 1 ; j >= 0 ; j-- ) {
                    str.add(dates.get(i) + ": " + files.get(j).toString());
                }
            }
        }
        return str; 
    }
    

}

