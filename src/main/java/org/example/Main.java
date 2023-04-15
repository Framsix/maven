package org.example;

import java.nio.file.*;
import java.util.HashSet;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        String str = "c:\\windows\\projects\\note.txt";
        Path path = Path.of(str).toAbsolutePath();
        System.out.println(path);
    }

}


class test {



}