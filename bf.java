import java.util.*;

public class bf {
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

        // Execute the Best Fit allocation algorithm
        bestFit();
    }

    // Best Fit Allocation Algorithm
    static void bestFit() {
        // Iterate through each job to allocate it to the best-fitting block
        for (int i = 0; i < numJobs; i++) {
            int bestIdx = -1; // Initialize with -1, meaning no block found

            // Search for the smallest block that can fit the current job
            for (int j = 0; j < numBlocks; j++) {
                if (blockSizes[j] >= jobSizes[i]) {
                    // If no block has been selected, or if this block is a better fit, update bestIdx
                    if (bestIdx == -1 || blockSizes[j] < blockSizes[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }

            // If a suitable block was found, allocate the job
            if (bestIdx != -1) {
                allocation[i] = bestIdx;             // Record the allocated block index
                blockSizes[bestIdx] -= jobSizes[i];  // Reduce the block size after allocation
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
