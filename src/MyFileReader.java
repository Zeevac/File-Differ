import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    public String readHTML(String path) throws IOException {
        File file = new File(path);
        String html = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        return html;
    }

    public void writeHTML(String path,String html) throws IOException {
        File newHtmlFile = new File(path);
        FileUtils.writeStringToFile(newHtmlFile, html, StandardCharsets.UTF_8);
        Desktop.getDesktop().browse(newHtmlFile.toURI());
    }

    public File getFile1() {
        return file1;
    }

    public File getFile2() {
        return file2;
    }
}
