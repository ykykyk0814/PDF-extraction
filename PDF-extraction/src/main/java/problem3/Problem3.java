package problem3;
//reference:https://stackoverflow.com/questions/23813727/how-to-extract-text-from-a-pdf-file-with-apache-pdfbox
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.*;

public class Problem3 {

    public static final List<String> TITLES = new ArrayList<String>() {{
        add("abstract");
        add("introduction");
        add("conclusion");
        add("assumption");
    }};

    // Problem 1
    public static String extractPDFFile(File file) throws Exception {
        PDDocument document = PDDocument.load(file);
        int totalPages = document.getNumberOfPages();
        StringBuilder text = new StringBuilder();
        for (int pageNumber = 0; pageNumber < totalPages; pageNumber++) {
            PDPage page = document.getPage(pageNumber);
            InputStream contents = page.getContents();
            if (contents != null) {
                PDFTextStripper stripper = new PDFTextStripper();
                stripper.setStartPage(pageNumber + 1);
                stripper.setEndPage(pageNumber + 1);
                String pageText = stripper.getText(document);
                text.append(pageText);
            }
        }
        document.close();
        return text.toString();
    }

    // Problem 2
    public static void formatPDF2TXT(File pdf, File txt) throws Exception {
        List<String> pdfLines = new ArrayList<>(Arrays.asList(extractPDFFile(pdf).split("\n")));
        List<String> txtLines = new ArrayList<>();
        for (int index = 0; index < pdfLines.size(); index += 1) {
            if (pdfLines.get(index).trim().isEmpty() && index > 0 && txtLines.get(txtLines.size() - 1).equals("\n")) {
                continue;
            }
            for (String title : TITLES) {
                if (pdfLines.get(index).toLowerCase().contains(title.toLowerCase())) {
                    txtLines.add("\n");
                    break;
                }
            }
            txtLines.add(pdfLines.get(index).trim() + "\n");
        }
        StringBuilder txtText = new StringBuilder();
        for (String line : txtLines) {
            txtText.append(line);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(txt));
        bw.write(txtText.toString());
        bw.close();
    }

    public static void processPDF2TXT(String pdfPath, String txtPath) throws Exception {
        File directory = new File(pdfPath);
        File[] files = directory.listFiles();
        for (File file : files) {
            String filename = file.getName();
            File pdf = new File(pdfPath + filename);
            File txt = new File(txtPath + filename.replace("pdf", "txt"));
            formatPDF2TXT(pdf, txt);
        }
    }

    // problem 3.1
    public static ArrayList<String> problem3_1(String path, int count) throws Exception {
        String text = extractPDFFile(new File(path));
        ArrayList<String> topWords = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String[] words = text.split(" ");
        for (String word : words) {
            if (word.trim().length() > 0) {
                map.put(word, 1 + map.getOrDefault(word, 0));
            }
        }
        ArrayList<Integer> values = new ArrayList<>(map.values());
        Collections.sort(values);
        int max10thNumber = values.get(values.size() - count);
        for (String word : map.keySet()) {
            if (map.get(word) >= max10thNumber) {
                topWords.add(word);
            }
        }
        return topWords;
    }

    // problem 3.2
    public static boolean problem3_2(String path, String word) throws Exception {
        return extractPDFFile(new File(path)).contains(word);
    }

    // problem 3.3
    public static ArrayList<Boolean> problem3_3(String path, ArrayList<String> titles) throws Exception {
        String text = extractPDFFile(new File(path));
        ArrayList<Boolean> results = new ArrayList<>();
        for (String title : titles) {
            results.add(text.contains(title));
        }
        return results;
    }

    // problem 3.4
    public static List<String> problem3_4(String pdfPath, String target) throws Exception {
        List<String> titles = new ArrayList<>();
        File directory = new File(pdfPath);
        File[] files = directory.listFiles();
        for (File file : files) {
            String filename = file.getName();
            String text = extractPDFFile(new File(pdfPath + filename));
            if (text.contains(target)) {
                titles.add(filename);
            }
        }
        return titles;
    }   public static void main(String[] args) throws Exception {
        // TODO Test: Problem 1
         String text = extractPDFFile(new File("PDFData/Easy to Process/291.pdf"));
         System.out.println(text);

        // TODO Test: Problem 2
        for (String type : new String[]{"Easy", "Challenging"})
            processPDF2TXT(
                    "PDFData/" + type + " to Process/",
                    "FormatData/" + type + " to Process/"
            );

        // TODO Test: Problem 3.1
        System.out.println(problem3_1("PDFData/Easy to Process/291.pdf", 20));

        // TODO Test: Problem 3.2
        System.out.println(problem3_2("PDFData/Easy to Process/291.pdf", "cell"));
        System.out.println(problem3_2("PDFData/Easy to Process/291.pdf", "Byzantium"));

        // TODO Test: Problem 3.3
        System.out.println(problem3_3("PDFData/Easy to Process/291.pdf", new ArrayList<String>() {{
            add("abstract");
            add("introduction");
            add("conclusion");
            add("assumption");
         }}));

        // TODO Test: Problem 3.4
        System.out.println(problem3_4("PDFData/Easy to Process/", "computer"));
    }
}
