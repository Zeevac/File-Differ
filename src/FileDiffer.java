import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FileDiffer {

    private List<String> file1List;
    private List<String> file2List;
    private MyFileReader reader;
    private final static List<String> keywords = Arrays.asList("public", "private", "protected", "void", "return", "for",
            "if", "else", "new", "import", "throws", "package", "final", "this", "try", "catch", "true", "false", "class", "null",
            "boolean", "int", "double", "float", "static", "extends", "implements", "interface", "enum");
    private final static List<String> annotations = Arrays.asList("@Override","@Nullable");
    private final static List<String> numbers = Arrays.asList("0","1","2","3","4","5","6","7","8","9");
    public FileDiffer(String file1, String file2) throws IOException {
        reader = new MyFileReader(file1, file2);
        file1List = reader.readIntoMap(reader.getFile1());
        file2List = reader.readIntoMap(reader.getFile2());
    }


    public void compare() throws IOException {
        String html = reader.readHTML("src/template.html");
        String file1 = addFile1ToString(new StringBuilder());
        html = html.replace("$file1", file1);
        String file2 = addFile2ToString(new StringBuilder());
        html = html.replace("$file2", file2);
        reader.writeHTML("src/output.html", html);
    }

    public String changeColor(String line) {
        for (String keyword : keywords) {
            if (line.contains(keyword)) {
                line = line.replace(keyword, String.format("<span>%s</span>", keyword));
            }
        }
        for (String annotation : annotations){
            if (line.contains(annotation)){
                line = line.replace(annotation,String.format("<span style='color:goldenrod;'>%s</span>", annotation));
            }
        }

        for (String number : numbers){
            if (line.contains(number)){
                line = line.replace(number,String.format("<span style='color:cornflowerblue;'>%s</span>", number));
            }
        }
        return line;
    }

    public String addFile1ToString(StringBuilder builder) {
        for (String line : file1List) {
            if (file2List.contains(line)) {
                line = changeColor(line);
                builder.append(String.format("<pre>%s</pre>", line));
            } else {
                builder.append(String.format("<pre style='color:chartreuse;'>%s</pre>", line));
            }
        }
        return builder.toString();
    }

    public String addFile2ToString(StringBuilder builder) {
        for (String line : file2List) {
            if (file1List.contains(line)) {
                line = changeColor(line);
                builder.append(String.format("<pre>%s</pre>", line));
            } else {
                builder.append(String.format("<pre style='color:chartreuse;'>%s</pre>", line));
            }
        }
        return builder.toString();
    }

}
