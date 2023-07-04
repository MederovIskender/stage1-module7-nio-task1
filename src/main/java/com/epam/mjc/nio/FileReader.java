package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String[] profileData = new String[4];
        Path filepath = file.toPath();
       try(
           Stream<String> lines = Files.lines(filepath)){
           lines.forEach(line -> {
               String[] parts = line.split(":");
               if (parts.length == 2) {
                   String key = parts[0].trim();
                   String value = parts[1].trim();
                   if (key.equalsIgnoreCase("Name")) {
                       profileData[0] = value;
                   } else if (key.equalsIgnoreCase("Age")) {
                       profileData[1] = value;
                   } else if (key.equalsIgnoreCase("Email")) {
                       profileData[2] = value;
                   } else if (key.equalsIgnoreCase("Phone")) {
                       profileData[3] = value;
                   }
               }
           });
           return new Profile(profileData[0], Integer.parseInt(profileData[1]), profileData[2], Long.valueOf(profileData[3]));
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
    }


