import java.util.Scanner;

class FirstFit {
    public static void main(String args[]) {
        // Create a Scanner object to read input from the user
        Scanner sc = new Scanner(System.in);

        // Input the number of partitions
        System.out.print("Enter no. of partitions: ");
        int npart = sc.nextInt();

        // Input the size of each partition
        System.out.print("Enter size of partitions: ");
        int part[] = new int[npart];
        for (int i = 0; i < npart; i++)
            part[i] = sc.nextInt();

        // Input the number of processes
        System.out.print("Enter no. of processes: ");
        int npro = sc.nextInt();

        // Input the size of each process
        System.out.print("Enter size of processes: ");
        int pro[] = new int[npro];
        for (int i = 0; i < npro; i++)
            pro[i] = sc.nextInt();

        // Create an array to track whether each partition is filled
        int filled[] = new int[npart];

        // Loop through each process
        for (int i = 0; i < npro; i++) {
            int k = -1;
            
            // Loop through each partition to find the first one that fits the current process
            for (int j = 0; j < npart; j++) {
                if (part[j] >= pro[i]) {
                    if (filled[j] != 1) {
                        // If the partition is not filled, mark it as the selected partition
                        k = j;
                        filled[k] = 1;
                        break;
                    }
                }
            }

            // Output the result based on whether a fitting partition is found
            if (k == -1)
                System.out.println("First Fit not found for process " + (i + 1) + ".");
            else
                System.out.println(
                        "First Fit for process " + (i + 1) + " is " + part[k] + " and Hole of " + (part[k] - pro[i]) + " is created.");
        }
    }
}
