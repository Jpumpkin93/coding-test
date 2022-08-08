package com.jpumpkin;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapCodingTest {

    // 1. 학급 회장
    @Test
    public void 학급회장() {
        String given = "BACBACCACCBDEDE";
        char answer = ' ';
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (char x: given.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        for (char x: map.keySet()){
            if(map.get(x) > max ) {
                max = map.get(x);
                answer = x;
            }
        }

        assertThat(answer).isEqualTo('C');
    }

    // 2. 아나 그램
    @Test
    public void 아나그램() {
        String givenA = "AbaAeCe";
        String givenB = "baeeACA";

        String answer = "YES";

        HashMap<Character, Integer> map = new HashMap<>();

        for(char x: givenA.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        for(char x: givenB.toCharArray()) {
            if(!map.containsKey(x) || map.get(x) == 0) answer = "NO";
            map.put(x, map.get(x)-1);
        }

        assertThat(answer).isEqualTo("YES");
    }

    // 3. 매출액의 종류 n = 날짜, k = 연속된 k일 (구간)
    @Test
    public void 매출액의_종류() {
        int n = 7;
        int k = 4;
        int[] given = new int[] {20, 12, 20, 10, 23, 17, 10};

        // 시작점과 끝점을 기억하고 밀면서 나간다

        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < k-1; i++) {
            map.put(given[i], map.getOrDefault(given[i], 0) + 1);
        }
        int lt = 0;
        for(int rt= k-1; rt<n; rt++) {
            map.put(given[rt], map.getOrDefault(given[rt],0) + 1);
            answer.add(map.size());
            map.put(given[lt], map.get(given[lt])-1);
            if(map.get(given[lt])==0) map.remove(given[lt]);
            lt++;
        }

        assertThat(answer).isEqualTo(new ArrayList<>(Arrays.asList(3,4,4,3)));
    }

    // 4. 모든 아나그램 찾기
    @Test
    public void 모든_아나그램_찾기() {
        String S = "bacaAacba";
        String T = "abc";

        int answer = 0;
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();

        for (char x: T.toCharArray()) mapT.put(x, mapT.getOrDefault(x, 0) + 1);

        int L = T.length() - 1;

        for (int i=0; i<L; i++) mapS.put(S.charAt(i), mapS.getOrDefault(S.charAt(i),0) + 1);

        int lt = 0;
        for(int rt=L; rt<S.length(); rt++) {
            mapS.put(S.charAt(rt), mapS.getOrDefault(S.charAt(rt),0) + 1);
            if(mapS.equals(mapT)) answer++;
            mapS.put(S.charAt(lt), mapS.get(S.charAt(lt)) - 1);
            if(mapS.get(S.charAt(lt)) == 0) mapS.remove(S.charAt(lt));
            lt++;
        }

        assertThat(answer).isEqualTo(3);
    }

    // 5. k번째 큰 수
    @Test
    public void K번째_큰_수() {
        int[] given = new int[] {13, 15, 34, 23, 45, 65, 33, 11, 26, 42};
        int k = 3;

        int answer = 0;
        TreeSet<Integer> Tset = new TreeSet<>(Collections.reverseOrder());

        for(int i = 0; i < given.length; i++) {
            for(int j = i+1; j < given.length; j++){
                for(int l = j+1; l < given.length; l++){
                    Tset.add(given[i] + given[j] + given[l]);
                }
            }
        }
        int cnt = 0;
        for (int x: Tset) {
            cnt++;
            if(cnt==k) answer = x;
        }
        assertThat(answer).isEqualTo(143);
    }

}
