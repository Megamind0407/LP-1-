import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Pass2 {
    public static void main(String[] args) {
        try {
           
            String mntFile = "mnt.txt";
            String mdtFile = "mdt.txt";
            String alaFile = "ala.txt";
            String output2File = "output2.txt";
            String inputCodeFile = "inter.txt";

            
            BufferedReader mntReader = new BufferedReader(new FileReader(mntFile));
            BufferedReader mdtReader = new BufferedReader(new FileReader(mdtFile));
            BufferedReader alaReader = new BufferedReader(new FileReader(alaFile));
            BufferedWriter output2Writer = new BufferedWriter(new FileWriter(output2File));
            BufferedReader inputCodeReader = new BufferedReader(new FileReader(inputCodeFile));

            
            Map<String, List<String>> alaTable = new HashMap<>();
            Map<String, String> macroNameMap = new HashMap<>();

            
            String line;
            while ((line = alaReader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    String parameterName = parts[1];
                    String parameterValue = parts[0];
                    alaTable.computeIfAbsent(parameterName, k -> new ArrayList<>()).add(parameterValue);
                }
            }

            
            while ((line = mntReader.readLine()) != null) {
                String[] parts = line.split("\\t");
                if (parts.length >= 3) {
                    String macroName = parts[1];
                    StringBuilder macroDefinition = new StringBuilder();
                    while (true) {
                        line = mdtReader.readLine();
                        if (line == null || line.trim().equals("MEND")) {
                            break; // End of macro definition
                        }
                        macroDefinition.append(line).append("\n");
                    }
                    macroNameMap.put(macroName, macroDefinition.toString());
                }
            }

            
            while ((line = inputCodeReader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (macroNameMap.containsKey(parts[0])) {
                    // Macro call found, expand it
                    String macroName = parts[0];
                    String macroDefinition = macroNameMap.get(macroName);
                    for (Map.Entry<String, List<String>> entry : alaTable.entrySet()) {
                        String parameter = entry.getKey();
                        List<String> arguments = entry.getValue();
                        for (String argument : arguments) {
                            macroDefinition = macroDefinition.replaceAll(Pattern.quote(parameter), argument);
                        }
                    }
                    
                    output2Writer.write(macroDefinition);
                    output2Writer.newLine();
                } else {
                    
                    output2Writer.write(line);
                    output2Writer.newLine();
                }
            }

            
            mntReader.close();
            mdtReader.close();
            alaReader.close();
            inputCodeReader.close();
            output2Writer.close();

           
            System.out.println("Pass 2 completed successfully. Check output2.txt for the expanded code.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
