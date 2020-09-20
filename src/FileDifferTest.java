import java.io.IOException;

public class FileDifferTest {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Expected two files.");
            return;
        } else {
            String file1 = args[0];
            String file2 = args[1];
            FileDiffer fileDiffer = new FileDiffer(file1, file2);
            fileDiffer.compare();
        }

    }
}
