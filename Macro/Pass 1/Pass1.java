import java.io.*;
import java.util.*;

class MacroDefinition {
    String name;
    List<String> parameterList;
    List<String> definition;

    public MacroDefinition(String name, List<String> parameterList, List<String> definition) {
        this.name = name;
        this.parameterList = parameterList;
        this.definition = definition;
    }
}

public class Pass1 {
    public static int alac;
    public static String[][] ala;
    public static int mntc;
    public static Object[][] mnt;
    public static Object[][] mdt;

    public static void main(String[] args) {
        try {
            // Input and Output file names
            String inputFile = "input.txt";
            String mntOutputFile = "mnt.txt";
            String mdtOutputFile = "mdt.txt";
            String alaOutputFile = "ala.txt";
            String outputOutputFile = "inter.txt";

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter mntWriter = new BufferedWriter(new FileWriter(mntOutputFile));
            BufferedWriter mdtWriter = new BufferedWriter(new FileWriter(mdtOutputFile));
            BufferedWriter alaWriter = new BufferedWriter(new FileWriter(alaOutputFile));
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputOutputFile));

            Map<String, MacroDefinition> macroTable = new HashMap<>();
            List<String> outputCode = new ArrayList<>();

            String currentMacroName = null;
            List<String> currentParameterList = null;
            List<String> currentDefinition = new ArrayList<>();
            String line;

            int mntc = 1; // Initialize MNT counter
            int mdtc = 1; // Initialize MDT counter
            int alac = 1;
            int flag = 0;
            String MACRO = null;

            while ((line = reader.readLine()) != null) {
                String parts[] = line.split("\\s+");
                if (parts[0].equalsIgnoreCase("MACRO")) {
                    flag = 1;
                    line = reader.readLine();
                    parts = line.split("\\s+");
                    MACRO = parts[0];
                    if (parts.length <= 1) {
                        writeMNTEntry(mntWriter, mntc, MACRO, mdtc); // Add MDT counter to MNT
                        mntc++;
                        continue;
                    }
                    writeMNTEntry(mntWriter, mntc, MACRO, mdtc); // Add MDT counter to MNT
                    mntc++;
                    currentMacroName = MACRO;
                    currentParameterList = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        parts[i] = parts[i].replaceAll("[&,]", "");
                        writeALAEntry(alaWriter, alac, parts[i]);
                        alac++;
                        currentParameterList.add(parts[i]);
                    }
                } else if (parts[0].equalsIgnoreCase("MEND")) {
                    writeMDTEntry(mdtWriter, mdtc, line);
                    flag = 0;
                    mdtc++;

                    MacroDefinition macro = new MacroDefinition(currentMacroName, currentParameterList, currentDefinition);
                    macroTable.put(currentMacroName, macro);

                    currentMacroName = null;
                    currentParameterList = null;
                    currentDefinition = new ArrayList<>();
                } else if (flag == 1) {
                    writeMDTEntry(mdtWriter, mdtc, line);
                    mdtc++;
                    currentDefinition.add(line);
                } else {
                    outputCode.add(line);
                }
            }

            // Close the file writers
            mntWriter.close();
            mdtWriter.close();
            alaWriter.close();
            reader.close();

            // Output a message to indicate the operation is complete
            System.out.println("Pass 1 completed successfully. Check mnt.txt for the macro names.");

            // Process the output code (e.g., write it to output.txt)
            for (String outputLine : outputCode) {
                outputWriter.write(outputLine);
                outputWriter.newLine();
            }
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeMNTEntry(BufferedWriter writer, int mntc, String MACRO, int mdtc) throws IOException {
        writer.write(mntc + "\t" + MACRO + "\t" + mdtc); // Write MNT with MDT counter
        writer.newLine();
    }

    private static void writeALAEntry(BufferedWriter writer, int alac, String parameter) throws IOException {
        writer.write(alac + "\t" + parameter);
        writer.newLine();
    }

    private static void writeMDTEntry(BufferedWriter writer, int mdtc, String line) throws IOException {
        writer.write(mdtc + "\t" + line);
        writer.newLine();
    }
}
