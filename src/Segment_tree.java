// our segment tree is a sum segment tree

public class Segment_tree {
    private int index_tree_num ;
    private double[] s_tree ;

    public Segment_tree(int array_num) {
        this.index_tree_num = (int) Math.pow(2 , array_num);
        this.s_tree = new double[this.index_tree_num] ;
        Lazy_tree l = new Lazy_tree(this.index_tree_num);
    }


    public void check_new_set (double low, double high) {}


    public void update (double low, double high, long amount) {
        int main_high = index_tree_num;
        int main_low = 0 ;

        if ()
    }
    public long query (double low, double high) {}





    public class Lazy_tree{
        private int[] tree ;

        public Lazy_tree(int index_num2) {
            this.tree = new int[index_num2];
        }
    }
}

















