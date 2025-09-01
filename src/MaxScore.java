public class MaxScore {

    public static int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int totalSum = 0;

        // Step 1: Calculate the total sum of all cards.
        for (int point : cardPoints) {
            totalSum += point;
        }

        // If k is equal to the number of cards, we must take all of them.
        if (k == n) {
            return totalSum;
        }

        // Step 2: Find the minimum sum of the subarray of size (n-k) to be left behind.
        int windowSize = n - k;
        int currentWindowSum = 0;

        // Initialize the sum for the first window.
        for (int i = 0; i < windowSize; i++) {
            currentWindowSum += cardPoints[i];
        }

        int minSubarraySum = currentWindowSum;

        // Slide the window across the rest of the array.
        for (int i = windowSize; i < n; i++) {
            // Update the window sum by adding the new element and removing the old one.
            currentWindowSum = currentWindowSum + cardPoints[i] - cardPoints[i - windowSize];
            minSubarraySum = Math.min(minSubarraySum, currentWindowSum);
        }

        // Step 3: The maximum score is the total sum minus the sum of the cards left behind.
        return totalSum - minSubarraySum;
    }

    public static void main(String[] args) {

        int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        System.out.println(maxScore(cardPoints, 3));

    }
}
