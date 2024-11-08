import java.util.Scanner;

class FCFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];           // Process ID
        int[] arrivalTime = new int[n];    // Arrival Time
        int[] burstTime = new int[n];      // Burst Time
        int[] completionTime = new int[n]; // Completion Time
        int[] turnAroundTime = new int[n]; // Turnaround Time
        int[] waitingTime = new int[n];    // Waiting Time

        float totalWaitingTime = 0;
        float totalTurnAroundTime = 0;

        // Input the arrival time and burst time for each process
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();

            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
        }

        // Sorting processes by arrival time
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arrivalTime[i] > arrivalTime[j]) {
                    // Swap arrival time, burst time, and process ID
                    int temp;

                    temp = arrivalTime[i];
                    arrivalTime[i] = arrivalTime[j];
                    arrivalTime[j] = temp;

                    temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }

        // Calculating completion, turnaround, and waiting times
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                // First process completes after its burst time
                completionTime[i] = arrivalTime[i] + burstTime[i];
            } else {
                if (arrivalTime[i] > completionTime[i - 1]) {
                    // If the next process arrives after the previous process has finished
                    completionTime[i] = arrivalTime[i] + burstTime[i];
                } else {
                    // If the next process arrives while the previous process is still running
                    completionTime[i] = completionTime[i - 1] + burstTime[i];
                }
            }

            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];

            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
        }

        // Displaying results
        System.out.println("\nPID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + completionTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i]);
        }

        // Displaying average waiting time and turnaround time
        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnAroundTime / n));

        sc.close();
    }
}
