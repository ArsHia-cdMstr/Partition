public class Segment_tree
{
    private final int reminder_from = 1000000007;

    // Max tree size
    private final int MAX_tree_size = 400000;
    private final int[] tree = new int[MAX_tree_size]; // To store segment tree
    private final int[] lazy = new int[MAX_tree_size]; // To store pending updates


    private void update(int segment_index, int segment_start, int segment_end, int update_start,
                        int update_end, int diff)
    {

        if (lazy[segment_index] != 0)
        {

            tree[segment_index] = (tree[segment_index] +
                    ( (segment_end - segment_start + 1) * lazy[segment_index] )
                            % reminder_from)
                    % reminder_from ;

            if (segment_start != segment_end)
            {
                lazy[segment_index * 2 + 1] = ( lazy[segment_index * 2 + 1] + lazy[segment_index] ) % reminder_from;
                lazy[segment_index * 2 + 2] = ( lazy[segment_index * 2 + 2] + lazy[segment_index] ) % reminder_from;
            }

            lazy[segment_index] = 0;
        }

        if (segment_start > segment_end || segment_start > update_end || segment_end < update_start)
            return;

        if (segment_start >= update_start && segment_end <= update_end) // total overlap
        {
            tree[segment_index] = (tree[segment_index] + ( ( (segment_end - segment_start + 1) * diff)% reminder_from) ) % reminder_from;

            if (segment_start != segment_end)
            {
                lazy[segment_index * 2 + 1] = ( lazy[segment_index * 2 + 1]  + diff) % reminder_from;
                lazy[segment_index * 2 + 2] = (lazy[segment_index * 2 + 2] + diff) % reminder_from;
            }
            return;
        }

        int s = segment_start + segment_end;
        int mid = (s) / 2;


        update(segment_index * 2 + 1, segment_start, mid, update_start, update_end, diff);
        update(segment_index * 2 + 2, mid + 1, segment_end, update_start, update_end, diff);

        tree[segment_index] = (tree[segment_index * 2 + 1] + tree[segment_index * 2 + 2]) % reminder_from;
    }

    public void update_start(int main_end , int update_start, int update_end, int diff) {
        update(0, 0, main_end, update_start, update_end, diff);
    }

    private int getSumUtil(int segment_start, int segment_end, int query_start, int query_end, int segment_index)
    {
        if (lazy[segment_index] != 0)
        {
            tree[segment_index] = (tree[segment_index] + (
                    (
                            ((segment_end - segment_start) + 1) * lazy[segment_index]
                    ) % reminder_from)) % reminder_from;

            if (segment_start != segment_end)
            {
                lazy[segment_index * 2 + 1] = (lazy[segment_index * 2 + 1] + lazy[segment_index] )% reminder_from;
                lazy[segment_index * 2 + 2] = (lazy[segment_index * 2 + 2] + lazy[segment_index] )% reminder_from;
            }

            lazy[segment_index] = 0;
        }

        if (segment_start > segment_end || segment_start > query_end || segment_end < query_start)
            return 0;

        if (segment_start >= query_start && segment_end <= query_end) {
            int tmp = tree[segment_index] % reminder_from;
            return tmp;
        }

        int mid = (segment_start + segment_end) / 2;

        int tmp = ((getSumUtil(segment_start, mid, query_start, query_end, 2 * segment_index + 1)% reminder_from )+
                (getSumUtil(mid + 1, segment_end, query_start, query_end, 2 * segment_index + 2)% reminder_from));

        return  tmp % reminder_from;
    }

    public int getSum(int main_end , int query_start, int query_end)
    {
        if (query_start < 0 || query_end > main_end || query_start > query_end)
        {
            System.out.println("Invalid Input");
            return -1;
        }

        return getSumUtil(0, main_end, query_start, query_end, 0);
    }

    private void construct(int[] arr, int segment_start, int segment_end, int segment_index)
    {
        if (segment_start > segment_end)
            return;

        if (segment_start == segment_end)
        {
            int a = arr[segment_start];
            tree[segment_index] = a;
            return;
        }

        int mid = (segment_start + segment_end) / 2;
        construct(arr, segment_start, mid, segment_index * 2 + 1);
        construct(arr, mid + 1, segment_end, segment_index * 2 + 2);

        tree[segment_index] = (tree[segment_index * 2 + 1] + tree[segment_index * 2 + 2]) % reminder_from;
    }

    public void construct_tree(int[] arr, int segment_end)
    {
        construct(arr, 0, segment_end , 0);
    }



}
