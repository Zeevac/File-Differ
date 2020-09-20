import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyFileReader {
    private File file1;
    private File file2;

    public MyFileReader(String file1, String file2) {
        this.file1 = new File(file1);
        this.file2 = new File(file2);
    }

    public List<String> readIntoMap(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public File getFile1() {
        return file1;
    }

    public File getFile2() {
        return file2;
    }
}
