public class Segment_tree
{
    int[] tree; // To store segment tree
    int[] lazy; // To store pending updates

    public Segment_tree(int max_array_size) {
        // Max tree size
        int MAX_tree_size = (int) Math.pow(2, max_array_size);
        tree = new int[MAX_tree_size];
        lazy = new int[MAX_tree_size];
    }

    private void update(int segment_index, int segment_start, int segment_end, int update_start,
                         int update_end, int diff)
    {
        if (lazy[segment_index] != 0)
        {
            tree[segment_index] += (segment_end - segment_start + 1) * lazy[segment_index];

            if (segment_start != segment_end)
            {
                lazy[segment_index * 2 + 1] += lazy[segment_index];
                lazy[segment_index * 2 + 2] += lazy[segment_index];
            }

            lazy[segment_index] = 0;
        }

        if (segment_start > segment_end || segment_start > update_end || segment_end < update_start)
            return;

        if (segment_start >= update_start && segment_end <= update_end)
        {
            tree[segment_index] += (segment_end - segment_start + 1) * diff;

            if (segment_start != segment_end)
            {
                lazy[segment_index * 2 + 1] += diff;
                lazy[segment_index * 2 + 2] += diff;
            }
            return;
        }

        int mid = (segment_start + segment_end) / 2;
        update(segment_index * 2 + 1, segment_start, mid, update_start, update_end, diff);
        update(segment_index * 2 + 2, mid + 1, segment_end, update_start, update_end, diff);

        tree[segment_index] = tree[segment_index * 2 + 1] + tree[segment_index * 2 + 2];
    }

    public void update_start(int main_end , int update_start, int update_end, int diff) {
        update(0, 0, main_end, update_start, update_end, diff);
    }

    private int getSumUtil(int segment_start, int segment_end, int query_start, int query_end, int segment_index)
    {
        if (lazy[segment_index] != 0)
        {
            tree[segment_index] += (segment_end - segment_start + 1) * lazy[segment_index];

            if (segment_start != segment_end)
            {
                lazy[segment_index * 2 + 1] += lazy[segment_index];
                lazy[segment_index * 2 + 2] += lazy[segment_index];
            }

            lazy[segment_index] = 0;
        }

        if (segment_start > segment_end || segment_start > query_end || segment_end < query_start)
            return 0;

        if (segment_start >= query_start && segment_end <= query_end)
            return tree[segment_index];

        int mid = (segment_start + segment_end) / 2;
        return getSumUtil(segment_start, mid, query_start, query_end, 2 * segment_index + 1) +
                getSumUtil(mid + 1, segment_end, query_start, query_end, 2 * segment_index + 2);
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

    private void construct(int arr[], int segment_start, int segment_end, int segment_index)
    {
        if (segment_start > segment_end)
            return;

        if (segment_start == segment_end)
        {
            tree[segment_index] = arr[segment_start];
            return;
        }

        int mid = (segment_start + segment_end) / 2;
        construct(arr, segment_start, mid, segment_index * 2 + 1);
        construct(arr, mid + 1, segment_end, segment_index * 2 + 2);

        tree[segment_index] = tree[segment_index * 2 + 1] + tree[segment_index * 2 + 2];
    }

    public void construct_tree(int arr[], int segment_end)
    {
        construct(arr, 0, segment_end , 0);
    }



}
