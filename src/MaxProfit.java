import java.util.Arrays;

public class MaxProfit {
    public static int maxProfit(int[] prices) {

//        int buyPrice = Integer.MAX_VALUE;
//        int sellPrice = Integer.MIN_VALUE;
//        for (int i = 0; i < prices.length; i++) {
//            int currentBuyPrice = prices[i];
//            buyPrice = Math.min(currentBuyPrice, buyPrice);
//        }
//        for (int i = findIndexInArray(prices, buyPrice); i < prices.length; i++) {
//            int currentSellPrice = prices[i];
//            sellPrice = Math.max(currentSellPrice, sellPrice);
//        }
//        return sellPrice - buyPrice;

        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;

    }


    public static int findIndexInArray(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }
}
