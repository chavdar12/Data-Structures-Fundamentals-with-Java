package bg.softuni.solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {

        Queue<Integer> cookies = new PriorityQueue<>();

        for (int cookie : cookiesSweetness) {
            cookies.add(cookie);
        }

        int operations = 0;

        while (cookies.peek() < requiredSweetness) {
            if (cookies.size() < 2) {
                return -1;
            }

            int first = cookies.poll();
            int second = cookies.poll();

            int newCookie = first + 2 * second;
            cookies.add(newCookie);

            operations++;
        }

        return operations;
    }
}
