package it.enricocandino.vigenere;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
public class Vigenere {

    private static final Logger LOGGER = LoggerFactory.getLogger(Vigenere.class);

    private static Random rand = new Random();
    private char[] alphabet;

    public Vigenere() {
        this(Alphabet.DEFAULT);
    }

    public Vigenere(char[] alphabet) {
        this.alphabet = Alphabet.clean(alphabet);
    }

    public Vigenere(char[]... alphabets) {
        this(Alphabet.merge(alphabets));
    }

    public String encrypt(String plaintext, String key) {
        return encrypt(plaintext, key, 0);
    }

    public String encrypt(String plaintext, String key, int saltSize) {
        String validKey = validateKey(key);

        if(plaintext == null || plaintext.length() == 0) {
            return "";
        }

        if(saltSize > 0) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<saltSize; i++) {
                sb.append(alphabet[rand.nextInt(alphabet.length)]);
            }
            String initVector = sb.toString();

            String firstPass = shift(plaintext, initVector, false);
            plaintext = initVector + firstPass;
        }

        return shift(plaintext, validKey, false);
    }

    public String decrypt(String encrypted, String key) {
        return decrypt(encrypted, key, 0);
    }

    public String decrypt(String encrypted, String key, int saltSize) {
        String validKey = validateKey(key);

        if(encrypted == null || encrypted.length() <= saltSize) {
            return "";
        }

        String decrypted = shift(encrypted, validKey, true);

        if(saltSize > 0) {
            String salt = decrypted.substring(0, saltSize);
            encrypted = decrypted.substring(saltSize);
            decrypted = shift(encrypted, salt, true);
        }

        return decrypted;
    }

    private String shift(String clear, String shifter, boolean backward) {

        if(shifter.length() == 0)
            return clear;

        while (shifter.length() < clear.length())
            shifter += shifter;
        shifter = shifter.substring(0, clear.length());

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<clear.length(); i++) {
            sb.append(shift(clear.charAt(i), shifter.charAt(i), backward));
        }
        return sb.toString();
    }

    private char shift(char c, char s, boolean backward) {
        int indexC = indexOf(c);
        int indexS = indexOf(s);

        if(indexC == -1) return c;
        if(indexS == -1) return s;

        int indexShifted;
        if(backward) {
            indexShifted = ((indexC - indexS) + alphabet.length) % alphabet.length;
        } else {
            indexShifted = (indexC + indexS) % alphabet.length;
        }

        return alphabet[indexShifted];
    }

    private int indexOf(char c) {
        int index = -1;
        for(int i=0; i<alphabet.length; i++) {
            if(c == alphabet[i]) {
                index = i;
                break;
            }
        }
        return index;
    }

    private String validateKey(String originalKey) {
        if(originalKey == null)
            return "";

        StringBuilder sb = new StringBuilder();
        for(char c : originalKey.toCharArray()) {
            if(indexOf(c) > -1)
                sb.append(c);
        }
        return sb.toString();
    }
}
