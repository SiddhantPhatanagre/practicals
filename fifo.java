import java.util.*;
import java.util.Scanner;

class fifo {
    static void print(int pages[], int capacity) {
        Queue<Integer> que = new LinkedList<>();
        int pageFaults = 0;
        int hitCount = 0;

        for (int page : pages) {
            if (!que.contains(page)) { // Page fault
                if (que.size() == capacity) {
                    // Remove the oldest page (FIFO)
                    que.poll();
                }
                que.add(page);
                pageFaults++;
                System.out.println("Page " + page + " causes a page fault.");
            } else { // Page hit
                hitCount++;
                System.out.println("Page " + page + " is already in the page frame (hit).");
            }
            System.out.println("Current Page Frame: " + que);
        }

        System.out.println("Total page faults in FIFO: " + pageFaults);
        System.out.println("Total page hits in FIFO: " + hitCount);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the number of pages:");
        int n = scan.nextInt();

        int pages[] = new int[n];
        System.out.println("Enter the Page Reference String:");
        for (int i = 0; i < n; i++) {
            pages[i] = scan.nextInt();
        }

        System.out.println("Enter the Capacity of the Frame:");
        int capacity = scan.nextInt();

        print(pages, capacity);
    }
}
