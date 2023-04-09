package day8;

public class Result {
    /**
     * # 518 coin change ||
     * @param amount target coin
     * @param coins type of coins
     * @return combination
     */
    public static int change(int amount, int[] coins){
        int len = coins.length;
        int[][] val = new int[len][amount+1];
        for(int i = 0; i < len; i ++){
            val[i][0] = 1;
        }
        for(int i = 1; i <= amount; i ++)
            for(int j = len-1; j >= 0; j --){
                int total = 0;
                if (j+1 < len){
                    total += val[j+1][i];
                }
                if (i - coins[j] >= 0){
                    total += val[j][i-coins[j]];
                }
                val[j][i] = total;
            }
        return val[0][amount];
    }
    public static int changeHelper(int amount, int[] coins, int current, int[] val, int allow){
        if (current == amount) {
            val[current] = 1;
            return 1;
        }
        if (current > amount) return 0;
    //    if (val[current] != -1) return val[current];
        int total = 0;
        int len = coins.length;
        for (int i = allow; i < len; i ++) {
            if ((current + coins[i]) <= amount && val[current+i] != -1) total += val[current+coins[i]];
            else total += changeHelper(amount, coins, current + coins[i], val, allow);
            allow++;
        }
        val[current] = total;
        return total;

    }

    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1,2,5}));
    }
}
