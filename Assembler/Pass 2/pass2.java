import java.io.*;
public class pass2{
    public static void main(String[] args) throws Exception {
        BufferedReader inputReader = new BufferedReader(new FileReader("output.txt"));
        BufferedReader symReader = new BufferedReader(new FileReader("symtab.txt"));
        BufferedReader litReader = new BufferedReader(new FileReader("littab.txt"));
        BufferedWriter outputWriter = new BufferedWriter(new FileWriter("output2.txt"));

        String inputLine;
        String symLine = null;
        String litLine = null;

        while ((inputLine = inputReader.readLine()) != null) {
            String[] tokens = inputLine.split("\\)\\(");
            StringBuilder outputLine = new StringBuilder();

            for (String token : tokens) {
                token = token.replaceAll("[\\(\\)]", "");

                if (token.startsWith("S,")) {
                    if (symLine == null) {
                        symLine = symReader.readLine();
                    }
                    if (symLine != null) {
                        String[] symTokens = symLine.split("\t");
                        if (symTokens.length > 1) {
                            int address = Integer.parseInt(symTokens[1]);
                            token = token.replace("S,", "");
                            token = getFormattedMachineCode(token, address);
                        }
                    }
                } else if (token.startsWith("L,")) {
                    if (litLine == null) {
                        litLine = litReader.readLine();
                    }
                    if (litLine != null) {
                        String[] litTokens = litLine.split("\t");
                        if (litTokens.length > 1) {
                            int address = Integer.parseInt(litTokens[1]);
                            token = token.replace("L,", "");
                            token = getFormattedMachineCode(token, address);
                        }
                    }
                }

                outputLine.append(token.replace(",", "")).append(" ");

            }

            outputLine = new StringBuilder(outputLine.toString().replaceAll("[A-Za-z]", ""));
            outputWriter.write(outputLine.toString().trim());
            outputWriter.newLine();
        }

        inputReader.close();
        symReader.close();
        litReader.close();
        outputWriter.close();
    }

    private static String getFormattedMachineCode(String instruction, int address) {
        String[] parts = instruction.split(",");
        String opcode = parts[0];
        String[] operands = parts.length > 1 ? parts[1].split(" ") : new String[0];

        String registerNumbers = "";
        for (String operand : operands) {
            int regIndex = Integer.parseInt(operand);
            registerNumbers += getRegisterNumber(regIndex);
        }

        return opcode + " " + registerNumbers + " " + address;
        
    }

    private static String getRegisterNumber(int index) {
        String[] regNumbers = {"01", "02", "03", "04"};
        if (index >= 0 && index < regNumbers.length) {
            return regNumbers[index];
        }
        return "";
    }
}