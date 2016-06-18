package it.enricocandino.vigenere;

import junit.framework.TestCase;

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
        char[] extendedAlphabet = Alphabet.merge(Alphabet.DEFAULT, punctuation);

        Vigenere vigenere = new Vigenere(extendedAlphabet);

        String encrypted = vigenere.encrypt(plaintext, key);
        String decrypted = vigenere.decrypt(encrypted, key);

        assertNotSame(plaintext, encrypted);
        assertEquals(plaintext, decrypted);
    }

}
