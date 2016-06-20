# Vigenere

Simple implementation of the Vigenere cipher, with the possibility of adding a random salt to encrypt the same plaintext with the same key in a different way.

By default it uses only the basic alphabet (numbers [0-9] and normal chars, upper and lower case [a-z,A-Z]).
Every other character won't be encrypted, but you can provide your char[] alphabet.

Example:

```
String key = "MyPrivateKey";
String plaintext = "Hello";

Vigenere vigenere = new Vigenere();
String encrypted = vigenere.encrypt(plaintext, key);
String decrypted = vigenere.decrypt(encrypted, key);

// the encrypted text will be always the same -> "dcAcW"
```

If you want to randomize a bit more the encrypted text, specify a number of character to create a random string that will be used to create a salt.
This salt will be used to encrypt the plaintext and then appended to the encrypted text.
Then the encrypted text will be encrypted again with the secret key.

```
String key = "MyPrivateKey";
String plaintext = "Hello";

Vigenere vigenere = new Vigenere();
String encrypted1 = vigenere.encrypt(plaintext, key, 3);
String encrypted2 = vigenere.encrypt(plaintext, key, 3);

String decrypted1 = vigenere.decrypt(encrypted1, key, 3);
String decrypted2 = vigenere.decrypt(encrypted2, key, 3);

// the encrypted texts will be different
// encrypted1 -> "NwE9KVMf"
// encrypted2 -> "5AjrY04t"
```

As stated above the cipher is going to use a simple alphabet, but you can provide a different one if needed.
You can so create a Vigenere class providing your own alphabet, or extending the default one.

```
char[] punctuation = new char[] { ',', '.' };
Vigenere vigenere = new Vigenere(Alphabet.DEFAULT, punctuation);
```

Remember to provide the same alphabet, key and salt size to correctly encrypt/decrypt the text.

The Alphabet class provides the three different charset used and a utility method to merge them.
You can use just one of it, mix two of them or merge other custom charsets.

As you can see the `Alphabet.DEFAULT` is just a merge of the three:

```
public static char[] DEFAULT = Alphabet.merge(
    Alphabet.NUMERIC,
    Alphabet.UPPER_CASE,
    Alphabet.LOWER_CASE
);
```


Developed By
--------

Enrico Candino - www.enricocandino.it

<a href="https://twitter.com/enrichmann">
  <img alt="Follow me on Twitter"
       src="http://icons.iconarchive.com/icons/danleech/simple/96/twitter-icon.png" />
</a>
<a href="https://plus.google.com/+EnricoCandino">
  <img alt="Follow me on Google+"
       src="http://icons.iconarchive.com/icons/danleech/simple/96/google-plus-icon.png" />
</a>
<a href="https://it.linkedin.com/in/enrico-candino-78995553">
  <img alt="Follow me on LinkedIn"
       src="http://icons.iconarchive.com/icons/danleech/simple/96/linkedin-icon.png" />
</a>


License
--------

    The MIT License (MIT)
    
    Copyright (c) 2015 Enrico Candino
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

