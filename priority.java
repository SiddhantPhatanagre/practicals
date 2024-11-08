import java.util.Scanner;

class priority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];       // Process ID
        int[] arrivalTime = new int[n]; // Arrival Time
        int[] burstTime = new int[n];   // Burst Time
        int[] priority = new int[n];    // Priority of each process
        int[] completionTime = new int[n]; // Completion Time
        int[] turnAroundTime = new int[n]; // Turnaround Time
        int[] waitingTime = new int[n];    // Waiting Time

        float totalWaitingTime = 0;
        float totalTurnAroundTime = 0;

        // Input the details of each process
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();

            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();

            System.out.print("Enter priority for process " + (i + 1) + ": ");
            priority[i] = sc.nextInt();
        }

        // Sorting processes by arrival time and priority (if arrival time is the same)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arrivalTime[i] > arrivalTime[j] || (arrivalTime[i] == arrivalTime[j] && priority[i] > priority[j])) {

                    // Swap based on arrival time and priority
                    int temp;

                    temp = arrivalTime[i];
                    arrivalTime[i] = arrivalTime[j];
                    arrivalTime[j] = temp;

                    temp = burstTime[i];
                    burstTime[i] = burstTime[j];
                    burstTime[j] = temp;

                    temp = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp;

                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;
                }
            }
        }

        // Scheduling logic
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                completionTime[i] = arrivalTime[i] + burstTime[i];
            } else {
                if (arrivalTime[i] > completionTime[i - 1]) {
                    completionTime[i] = arrivalTime[i] + burstTime[i];
                } else {
                    completionTime[i] = completionTime[i - 1] + burstTime[i];
                }
            }

            turnAroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnAroundTime[i] - burstTime[i];

            totalWaitingTime += waitingTime[i];
            totalTurnAroundTime += turnAroundTime[i];
        }

        // Display results
        System.out.println("\nPID\tArrival\tBurst\tPriority\tCompletion\tTurnaround\tWaiting");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + arrivalTime[i] + "\t" + burstTime[i] + "\t" + priority[i] + "\t\t" + completionTime[i] + "\t\t" + turnAroundTime[i] + "\t\t" + waitingTime[i]);
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnAroundTime / n));
    }
}
