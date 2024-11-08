import java.util.*;

public class ff {
    static int[] jobSizes;      // Array for storing job sizes
    static int[] blockSizes;    // Array for storing block sizes
    static int[] allocation;    // To track which block each job is allocated to
    static int numJobs, numBlocks;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Input number of jobs and blocks
        System.out.println("Enter number of jobs:");
        numJobs = sc.nextInt();
        System.out.println("Enter number of blocks:");
        numBlocks = sc.nextInt();

        // Initialize arrays
        jobSizes = new int[numJobs];
        blockSizes = new int[numBlocks];
        allocation = new int[numJobs];

        // Initialize allocation array to -1 (indicating unallocated jobs)
        for (int i = 0; i < numJobs; i++) {
            allocation[i] = -1;
        }

        // Input job sizes
        System.out.println("Enter sizes of jobs:");
        for (int i = 0; i < numJobs; i++) {
            System.out.print("Job " + (i + 1) + ": ");
            jobSizes[i] = sc.nextInt();
        }

        // Input block sizes
        System.out.println("Enter sizes of blocks:");
        for (int i = 0; i < numBlocks; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            blockSizes[i] = sc.nextInt();
        }

        // Execute the First Fit allocation algorithm
        firstFit();
    }

    // First Fit Allocation Algorithm
    static void firstFit() {
        // Iterate through each job to allocate it to the first-fitting block
        for (int i = 0; i < numJobs; i++) {
            for (int j = 0; j < numBlocks; j++) {
                // Check if the current block can accommodate the job
                if (blockSizes[j] >= jobSizes[i]) {
                    allocation[i] = j;             // Record the allocated block index
                    blockSizes[j] -= jobSizes[i];  // Reduce the block size after allocation
                    break;                         // Exit loop once job is allocated
                }
            }
        }

        // Display the final allocation of jobs to blocks
        displayAllocation();
    }

    // Display the allocation results
    static void displayAllocation() {
        System.out.println("\nJob No.\tJob Size\tBlock No.");
        for (int i = 0; i < numJobs; i++) {
            System.out.print((i + 1) + "\t" + jobSizes[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1); // Output block number (+1 for 1-based indexing)
            else
                System.out.println("Not Allocated");
        }
    }
}
