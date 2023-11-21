import java.util.Scanner;

class WorstFit {
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

        // Create arrays to track whether each partition is filled and the difference in size between partitions and processes
        int filled[] = new int[npart];
        int diff[] = new int[npart];

        // Loop through each process
        for (int i = 0; i < npro; i++) {
            // Calculate the difference between each partition and the current process size
            for (int j = 0; j < npart; j++) {
                diff[j] = part[j] - pro[i];

                // If the partition is already filled, mark the difference as -1
                if (filled[j] == 1)
                    diff[j] = -1;
            }

            // Find the maximum difference, i.e., the worst fit
            int max = -1, k = 0;
            for (int j = 0; j < npart; j++) {
                if (diff[j] > max) {
                    max = diff[j];
                    k = j;
                }
            }

            // Mark the selected partition as filled
            filled[k] = 1;

            // Output the result based on whether a worst fit is found
            if (diff[k] < 0)
                System.out.println("Worst Fit not found for process " + (i + 1) + ".");
            else
                System.out.println("Worst Fit for process " + (i + 1) + " is " + part[k] + " and Hole of " + diff[k] + " is created.");
        }
    }
}
