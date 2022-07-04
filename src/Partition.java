import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Partition {
    final static int reminder_from = 1000000007 ;
    public static void main(String[] args) throws FileNotFoundException {
        start();
    }

    public static void start () throws FileNotFoundException {

        File readF = new File("C:\\Users\\Arshia\\IdeaProjects\\Partition\\src\\input.txt");
        Scanner scan = new Scanner(readF);

        int sets_num = scan.nextInt();  // n
        int main_set_ending =scan.nextInt();  // x = 5


        int[][] arr = new int[sets_num][2];

        //give the sets
        for (int i = 0; i < sets_num; i++) {
            arr[i][0] = scan.nextInt();
            arr[i][1] = scan.nextInt();
        }

        //sort the sets
        sort_by_Column(arr, 1);

        // make a tree and initialize it

        Segment_tree segment_tree = new Segment_tree();

        int[] temp = new int[main_set_ending+2];

        segment_tree.construct_tree( temp , main_set_ending  );

        // update sets and use algorithm

        segment_tree.update_start(main_set_ending ,main_set_ending ,main_set_ending ,1 );

        for (int i = 0; i < sets_num; i++) {
            int amount = segment_tree.getSum(main_set_ending , arr[i][0] , arr[i][1]);
            segment_tree.update_start(main_set_ending ,arr[i][0] , arr[i][0] , amount % reminder_from );
        }

        System.out.println( segment_tree.getSum( main_set_ending , 0, 0 ));

    }

    // Function to sort by column
    public static void sort_by_Column(int arr[][], int col)
    {
        // Using built-in sort function Arrays.sort
        Arrays.sort(arr, new Comparator<int[]>() {

            @Override
            // Compare values according to columns
            public int compare(final int[] entry1,
                               final int[] entry2) {

                // To sort in descending order revert
                // the '>' Operator
                if (entry1[col] < entry2[col])
                    return 1;
                else
                    return -1;
            }
        } );  // End of function call sort().
    }
}
