public class quick_sort {
    public static int[] quick_sort(int[] array, int size)
    {
        int target = array[0];
        int[] smaller = new int[size];
        int smaller_next = 0;
        int[] larger = new int[size];
        int larger_next = 0;
        for (int i = 1; i < size; i ++)
        {
            if (array[i] <= target){
                smaller[smaller_next] = array[i];
                smaller_next ++;
            }
            else{
                larger[larger_next] = array[i];
                larger_next ++;
            }
        }
        int[] sorted = new int[size];
        int[] left_sorted = new int[smaller_next];
        int[] right_sorted = new int[larger_next];

        if (smaller_next != 0) {
            left_sorted = quick_sort(smaller, smaller_next);
        }
        if(larger_next != 0) {
            right_sorted = quick_sort(larger, larger_next);
        }
        int ind = 0;
        for (int i = 0; i < smaller_next; i++){
            sorted[ind] = left_sorted[i];
            ind++;
        }
        sorted[ind] = target;
        ind ++;
        for(int i = 0; i < larger_next; i ++){
            sorted[ind] = right_sorted[i];
            ind ++;
        }
        return sorted;

    }

    public static void main(String[] args) {
        int[] arr = {2,1,4,1,0};
        int[] res = quick_sort(arr, arr.length);
        for (int i : res)
        {
            System.out.println(i);
        }
    }
}
