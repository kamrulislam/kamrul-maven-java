package kamrul.file;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import kamrul.ICsvReader;

public class FileReader implements ICsvReader {

    private String path;

    public FileReader(String path) {
        this.path = path;
    }

    public List<String> getDataFromCsv() {
        List<String> csvAsLineStrings = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File(this.path));
			while (scanner.hasNextLine()) {
				csvAsLineStrings.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        return csvAsLineStrings;
    }

}