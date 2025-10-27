import java.util.*;

public class Streaks {

    /*
     * Handle the sign separately.
     * Result is negative if one of numerator, denominator is negative.
     * Work with absolute values (cast to long to avoid overflow).
     * Compute the integer part using division.
     * If perfectly divisible → return integer as string.
     * Otherwise, process the fractional part:
     * Use a map to store each remainder and the index where its quotient digit was placed in the string.
     * Multiply remainder by 10 each step, append quotient digit.
     * If the same remainder repeats → we found a cycle. Insert "(" at stored index, append ")" at the end.
     * Return the constructed string.
     * */

    public String fractionToDecimal(int numerator, int denominator) {

        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        if ((numerator < 0) ^ (denominator < 0)) result.append("-");

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        result.append(num / den);
        long remainder = num % den;
        if (remainder == 0) return result.toString();

        result.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {

            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                result.insert(index, "(");
                result.append(")");
                return result.toString();
            }
            map.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }
        return result.toString();
    }

    public int minimumTotal(List<List<Integer>> triangle) {

        for (int row = triangle.size() - 2; row >= 0; row--) {

            List<Integer> currentRow = triangle.get(row);

            List<Integer> belowRow = triangle.get(row + 1);

            for (int i = 0; i < currentRow.size(); i++) {

                currentRow.set(i, currentRow.get(i) + Math.min(belowRow.get(i), belowRow.get(i + 1)));
            }
        }

        return triangle.getFirst().getFirst();
    }

    public static int triangleNumber(int[] nums) {

        Arrays.sort(nums);
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            int left = 0;
            int right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    total += right - left;
                    right--;
                } else {
                    left++;
                }
            }

        }

        return total;
    }

    public double largestTriangleArea(int[][] points) {

        double maxArea = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                for (int k = 0; k < points.length; k++) {
                    double a = dist(points[i], points[j]);
                    double b = dist(points[j], points[k]);
                    double c = dist(points[k], points[i]);

                    double s = (a + b + c) / 2;

                    double areaSquare = s * (s - a) * (s - b) * (s - c);

                    areaSquare = Math.max(0.0, areaSquare);

                    double currentArea = Math.sqrt(areaSquare);

                    maxArea = Math.max(currentArea, maxArea);
                }
            }
        }


        return maxArea;
    }

    public double dist(int[] pt1, int[] pt2) {

        double x = pt1[0] - pt2[0];
        double y = pt1[1] - pt2[1];

        return Math.sqrt(x * x + y * y);
    }

    public int largestPerimeter(int[] nums) {

        Arrays.sort(nums);

        for (int i = nums.length - 3; i >= 0; --i) {
            if (nums[i] + nums[i + 1] > nums[i + 2]) {
                return nums[i] + nums[i + 1] + nums[i + 2];
            }
        }
        return 0;
    }

    public int minScoreTriangulation(int[] values) {
        int vertexCount = values.length;
        int[][] minScore = new int[vertexCount][vertexCount];

        for (int gap = 2; gap < vertexCount; gap++) {
            for (int start = 0; start + gap < vertexCount; start++) {
                int end = start + gap;
                int currentMinScore = Integer.MAX_VALUE;
                for (int mid = start + 1; mid < end; mid++) {
                    int triangleScore = minScore[start][mid] + minScore[mid][end] + values[start] * values[mid] * values[end];
                    currentMinScore = Math.min(currentMinScore, triangleScore);
                }
                minScore[start][end] = currentMinScore;
            }
        }
        return minScore[0][vertexCount - 1];
    }

    public int triangularSum(int[] nums) {
        return process(nums);
    }

    public int process(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] newNums = new int[n - 1];

        for (int i = 0; i < n - 1; i++) {
            newNums[i] = (nums[i] + nums[i + 1]) % 10;
        }

        return process(newNums);
    }

    public static int numWaterBottles(int numBottles, int numExchange) {
        int drink = numBottles;
        int empty = numBottles;
        while (empty >= numExchange) {
            int newBottles = empty / numExchange;
            drink += newBottles;
            empty = newBottles + (empty % numExchange);
        }
        return drink;
    }

    public static int maxBottlesDrunk(int numBottles, int numExchange) {

        int drink = numBottles;
        while (numBottles >= numExchange) {
            numBottles -= numExchange - 1;
            numExchange++;
            drink++;
        }
        return drink;

    }

    public int trapRainWater(int[][] heightMap) {

        PriorityQueue<int[]> walls = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        int rows = heightMap.length;
        int cols = heightMap[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                    walls.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }

        int totalTrappedWater = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!walls.isEmpty()) {

            int[] currentWall = walls.poll();

            for (int[] direction : directions) {

                int newRow = currentWall[0] + direction[0];
                int newCol = currentWall[1] + direction[1];

                if (newRow > 0 && newRow < rows - 1 && newCol > 0 && newCol < cols - 1 && !visited[newRow][newCol]) {

                    int trappedWater = Math.max(0, currentWall[2] - heightMap[newRow][newCol]);
                    totalTrappedWater += trappedWater;

                    visited[newRow][newCol] = true;

                    walls.offer(new int[]{newRow, newCol, Math.max(currentWall[2], heightMap[newRow][newCol])});
                }
            }
        }

        return totalTrappedWater;
    }

    public int maxArea(int[] height) {

        int maxArea = 0;

        int l = 0, r = height.length - 1;

        while (l < r) {
            int currentArea = Math.abs(l - r) * Math.min(height[l], height[r]);

            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }

            maxArea = Math.max(maxArea, currentArea);
        }

        return maxArea;
    }


    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            visitHelper(heights, pacific, i, 0, heights[i][0]);
            visitHelper(heights, atlantic, i, cols - 1, heights[i][cols - 1]);
        }

        for (int j = 0; j < cols; j++) {
            visitHelper(heights, pacific, 0, j, heights[0][j]);
            visitHelper(heights, atlantic, rows - 1, j, heights[rows - 1][j]);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    public void visitHelper(int[][] heights, boolean[][] visited, int r, int c, int prevHeight) {
        int rows = heights.length;
        int cols = heights[0].length;

        if (r < 0 || c < 0 || r >= rows || c >= cols) return;
        if (visited[r][c]) return;
        if (heights[r][c] < prevHeight) return;

        visited[r][c] = true;

        visitHelper(heights, visited, r + 1, c, heights[r][c]);
        visitHelper(heights, visited, r - 1, c, heights[r][c]);
        visitHelper(heights, visited, r, c + 1, heights[r][c]);
        visitHelper(heights, visited, r, c - 1, heights[r][c]);
    }

    public int swimInWater(int[][] grid) {

        int n = grid.length;

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[n][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[]{grid[0][0], 0, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int time = current[0], r = current[1], c = current[2];

            if (r == n - 1 && c == n - 1) return time;

            if (visited[r][c]) continue;
            visited[r][c] = true;

            for (int[] dir : directions) {

                int nr = r + dir[0], nc = c + dir[1];

                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    pq.offer(new int[]{Math.max(time, grid[nr][nc]), nr, nc});
                }
            }
        }
        return -1;
    }

    public int[] avoidFlood(int[] rains) {

        HashMap<Integer, Integer> lakeData = new HashMap<>();
        TreeSet<Integer> dryDays = new TreeSet<>();

        int[] result = new int[rains.length];
        Arrays.fill(result, 1);

        for (int i = 0; i < rains.length; i++) {
            int lake = rains[i];

            if (lake == 0) {
                dryDays.add(i);
            } else {
                result[i] = -1;
                if (lakeData.containsKey(lake)) {
                    int lastRainDay = lakeData.get(lake);

                    Integer dryDayToUse = dryDays.ceiling(lastRainDay);

                    if (dryDayToUse == null) return new int[0];
                    result[dryDayToUse] = lake;
                    dryDays.remove(dryDayToUse);
                }
                lakeData.put(lake, i);
            }
        }

        for (int day : dryDays) result[day] = 1;

        return result;
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        int[] result = new int[spells.length];
        Arrays.sort(potions);

        for (int i = 0; i < spells.length; i++) {
            int start = 0;
            int end = potions.length - 1;

            while (start <= end) {
                int mid = start + (end - start) / 2;

                if ((long) potions[mid] * spells[i] >= success) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            result[i] = potions.length - start;
        }
        return result;
    }

    public long minTime(int[] skill, int[] mana) {

        int[] freeTime = new int[skill.length];

        int now = freeTime[0];

        for (int i = 0; i < mana.length; i++) {
            for (int j = 1; j < skill.length; j++) {
                now = Math.max(now + skill[j - 1] * i, freeTime[j]);
            }
            freeTime[skill.length - 1] = now + skill[skill.length - 1] * i;

            for (int j = 0; j < skill.length - 1; j++) {
                freeTime[j] = freeTime[j + 1] - skill[j + 1] * i;
            }
        }

        return now;
    }

    public int maximumEnergy(int[] energy, int k) {
        int maxEnergy = Integer.MIN_VALUE;
        int n = energy.length;

        for (int i = n - 1; i >= 0; i--) {
            if (i + k < n) {
                energy[i] += energy[i + k];
            }
            maxEnergy = Math.max(maxEnergy, energy[i]);
        }

        return maxEnergy;
    }

    public long maximumTotalDamage(int[] power) {
        if (power == null || power.length == 0) {
            return 0;
        }

        Map<Integer, Integer> counts = new HashMap<>();
        for (int p : power) {
            counts.put(p, counts.getOrDefault(p, 0) + 1);
        }

        List<Integer> uniquePowers = new ArrayList<>(counts.keySet());
        Collections.sort(uniquePowers);

        int n = uniquePowers.size();
        if (n == 0) {
            return 0;
        }

        long[] dp = new long[n];
        dp[0] = (long) uniquePowers.get(0) * counts.get(uniquePowers.get(0));

        for (int i = 1; i < n; i++) {
            int currentPower = uniquePowers.get(i);

            long skipDamage = dp[i - 1];

            long currentDamage = (long) currentPower * counts.get(currentPower);

            long prevDamage = 0;
            int k = i - 1;
            while (k >= 0 && uniquePowers.get(k) >= currentPower - 2) {
                k--;
            }
            if (k >= 0) {
                prevDamage = dp[k];
            }
            long takeDamage = currentDamage + prevDamage;

            dp[i] = Math.max(skipDamage, takeDamage);
        }

        return dp[n - 1];
    }


    // public List<String> removeAnagrams(String[] words) {

    //     List<String> result = new ArrayList<>();

    //     for (int i = 1; i < words.length; i++) {
    //         if (isNotAnagram(words, i)) {
    //             result.add(words[i]);
    //         }
    //     }

    //     return result;
    // }

    // public boolean isNotAnagram(String[] words, int i) {


    // }


    public boolean canBeIncreasing(int[] nums, int k) {
        int n = nums.length;

        if (n < 2 * k) return false;

        for (int a = 0; a <= n - 2 * k; a++) {
            boolean firstIsIncreasing = isStrictlyIncreasing(nums, a, k);
            if (firstIsIncreasing) {
                boolean secondIsIncreasing = isStrictlyIncreasing(nums, a + k, k);
                if (secondIsIncreasing) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isStrictlyIncreasing(int[] nums, int start, int length) {
        if (length <= 1) {
            return true;
        }

        for (int i = start; i < start + length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static int numberOfBeams(String[] bank) {

        int totalBeams = 0;

        int[] devices = new int[bank.length];

        for (int i = 0; i < bank.length; i++) {
            int currentDevices = 0;
            for (int j = 0; j < bank[i].length(); j++) {
                currentDevices += Integer.parseInt(String.valueOf(bank[i].charAt(j)));
            }
            devices[i] = currentDevices;
        }

        int p1 = 0;
        int p2;

        for (p2 = 1; p2 < devices.length; p2++) {
            while (p1 < p2) {
                if (devices[p1] == 0){
                    p1++;
                    continue;
                }
                if (devices[p2] == 0) {
                    break;
                }
                totalBeams += devices[p1] * devices[p2];
                p1++;
            }
        }

        return totalBeams;
    }

    public static void main(String[] args) {
//
//        int[] nums = {2, 2, 3, 4}; // 3
//        int[] nums1 = {4, 2, 3, 4}; // 4
//        System.out.println(triangleNumber(nums));
//        System.out.println(triangleNumber(nums1));

//        System.out.println(maxBottlesDrunk(10, 3));
//
        //    List<Integer> nums = new ArrayList<>();
        //    nums.add(2);
        //    nums.add(5);
        //    nums.add(7);
        //    nums.add(8);
        //    nums.add(9);
        //    nums.add(2);
        //    nums.add(3);
        //    nums.add(4);
        //    nums.add(3);
        //    nums.add(1);

        //    System.out.println(hasIncreasingSubarrays(nums, 3));


        String[] bank = {"011001", "000000", "010100", "001000"};

        System.out.println(numberOfBeams(bank));

    }

}
