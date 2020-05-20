package Service.inheritance_complexity;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import Model.CustomFile;
import Model.FileRead;
import Model.Line;

public class FileReadServiceImp {
	// Code segmant for open files
		public void openFile(FileRead file, CustomFile fileobj) {
			try {
				file.setScanner(new Scanner(new File(fileobj.getFilePath() + file.getFileName())));
			} catch (Exception e) {
				System.out.println("Couldn't find the file.");
			}
		}

		// Code segmant for read files and store in a object
		public void readFile(FileRead file, CustomFile fileobj) {
			int count = 1;
			ArrayList<Line> lineSet = new ArrayList<Line>();
			while (file.getScanner().hasNext()) {
				Line line = new Line(count, file.getScanner().nextLine());
				lineSet.add(line);
				count++;
			}
			fileobj.setLineSet(lineSet);
			fileobj.setLastIndex(lineSet.size());
		}

		// Code segmant for close opened files
		public void closeFile(FileRead file) {
			file.getScanner().close();
		}
}
