public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    public FileData(String name, String directory, String modifiedDate) {
        this.name = name;
        this.dir = directory;
        this.lastModifiedDate = modifiedDate;
    }
    /*
     * A method that returns the string representation of 
     * FileData by displaying the file information. 
     * It should strictly follow the format of 
     * {Name: file_name, Directory: dir_name, Modified Date: date}.
     */
    @Override
    public String toString() {
        // STUB
        String str = "{Name: "+this.name+", Directory: "+this.dir+", Modified Date: "+this.lastModifiedDate+"}";
        return str;
    }
}