package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> results = new ArrayList<>();
        results.add(source);
        for (String delimiter : delimiters) {
            List<String> current = new ArrayList<>();
            for (String res : results) {
                String[] splits = res.split(delimiter);
                for (String s : splits) {
                    if (!s.isEmpty()) {
                        current.add(s);
                    }
                }
            }
            results = current;
        }
        return results;
    }
}
