import java.io.IOException;
import java.util.List;

public class FileDiffer {

    private List<String> file1List;
    private List<String> file2List;
    private MyFileReader reader;

    public FileDiffer(String file1, String file2) throws IOException {
        reader = new MyFileReader(file1, file2);
        file1List = reader.readIntoMap(reader.getFile1());
        file2List = reader.readIntoMap(reader.getFile2());
    }


    public void compare() {
        for (String line : file1List) {
            if (file2List.contains(line)) {
                System.out.println(line);
            } else {
                System.out.println(ConsoleColors.GREEN + line + ConsoleColors.RESET);
            }
        }
        for (String line : file2List) {
            if (file1List.contains(line)) {
                System.out.println(line);
            } else {
                System.out.println(ConsoleColors.GREEN + line + ConsoleColors.RESET);
            }
        }
    }

}
