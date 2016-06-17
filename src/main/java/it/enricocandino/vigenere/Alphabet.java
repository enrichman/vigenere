package it.enricocandino.vigenere;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
public class Alphabet {

    public static char[] NUMERIC = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static char[] UPPER_CASE = new char[] {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static char[] LOWER_CASE = new char[] {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static char[] DEFAULT = Alphabet.merge(
        Alphabet.NUMERIC,
        Alphabet.UPPER_CASE,
        Alphabet.LOWER_CASE
    );

    public static char[] merge(char[]... alphabetArrays) {
        int totalSize = 0;
        for(char[] alphabet : alphabetArrays) {
            totalSize += alphabet.length;
        }
        char[] merged = new char[totalSize];

        int i=0;
        for(char[] alphabet : alphabetArrays) {
            for(char c : alphabet) {
                merged[i] = c;
                i++;
            }
        }

        return merged;
    }

    public static char[] clean(char[] alphabet) {
        Set<Character> set = new HashSet<Character>();
        for(Character c : alphabet){
            set.add(c);
        }

        char[] cleaned = new char[set.size()];
        int i = 0;
        for(Character c : set) {
            cleaned[i] = c;
            i++;
        }

        Arrays.sort(cleaned);

        return cleaned;
    }

}
