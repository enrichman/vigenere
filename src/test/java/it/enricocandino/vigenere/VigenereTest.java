package it.enricocandino.vigenere;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
public class VigenereTest extends TestCase {

    public void testEncryptDecrypt() {
        String key = "MyPrivateKey";
        String plaintext = "Hello";

        Vigenere vigenere = new Vigenere(Alphabet.DEFAULT);
        String encrypted = vigenere.encrypt(plaintext, key);
        String decrypted = vigenere.decrypt(encrypted, key);

        assertNotSame(plaintext, encrypted);
        assertEquals(plaintext, decrypted);
    }

    public void testEncryptDecryptAgain() {
        String key = "MyPrivateKey";
        String plaintext = "Hello";

        Vigenere vigenere = new Vigenere();
        String encrypted1 = vigenere.encrypt(plaintext, key);
        String encrypted2 = vigenere.encrypt(plaintext, key);

        String decrypted1 = vigenere.decrypt(encrypted1, key);
        String decrypted2 = vigenere.decrypt(encrypted2, key);

        assertEquals(encrypted1, encrypted2);
        assertEquals(decrypted1, decrypted2);
        assertNotSame(plaintext, encrypted1);
        assertEquals(plaintext, decrypted1);
        assertEquals(plaintext, decrypted2);
    }

    public void testEncryptDecryptWithSalt() {
        String key = "MyPrivateKey";
        String plaintext = "Hello";

        Vigenere vigenere = new Vigenere();
        String encrypted1 = vigenere.encrypt(plaintext, key, 3);
        String encrypted2 = vigenere.encrypt(plaintext, key, 3);

        String decrypted1 = vigenere.decrypt(encrypted1, key, 3);
        String decrypted2 = vigenere.decrypt(encrypted2, key, 3);

        assertNotSame(encrypted1, encrypted2);
        assertEquals(decrypted1, decrypted2);
        assertNotSame(plaintext, encrypted1);
        assertEquals(plaintext, decrypted1);
        assertEquals(plaintext, decrypted2);
    }

    public void testEncryptDecryptWithCustomAlphabet() {
        String key = "MyPrivateKey";
        String plaintext = "Hello...";

        char[] punctuation = new char[] { ',', '.' };
        Vigenere vigenere = new Vigenere(Alphabet.DEFAULT, punctuation);

        String encrypted = vigenere.encrypt(plaintext, key);
        String decrypted = vigenere.decrypt(encrypted, key);

        assertNotSame(plaintext, encrypted);
        assertEquals(plaintext, decrypted);
    }

    public void testEncryptDecryptWithWrongKey() {
        String key = "MyPrivateKey";
        String plaintext = "HELLO";

        Vigenere vigenere = new Vigenere(Alphabet.NUMERIC, Alphabet.UPPER_CASE);

        String encrypted = vigenere.encrypt(plaintext, key);
        String decrypted = vigenere.decrypt(encrypted, key);

        assertNotSame(plaintext, encrypted);
        assertEquals(plaintext, decrypted);
    }

    public void testEncryptDecryptWithEmptyKey() {
        String key = "";
        String plaintext = "HELLO";

        Vigenere vigenere = new Vigenere(Alphabet.NUMERIC, Alphabet.UPPER_CASE);

        String encrypted = vigenere.encrypt(plaintext, key);
        String decrypted = vigenere.decrypt(encrypted, key);

        assertEquals(plaintext, encrypted);
        assertEquals(plaintext, decrypted);
    }

    public void testEncryptDecryptWithNullKey() {
        String plaintext = "HELLO";

        Vigenere vigenere = new Vigenere(Alphabet.NUMERIC, Alphabet.UPPER_CASE);

        String encrypted = vigenere.encrypt(plaintext, null);
        String decrypted = vigenere.decrypt(encrypted, null);

        assertEquals(plaintext, encrypted);
        assertEquals(plaintext, decrypted);
    }

    public void testEmptyDecrypt() {
        String key = "KLMMSL";

        Vigenere vigenere = new Vigenere(Alphabet.NUMERIC, Alphabet.UPPER_CASE);
        String decrypted = vigenere.decrypt("", key, 3);

        assertEquals("", decrypted);
    }

}
