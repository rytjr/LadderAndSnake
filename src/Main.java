import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.IOException;

public class Main {

    static int N, M;
    static int[] isVisited = new int[101];
    static HashMap<Integer, Integer> arr = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Arrays.fill(isVisited, -1);

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr.put(a, b);
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Integer> queue = new LinkedList<>(); // BFS를 위해 Queue 사용
        queue.add(1);
        isVisited[1] = 0;

        while (!queue.isEmpty()) {
            int number = queue.poll();
            if (number == 100) {
                return isVisited[number];
            }

            for (int i = 1; i <= 6; i++) { // 주사위 굴림 (1~6) 확인
                int next = number + i;
                if (next <= 100 && isVisited[next] == -1) { // 범위 내에 있고 미방문한 경우만
                    if (arr.containsKey(next)) {
                        next = arr.get(next); // 사다리 또는 뱀이 있는 경우 이동
                    }

                    if (isVisited[next] == -1) { // 방문되지 않은 경우만 방문 처리
                        queue.add(next);
                        isVisited[next] = isVisited[number] + 1; // 이동 횟수 증가
                    }
                }
            }
        }
        return -1; // 100에 도달 불가 시 -1 반환
    }
}
