import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class lru {
    static void print(int pages[], int capacity) {
        List<Integer> list = new ArrayList<>();
        int pageFaults = 0;
        int hitCount = 0;

        System.out.println("\nLRU Page Replacement:");
        for (int page : pages) {
            if (!list.contains(page)) { // Page fault
                if (list.size() == capacity) {
                    list.remove(0); // Remove least recently used page
                }
                list.add(page);
                pageFaults++;
                System.out.println("Page " + page + " causes a page fault.");
            } else { // Page hit
                hitCount++;
                list.remove(Integer.valueOf(page)); // Move the page to the end
                list.add(page);
                System.out.println("Page " + page + " is accessed and rearranged (hit).");
            }
            System.out.println("Current Page Frame: " + list);
        }

        System.out.println("Total Page Faults in LRU: " + pageFaults);
        System.out.println("Total Page Hits in LRU: " + hitCount);
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
