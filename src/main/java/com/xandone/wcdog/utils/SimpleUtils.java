package com.xandone.wcdog.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleUtils {
    private final static ObjectMapper mapper = new ObjectMapper();

    public static List<String> toList(String data) {
        try {
            return mapper.readValue(data, new TypeReference<List<String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
