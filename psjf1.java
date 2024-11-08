import java.util.Scanner;

class psjf1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Prompt for the number of processes
        System.out.println("Enter the Number of processes (Max 10):");
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

        int time = 0; // Current time
        int completed = 0; // Number of completed processes

        // While there are still processes to complete
        while (completed < n) {
            int shortestIndex = -1;
            int shortestTime = Integer.MAX_VALUE;

            // Find the process with the shortest remaining time that has arrived
            for (int i = 0; i < n; i++) {
                if (at[i] <= time && rem_bt[i] > 0 && rem_bt[i] < shortestTime) {
                    shortestTime = rem_bt[i];
                    shortestIndex = i;
                }
            }

            if (shortestIndex != -1) {
                // Execute the shortest job for one time unit
                rem_bt[shortestIndex]--;
                time++;

                // If the process is completed
                if (rem_bt[shortestIndex] == 0) {
                    completed++;
                    ct[shortestIndex] = time; // Completion time
                    tat[shortestIndex] = ct[shortestIndex] - at[shortestIndex]; // Turnaround time
                    wt[shortestIndex] = tat[shortestIndex] - bt[shortestIndex]; // Waiting time
                }
            } else {
                // If no process is ready, increment the time to the next arrival
                time++;
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

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Average Waiting Time = " + avgWT);
        System.out.println("Average Turnaround Time = " + avgTAT);

        scan.close(); // Close the scanner
    }
}
