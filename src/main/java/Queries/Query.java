package Queries;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.util.Pair;

public class Query extends Container {

    private String name;
    private Map<String, String> values;

    Query(String inputLine) {
        name = Parser.parseName(inputLine);
        values = Collections.unmodifiableMap(Parser.parseValues(inputLine));
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getInteger(String name) {
        try {
            return Integer.parseInt(values.get(name));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getString(String name) {
        return values.get(name);
    }

    @Override
    public Double getDouble(String name) {
        try {
            return Double.parseDouble(values.get(name));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

class Parser {

    private static final String SEPARATOR = "|";
    private static final String OPEN_TOKEN = "{";
    private static final String CLOSE_TOKEN = "}";

    static String parseName(String inputLine) {
        if (inputLine == null) {
            throw new IllegalArgumentException("Input value is null");
        }
        int endOfName = inputLine.indexOf(SEPARATOR);
        if (endOfName == -1) {
            throw new IllegalArgumentException("There isn't a separator in inputLine");
        }
        String name = inputLine.substring(0, endOfName).trim();
        if (name.length() == 0) {
            throw new IllegalArgumentException("Empty name of the query");
        }
        return name;
    }

    static Map<String, String> parseValues(String inputLine) {
        Map<String, String> resultMap = new HashMap<>();

        int endOfName = inputLine.indexOf(SEPARATOR);
        if (endOfName == -1) {
            throw new IllegalArgumentException("There isn't a separator in inputLine");
        }

        Iterator<Pair<String, String>> iterator = new Itr(inputLine.substring(endOfName + 1));

        while (iterator.hasNext()) {
            Pair<String, String> pair = iterator.next();
            resultMap.put(pair.getKey(), pair.getValue());
        }
        return resultMap;
    }

    private static class Itr implements Iterator<Pair<String, String>> {

        private String line;
        private int pointer;

        public Itr(String line) {
            this.line = line;
        }

        @Override
        public boolean hasNext() {
            return pointer < line.length();
        }

        @Override
        public Pair<String, String> next() {
            int endOfName = line.indexOf(OPEN_TOKEN, pointer);
            if (endOfName == -1) {
                throw new IllegalArgumentException("Doesn't exist open token");
            }

            int endOfCount = line.indexOf(CLOSE_TOKEN, endOfName);
            if (endOfCount == -1) {
                throw new IllegalArgumentException("Doesn't exist close token");
            }

            String name = line.substring(pointer, endOfName);
            int count = Integer.parseInt(line.substring(endOfName + 1, endOfCount));
            String value = line.substring(endOfCount + 1, endOfCount + 1 + count);

            pointer = endOfCount + 1 + count;
            return new Pair<>(name, value);
        }
    }
}