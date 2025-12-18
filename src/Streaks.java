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
                if (devices[p1] == 0) {
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

    public String countAndSay(int n) {

        StringBuilder result = new StringBuilder("1");

        int currentNumber = 1;

        while (currentNumber < n) {
            result = rleGenerator(result);
            currentNumber++;
        }

        return result.toString();

    }

    public StringBuilder rleGenerator(StringBuilder s) {

        StringBuilder newString = new StringBuilder();

        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                count++;
            } else {
                newString.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }

        newString.append(count).append(s.charAt(s.length() - 1));

        return newString;
    }

    public int repeatedStringMatch(String a, String b) {
        int count = 0;
        StringBuilder string = new StringBuilder();

        while (string.length() < b.length()) {
            string.append(a);
            count++;
        }

        if (string.indexOf(b) != -1) return count;

        string.append(a);
        count++;

        if (string.indexOf(b) != -1) return count;

        return -1;
    }

    public static String shortestPalindrome(String s) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            StringBuilder newString = new StringBuilder();
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                newString.append(s.charAt(i)).append(s);
                sb = newString;
            }
        }

        return sb.toString();
    }

    public static int[] getSneakyNumbers(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        System.out.println(map);

        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            if (e.getValue() > 1) {
                list.add(e.getKey());
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public int countTriples(int n) {

        int total = 0;

        for (int i = 1; i <= n; i++) {
            int i_sq = i * i;
            for (int j = 1; j <= n; j++) {
                int j_sq = j * j;
                int a_sq = i_sq + j_sq;

                int a = (int) Math.sqrt(a_sq);

                if (a * a == a_sq && a <= n) {
                    total++;
                }
            }
        }

        return total;
    }

    public int specialTriplets(int[] nums) {

        int MOD = 1_000_000_007;

        Map<Integer, Long> suffixCount = new HashMap<>();
        Map<Integer, Long> prefixCount = new HashMap<>();

        for (int x : nums) {
            suffixCount.put(x, suffixCount.getOrDefault(x, 0L) + 1);
        }

        long totalTriplets = 0;

        for (int currentNum : nums) {

            suffixCount.put(currentNum, suffixCount.get(currentNum) - 1);

            int targetValue = currentNum * 2;

            long countI = prefixCount.getOrDefault(targetValue, 0L);
            long countK = suffixCount.getOrDefault(targetValue, 0L);

            long tripletsforJ = (countI * countK) % MOD;
            totalTriplets = (totalTriplets + tripletsforJ) % MOD;

            prefixCount.put(currentNum, prefixCount.getOrDefault(currentNum, 0L) + 1);
        }

        return (int) totalTriplets;
    }

    public int countPermutations(int[] complexity) {

        int MOD = 1_000_000_007;

        int totalPermutations = 1;

        for (int i = 1; i < complexity.length; i++) {
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
        }

        for (int i = 2; i < complexity.length; i++) {
            totalPermutations = (int) (((long) totalPermutations * i) % MOD);
        }

        return totalPermutations;
    }

    public int countCoveredBuildings(int n, int[][] buildings) {

        Set<Integer> buildingData = new HashSet<>();

        for (int[] b : buildings) {
            int x = b[0];
            int y = b[1];

            buildingData.add(x * n + y);
        }

        int totalBuildingsCovered = 0;

        for (int[] b : buildings) {

            int x = b[0];
            int y = b[1];

            boolean hasBelow = buildingData.contains((x - 1) * n + y);
            boolean hasAbove = buildingData.contains((x + 1) * n + y);
            boolean hasLeft = buildingData.contains(x * n + y - 1);
            boolean hasRight = buildingData.contains(x * n + y + 1);

            if (hasLeft && hasAbove && hasBelow && hasRight) {
                totalBuildingsCovered++;
            }
        }

        return totalBuildingsCovered;
    }

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {

        int[] offlineUsers = new int[numberOfUsers];
        int[] onlineTime = new int[numberOfUsers];

        int[] mentions = new int[numberOfUsers];

        for (int i = 0; i < events.size(); i++) {

            List<String> e = events.get(i);

            if (Objects.equals(e.getFirst(), "MESSAGE")) {

                for (int j = 0; j < offlineUsers.length; j++) {
                    if (Integer.parseInt(e.get(1)) >= onlineTime[j]) {
                        offlineUsers[j] = 0;
                    }
                }

                if (Objects.equals(e.getLast(), "ALL")) {
                    for (int j = 0; j < mentions.length; j++) {
                        mentions[j] += 1;
                    }
                } else if (Objects.equals(e.getLast(), "HERE")) {
                    for (int j = 0; j < mentions.length; j++) {
                        if (offlineUsers[j] == 0) {
                            mentions[j] += 1;
                        }
                    }
                } else {
                    String[] users = e.getLast().split(" ");
                    for (String user : users) {
                        int index = Integer.parseInt(user.substring(2));
                        mentions[index] += 1;
                    }
                }

            } else {
                offlineUsers[Integer.parseInt(e.getLast())] = 1;
                onlineTime[Integer.parseInt(e.getLast())] = Integer.parseInt(e.get(1)) + 60;
            }

        }

        return mentions;
    }

    public class Coupon {
        String code;
        String businessLine;
        int priority;

        Coupon(String code, String businessLine, int priority) {
            this.code = code;
            this.businessLine = businessLine;
            this.priority = priority;
        }
    }

    public int getPriority(String businessLine) {
        return switch (businessLine) {
            case "electronics" -> 0;
            case "grocery" -> 1;
            case "pharmacy" -> 2;
            case "restaurant" -> 3;
            default -> -1;
        };
    }

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {

        int n = code.length;
        List<Coupon> validCoupon = new ArrayList<>();

        Map<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("electronics", 0);
        priorityMap.put("grocery", 1);
        priorityMap.put("pharmacy", 2);
        priorityMap.put("restaurant", 2);

        for (int i = 0; i < n; i++) {
            if (code[i].matches("\\w+") && priorityMap.containsKey(businessLine[i]) && isActive[i]) {

                int priority = getPriority(businessLine[i]);
                if (priority == -1) continue;

                validCoupon.add(new Coupon(code[i], businessLine[i], priority));
            }
        }

        validCoupon.sort((a, b) -> {
            if (a.priority != b.priority) {
                return Integer.compare(a.priority, b.priority);
            }
            return a.code.compareTo(b.code);
        });

        List<String> result = new ArrayList<>();
        for (Coupon coupon : validCoupon) {
            result.add(coupon.code);
        }

        return result;
    }

    public int numberOfWays(String corridor) {

        long MOD = 1_000_000_007;

        List<Integer> indexOfSeat = new ArrayList<>();

        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S')
                indexOfSeat.add(i);
        }
        int n = indexOfSeat.size();

        if (indexOfSeat.isEmpty() || n % 2 != 0)
            return 0;

        long totalWays = 1;

        for (int i = 2; i < n; i += 2) {
            totalWays *= (indexOfSeat.get(i) - indexOfSeat.get(i - 1));
            totalWays %= MOD;
        }

        return (int) totalWays;
    }

    public long getDescentPeriods(int[] prices) {

        long result = 1;
        long consecutiveNumbers = 1;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                consecutiveNumbers++;
            } else {
                consecutiveNumbers = 1;
            }
            result += consecutiveNumbers;
        }

        return result;
    }

    //        Character[] transactionType = new Character[]{'n', 's'};
    public long maximumProfit(int[] prices, int k) {

        if (prices == null || prices.length < 2 || k == 0) {
            return 0;
        }

        int n = prices.length;
        long[] empty = new long[k + 1];
        long[] holdLong = new long[k + 1];
        long[] holdShort = new long[k + 1];

        for (int i = 0; i <= k; i++) {
            holdLong[i] = Long.MIN_VALUE / 2;
            holdShort[i] = Long.MIN_VALUE / 2;
        }

        for (int price : prices) {

            long[] nextEmpty = new long[k + 1];
            long[] nextHoldLong = new long[k + 1];
            long[] nextHoldShort = new long[k + 1];

            System.arraycopy(empty, 0, nextEmpty, 0, k + 1);
            System.arraycopy(holdLong, 0, nextHoldLong, 0, k + 1);
            System.arraycopy(holdShort, 0, nextHoldShort, 0, k + 1);

            for (int i = 1; i <= k; i++) {

                nextHoldLong[i] = Math.max(holdLong[i], empty[i - 1] - price);
                nextHoldShort[i] = Math.max(holdShort[i], empty[i - 1] + price);

                nextEmpty[i] = Math.max(nextEmpty[i], holdLong[i] + price);
                nextEmpty[i] = Math.max(nextEmpty[i], holdShort[i] - price);
            }
            empty = nextEmpty;
            holdLong = nextHoldLong;
            holdShort = nextHoldShort;
        }

        return empty[k];
    }


    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        int half = k / 2;

        long[] prefOriginal = new long[n + 1];
        long[] prefPrices = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefOriginal[i + 1] = prefOriginal[i] + (long) strategy[i] * prices[i];
            prefPrices[i + 1] = prefPrices[i] + (long) prices[i];
        }

        long maxTotalProfit = prefOriginal[n];

        for (int i = 0; i <= n - k; i++) {
            long beforeWindow = prefOriginal[i];

            int secondHalfStart = i + half;
            int secondHalfEnd = i + k;
            long windowSecondHalf = prefPrices[secondHalfEnd] - prefPrices[secondHalfStart];

            long afterWindow = prefOriginal[n] - prefOriginal[i + k];

            long currentTotal = beforeWindow + windowSecondHalf + afterWindow;
            maxTotalProfit = Math.max(maxTotalProfit, currentTotal);
        }

        return maxTotalProfit;
    }

    public static void main(String[] args) {
//
//        int[] nums = {2, 2, 3, 4}; // 3
//        int[] nums1 = {4, 2, 3, 4}; // 4
//        System.out.println(triangleNumber(nums));
//        System.out.println(triangleNumber(nums1));

//        System.out.println(maxBottlesDrunk(10, 3));
//
//            List<Integer> nums = new ArrayList<>();
//            nums.add(2);
//            nums.add(5);
//            nums.add(7);
//            nums.add(8);
//            nums.add(9);
//            nums.add(2);
//            nums.add(3);
//            nums.add(4);
//            nums.add(3);
//            nums.add(1);
//
//            System.out.println(hasIncreasingSubarrays(nums, 3));
//
//
//        String[] bank = {"011001", "000000", "010100", "001000"};
//
//        System.out.println(numberOfBeams(bank));
//
//
//        System.out.println(shortestPalindrome("abcd"));

        int[] nums = {0, 1, 1, 0};

        int[] result = getSneakyNumbers(nums);

        for (int j : result) {
            System.out.print(j + " ");
        }

    }

}
