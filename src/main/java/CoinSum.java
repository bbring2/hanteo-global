public class CoinSum {

    public static int change(int sum, int[] coins) {
        int[] tmpArr = new int[sum+1];
        tmpArr[0] = 1;

        for(int coin : coins) {
            for(int i=coin; i<=sum; i++) {
                tmpArr[i] += tmpArr[i-coin];
            }
        }

        return tmpArr[sum];
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,2};
        System.out.println(change(4, arr));
    }

}
