package br.com.vacine_se.utils;

import java.util.UUID;

public class IdGenerator {
    public static String getHash() {
        return UUID.randomUUID().toString();
    }
}
