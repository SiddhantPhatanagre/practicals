import java.util.Scanner;

class rr1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Prompt for the number of processes
        System.out.println("Enter the Number of the processes (Max 10):");
        int n = scan.nextInt();

        // Declare arrays for burst time, waiting time, turnaround time, remaining burst time, arrival time, and completion time
        int[] bt = new int[n]; // Burst time
        int[] wt = new int[n]; // Waiting time
        int[] tat = new int[n]; // Turnaround time
        int[] rem_bt = new int[n]; // Remaining burst time
        int[] at = new int[n]; // Arrival time
        int[] ct = new int[n]; // Completion time

        // Input the arrival times and burst times for each process
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the arrival time of process P" + (i + 1) + ": ");
            at[i] = scan.nextInt();
            System.out.print("Enter the burst time of process P" + (i + 1) + ": ");
            bt[i] = scan.nextInt();
            rem_bt[i] = bt[i]; // Initialize remaining burst time with burst time
        }

        // Input the quantum time
        System.out.println("Enter the Quantum Time:");
        int qt = scan.nextInt();

        boolean allDone = false; // To check if all processes are completed
        int time = 0; // Current time

        // While there are still processes to complete
        while (!allDone) {
            allDone = true; // Assume all processes are done

            // Loop through each process
            for (int i = 0; i < n; i++) {
                // Check if the process has remaining burst time and has arrived
                if (rem_bt[i] > 0 && at[i] <= time) {
                    allDone = false; // Not done yet
                    if (rem_bt[i] > qt) {
                        // Process can run for the entire quantum
                        time += qt;
                        rem_bt[i] -= qt; // Reduce remaining time
                    } else {
                        // Process finishes execution
                        time += rem_bt[i];
                        rem_bt[i] = 0; // Set remaining time to zero
                        ct[i] = time; // Completion time

                        // Calculate turnaround time (TAT) = Completion Time - Arrival Time
                        tat[i] = ct[i] - at[i];

                        // Calculate waiting time (WT) = Turnaround Time - Burst Time
                        wt[i] = tat[i] - bt[i];
                    }
                }
            }

            // If all processes are done, check for any remaining processes that haven't arrived yet
            if (allDone) {
                for (int i = 0; i < n; i++) {
                    // If any process hasn't arrived yet, move time forward
                    if (at[i] > time) {
                        time = at[i];
                        allDone = false; // Set done to false
                        break;
                    }
                }
            }
        }

        // Output the results
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time");
        float totalWT = 0, totalTAT = 0; // For calculating averages

        for (int i = 0; i < n; i++) {
            totalWT += wt[i];
            totalTAT += tat[i];

            // Display process details
            System.out.println("P" + (i + 1) + "\t\t" + at[i] + "\t\t" + bt[i] + "\t\t" + ct[i] + "\t\t" + tat[i] + "\t\t" + wt[i]);
        }

        // Calculate and display average waiting time and average turnaround time
        float avgWT = totalWT / n;
        float avgTAT = totalTAT / n;

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Average Waiting Time = " + avgWT);
        System.out.println("Average Turnaround Time = " + avgTAT);
        
        scan.close(); // Close the scanner
    }
}
