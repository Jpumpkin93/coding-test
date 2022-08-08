package com.jpumpkin;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StringCodingTest {

    // 기준을 세운다
    // 어떤 변수가 변하면서 기준과 비교해야하는지 확인한다
    // 정답을 도출할때 어떤게 필요한가 생각해본다


    // 3. 문장_속_제일_긴_단어_찾기.
    @Test
    public String 문장_속_제일_긴_단어_찾기(String str) {
        String answer = "";
        String[] strArray = str.split(" ");

        for(String s: strArray) {
            if (s.length() > answer.length()) answer = s;
        }
        return answer;
    }

    // 4. 단어 뒤집기 (N개의 단어가 주어지면 각 단어 뒤집어 출력)
    @Test
    public ArrayList<String> 단어_뒤집기(int n, String[] str) {
        ArrayList<String> answer = new ArrayList<>();

        for (String s: str) {
            answer.add(new StringBuilder(s).reverse().toString());
        }

        return answer;
    }

    // 5. 특정 문자 뒤집기
    @Test
    public void 특정_문자_뒤집기() {
        String given = "a#b!GE*T@S";

        char[] givenCharArray = given.toCharArray();

        int leftIndex = 0, rightIndex = givenCharArray.length - 1;

        while (leftIndex < rightIndex) {
            if (!Character.isAlphabetic(givenCharArray[leftIndex])) leftIndex++;
            if (!Character.isAlphabetic(givenCharArray[rightIndex])) rightIndex--;
            else {
                char tmp = givenCharArray[leftIndex];
                givenCharArray[leftIndex] = givenCharArray[rightIndex];
                givenCharArray[rightIndex] = tmp;
                leftIndex++;
                rightIndex--;
            }
        }
        assertThat(String.valueOf(givenCharArray)).isEqualTo("S#T!EG*b@a");
    }

    // 6. 중복 문자 제거
    @Test
    public void 중복_문자_제거() {
        String answer = "";
        String given = "ksekkset";

        for(int i=0; i < given.length(); i++) {
            if (i == given.indexOf(given.charAt(i))) {
                answer += given.charAt(i);
            }
        }

        assertThat(answer).isEqualTo("kset");
    }

    // 7. 회문 문자열 (앞으로 뒤로 어느 방향으로 읽어도 같은 문자)
    @Test
    public void 회문_문자열() {
        String given = "gooG";
        String answer = "NO";

        String tmp = new StringBuilder(given).reverse().toString();

        if(given.equalsIgnoreCase(tmp)) {
            answer = "YES";
        }
        assertThat(answer).isEqualTo("YES");
    }

    // 8. 팰린드롬 (알파벳만 신경쓴다)
    @Test
    public void 팰린드롬() {
        String given = "found7, time: study; Yduts; emit, 7Dnuof";
        String answer = "NO";

        String s = given.toUpperCase().replaceAll("[^A-Z]", "");

        if (s.equals(new StringBuilder(s).reverse().toString())) answer = "YES";

        assertThat(answer).isEqualTo("YES");
    }

    // 9. 숫자만 추출하기
    @Test
    public void 숫자만_추출() {
        String given = "g0en2T0s8eSoft";
        StringBuilder answer = new StringBuilder();

        for (char x: given.toCharArray()) {
            if (Character.isDigit(x)) answer.append(x);
        }

        assertThat(Integer.parseInt(answer.toString())).isEqualTo(208);
    }

    // 10. 문자 간 최소거리 구하기
    @Test
    public void 문자_간_최소거리_구하기() {
        String given = "teachermode";
        char target = 'e';

        int instance = 1000;

        int[] answer = new int[given.length()];

        for(int i=0; i < given.length(); i++) {
            if (given.charAt(i) == target) {
                instance = 0;
            } else {
                instance++;
            }
            answer[i] = instance;
        }

        for(int i=given.length()-1; i >= 0; i--) {
            if (given.charAt(i) == target) {
                instance = 0;
            } else {
                instance++;
            }
            answer[i] = Math.min(answer[i], instance);
        }

        assertThat(Arrays.toString(answer).replaceAll("\\D","")).isEqualTo("10121012210");
    }

    @Test
    public void 문자열_압축() {
        String given = "KKHSSSSSSSE";
        String givenWithSpace = given + " ";
        String answer = "";

        int duplicate = 1;

        for(int i = 0; i<given.length(); i++) {
            if(givenWithSpace.charAt(i) == givenWithSpace.charAt(i+1)) duplicate ++;
            else {
                answer += givenWithSpace.charAt(i);
                if (duplicate > 1) answer += String.valueOf(duplicate);
                duplicate = 1;
            }
        }

        assertThat(answer).isEqualTo("K2HS7E");
    }

    @Test
    public void 암호() {
        String given = "#****###**#####**#####**##**";
        int alphabetCount = 4;

        String answer = "";

        for(int i = 0; i < alphabetCount; i ++) {
            String tmp = given
                        .substring(0, 7)
                        .replace("#", "1")
                        .replace("*", "0");
            answer += (char)Integer.parseInt(tmp, 2);

            given = given.substring(7);
        }

        assertThat(answer).isEqualTo("COOL");
    }
}