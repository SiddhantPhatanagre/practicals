import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class optimal {
    public static void optimal(int[] pages, int capacity) {
        List<Integer> list = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("\nOptimal Page Replacement:");

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            // Check if page is already in memory (no page fault)
            if (!list.contains(page)) {  // Page fault occurs
                if (list.size() == capacity) {
                    // Find the page to replace (furthest in future)
                    int furthest = -1, indexToReplace = -1;

                    for (int j = 0; j < list.size(); j++) {
                        int currentPage = list.get(j);
                        int nextOccurrence = Integer.MAX_VALUE;

                        // Check when this page will appear next
                        for (int k = i + 1; k < pages.length; k++) {
                            if (pages[k] == currentPage) {
                                nextOccurrence = k;
                                break;
                            }
                        }

                        // Replace the page with the furthest next occurrence
                        if (nextOccurrence > furthest) {
                            furthest = nextOccurrence;
                            indexToReplace = j;
                        }
                    }

                    list.remove(indexToReplace);  // Replace the page
                }

                list.add(page);  // Add the new page
                pageFaults++;
                System.out.println("Page " + page + " causes a page fault.");
            } else {
                System.out.println("Page " + page + " already in memory.");
            }

            System.out.println("Current List: " + list);
        }

        System.out.println("Total page faults in Optimal: " + pageFaults);
    }

    // Main function to simulate Optimal Page Replacement
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of pages:");
        int n = sc.nextInt();
        int[] pages = new int[n];

        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        System.out.println("Enter the frame capacity:");
        int capacity = sc.nextInt();

        // Simulate Optimal Page Replacement
        optimal(pages, capacity);

        sc.close();
    }
}
