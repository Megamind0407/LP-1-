import java.util.Scanner;

class NextFit {
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

        // Initialize variables for the starting partition index (k) and a flag to check if a fit is found
        int k = 0, flag = 0;

        // Loop through each process
        for (int i = 0; i < npro; i++) {
            // Set the current index to the starting index (k)
            int j = k;

            // Iterate through the partitions in a circular manner until a fit is found
            do {
                if (part[j] >= pro[i]) {
                    if (filled[j] != 1) {
                        // If the partition is not filled, mark it as the selected partition
                        k = j;
                        filled[k] = 1;
                        flag = 1;
                        break;
                    }
                }

                // Move to the next partition index in a circular manner
                j++;
                if (j == npart)
                    j = 0;
            } while (j != k);

            // Output the result based on whether a fitting partition is found
            if (flag == 0)
                System.out.println("Next Fit not found for process " + (i + 1) + ".");
            else
                System.out.println("Next Fit for process " + (i + 1) + " is " + part[k] + " and Hole of " + (part[k] - pro[i]) + " is created.");
        }
    }
}
