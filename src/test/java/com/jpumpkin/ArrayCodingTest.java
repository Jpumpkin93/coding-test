package com.jpumpkin;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayCodingTest {


    // 1. 큰 수 출력하기
    @Test
    public void 큰_수_출력하기() {
        int[] given = new int[] {7, 3, 9, 5, 6, 12};

        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(given[0]);

        for(int i = 1; i < given.length; i++) {
            if(given[i] > given[i-1]) answer.add(given[i]);
        }
        assertThat(answer).isEqualTo(new ArrayList<>(Arrays.asList(7, 9, 6, 12)));
    }

    // 2. 보이는 학생
    @Test
    public void 보이는_학생() {
        int[] given = new int[] {130, 135, 148, 140, 145, 150, 150, 153};
        int highest = 0;
        int answer = 0;

        for (int j : given) {
            if (j > highest) {
                highest = j;
                answer++;
            }
        }
        assertThat(answer).isEqualTo(5);
    }

    // 3. 가위 바위 보 (1 -> 가위, 2 -> 바위, 3 -> 보)
    @Test
    public void 가위_바위_보() {
        int[] aGiven = new int[] {2, 3, 3, 1, 3};
        int[] bGiven = new int[] {1, 1, 2, 2, 3};
        ArrayList<String> answer = new ArrayList();

        for (int i = 0; i<aGiven.length; i++) {
            if (aGiven[i] == bGiven[i]) answer.add("D");
            else if (aGiven[i] == 1 && bGiven[i] == 3) { answer.add("A"); }
            else if (aGiven[i] == 2 && bGiven[i] == 1) { answer.add("A"); }
            else if (aGiven[i] == 3 && bGiven[i] == 2) { answer.add("A"); }
            else answer.add("B");
        }

        assertThat(answer).isEqualTo(new ArrayList<>(Arrays.asList("A", "B", "A", "B", "D")));
    }

    // 4. 피보나치 수열
    @Test
    public void 피보나치_수열() {
        int given = 10;
        int[] answer = new int[10];
        answer[0]= 1;
        answer[1]= 1;
        for (int i= 2; i < given; i++) {
            answer[i] = answer[i-2] + answer[i - 1];
        }

        assertThat(answer).isEqualTo(new int[] {1, 1, 2, 3, 5, 8, 13, 21, 34, 55});
    }

    // 5. 소수 구하기 (에라토스테네스 체)
    @Test
    public void 소수_구하기() {
        int given = 20;
        int[] givenArray = new int[given+1];
        int answer = 0;

        for (int i = 2; i < given; i++) {
            if (givenArray[i] == 0) {
                answer++;
                givenArray[i] = 1;
                for (int j = i; j < given; j=j+i) givenArray[j] = 1;
            }
        }

        assertThat(answer).isEqualTo(8);
    }

    // 6. 뒤집은 소수
    @Test
    public void 뒤집은_소수() {
        int givenCount = 9;
        int[] given = new int[] {32, 55, 62, 20, 250, 370, 200, 30, 100};

        ArrayList<Integer> answer = new ArrayList<>();
        for(int i = 0; i < givenCount; i++) {
            int tmp = given[i];
            int res = 0;
            // 숫자를 뒤집는다
            while(tmp > 0) {
                int t = tmp % 10;
                res = res * 10 + t;
                tmp = tmp / 10;
            }
            if(isPrime(res)) answer.add(res);
        }
        assertThat(answer).isEqualTo(new ArrayList(Arrays.asList(23, 2, 73, 2, 3)));
    }

    private boolean isPrime(int num) {
        if(num ==1) return false;
        for(int i=2; i<num; i++) {
            if(num%i==0) return false;
        }
        return true;
    }


    // 7. 점수 계산
    @Test
    public void 점수_계산() {
        int givenCount = 10;
        int[] given = new int[] {1, 0, 1, 1, 1, 0, 0, 1, 1, 0};
        int answer = 0;
        int count = 0;

        for(int i =0; i < givenCount; i++) {
            if(given[i] == 1) {
                count++;
                answer += count;
            }
            else count = 0;
        }
        assertThat(answer).isEqualTo(10);
    }

    // 8. 등수 구하기
    @Test
    public void 등수_구하기() {
        int[] given = new int[] {87, 89, 92, 100, 76};
        int[] answer = new int[given.length];

        for(int i = 0; i < given.length; i++) {
            int rank = 1;
            for (int j = 0; j < given.length; j++){
                if(given[i] < given[j]) {
                    rank++;
                }
            }
            answer[i] = rank;
        }
        assertThat(answer).isEqualTo(new int[] {4, 3, 2, 1, 5});
    }

    // 9. 격자판 최대합
    @Test
    public void 격자판_최대합() {
        int answer = Integer.MIN_VALUE;
        int n = 5;
        int[][] given = new int[][] {
                {10, 13, 10, 12, 15},
                {12, 39, 30, 23, 11},
                {11, 25, 50, 53, 15},
                {19, 27, 29, 37, 27},
                {19, 13, 30, 13, 19}
        };
        int sum1, sum2;

        for(int i=0; i<n; i++) {
            sum1=sum2=0;
            for(int j =0; j<n; j++) {
                sum1+=given[i][j];
                sum2+=given[j][i];
            }
            answer= Math.max(answer, sum1);
            answer= Math.max(answer, sum2);
        }
        sum1=sum2=0;
        for(int i=0; i<n; i++) {
            sum1+=given[i][i];
            sum2+=given[i][n-i-1];
        }
        answer= Math.max(answer, sum1);
        answer= Math.max(answer, sum2);

        assertThat(answer).isEqualTo(155);
    }

    // 10. 봉우리 구하기 (상하좌우 숫자보다 큰 숫자는 봉우리 지역)
    @Test
    public void 봉우리_구하기() {
        int n = 5;
        int[][] given = new int[][] {
                {5, 3, 7, 2, 3},
                {3, 7, 1, 6, 1},
                {7, 2, 5, 3, 4},
                {4, 3, 6, 4, 1},
                {8, 7, 3, 5, 2}
        };

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int answer = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                boolean flag = true;
                for(int k=0; k<4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx>=0 && nx<n && ny>=0 && ny <n && given[nx][ny] >= given[i][j]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) answer++;
            }
        }
        assertThat(answer).isEqualTo(10);
    }

    // 11. 반장 선거 하기
    @Test
    public void 반장선거() {
        int n = 5;
        int[][] given = new int[][] {
                {2, 3, 1, 7, 3},
                {4, 1, 9, 6, 8},
                {5, 5, 2, 4, 4},
                {6, 5, 2, 6, 7},
                {8, 4, 2, 2, 2}
        };

        int answer = 0;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            int count = 0;
            for(int j=0; j<n; j++) {
                for(int k = 0; k < 5; k++) {
                    if(given[i][k] == given[j][k]) {
                        count++;
                        break;
                    }
                }
            }
            if(count > max) {
                max = count;
                answer = i+1;
            }
        }

        assertThat(answer).isEqualTo(4);
    }
}
