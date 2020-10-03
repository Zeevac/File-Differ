import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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


    public void compare() throws IOException {
        String html = reader.readHTML("src/index.html");
        String file1 = addFile1ToString(new StringBuilder());
        html = html.replace("$file1",file1);
        String file2 = addFile2ToString(new StringBuilder());
        html = html.replace("$file2",file2);
        reader.writeHTML("src/output.html",html);
    }

    public String changeColor(String line){
        List<String> keywords = new ArrayList<>();
        keywords.add("public");
        keywords.add("private");
        keywords.add("void");
        keywords.add("return");
        keywords.add("for");
        keywords.add("if");
        keywords.add("else");
        keywords.add("new");
        keywords.add("import");
        keywords.add("throws");
        for(String keyword : keywords){
            if (line.contains(keyword)){
                line = line.replace(keyword,String.format("<span>%s</span>",keyword));
            }
        }
        return line;
    }

    public String addFile1ToString(StringBuilder builder){
        for (String line : file1List) {
            if (file2List.contains(line)) {
                line = changeColor(line);
                builder.append(String.format("<pre>%s</pre>",line));
            } else {
                builder.append(String.format("<pre style='color:green;'>%s</pre>",line));
            }
        }
        return builder.toString();
    }

    public String addFile2ToString(StringBuilder builder){
        for (String line : file2List) {
            if (file1List.contains(line)) {
                line = changeColor(line);
                builder.append(String.format("<pre>%s</pre>",line));
            } else {
                builder.append(String.format("<pre style='color:green;'>%s</pre>",line));
            }
        }
        return builder.toString();
    }

}
