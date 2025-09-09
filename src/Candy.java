import java.util.Arrays;

public class Candy {

    public static int candy(int[] ratings) {

        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i + 1] + 1, candies[i]);
            }
        }
        int minCandies = 0;

        for (int candy : candies) {
            minCandies += candy;
        }

        return minCandies;
    }

    public static void main(String[] args) {
        int[] ratings = {1, 0, 2};
        int[] ratings1 = {1, 2, 2};
        System.out.println(candy(ratings));
        System.out.println(candy(ratings1));
    }
}
