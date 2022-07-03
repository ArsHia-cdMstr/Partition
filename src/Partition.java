import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Partition {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int sets_num = scan.nextInt();  // n
        int main_set_end =scan.nextInt();  // x = 5
        start(sets_num, main_set_end);


    }

    public static void start (int sets_num , int main_set_ending ){

        Scanner scan2 = new Scanner(System.in) ;


        int[][] arr = new int[sets_num][2];

        //give the sets
        for (int i = 0; i < sets_num; i++) {
            arr[i][0] = scan2.nextInt();
            arr[i][1] = scan2.nextInt();
        }

        //sort the sets
        sort_by_Column(arr, 1);

        // make a tree and initialize it

        Segment_tree segment_tree = new Segment_tree(main_set_ending);

        int[] temp = new int[main_set_ending+12];

        segment_tree.construct_tree( temp , main_set_ending  );

        // update sets and use algorithm

        segment_tree.update_start(main_set_ending + 1 ,main_set_ending ,main_set_ending ,1 );

        for (int i = 0; i < sets_num; i++) {
                int amount = segment_tree.getSum(main_set_ending , arr[i][0] , arr[i][1]);
                segment_tree.update_start(main_set_ending ,arr[i][0] , arr[i][0] , amount);
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




//    public static void main(String args[])
//    {
//        int arr[] = {1, 3, 5, 7, 9, 11};
//        int n = arr.length;
//        Segment_tree tree = new Segment_tree(10);
//
//        tree.construct_tree(arr, n);
//
//        System.out.println("Sum of values in given range = " +
//                tree.getSum(n, 1, 3));
//
//        tree.update_start(n, 1, 5, 10);
//
//        System.out.println("Updated sum of values in given range = " +
//     1111111111111111111111111111           tree.getSum(n, 1, 3));
//    }