package pl.twardy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class implements method to parse one record from string
 */
class StringParser {

    /**
     * This method is used to parse record from string
     * it is fundamental method for this application
     * @param str This is string from where method parses string
     * @return It returns record as a Record class
     * @throws ClassNotFoundException It handless incorrect record declaration in Stirng
     */
    Record parseFromString(String str) throws ClassNotFoundException {

        String[] lines = str.split("\\n+", 2);

        String[] recordTypes = {"Book", "Article", "Booklet", "Conference", "Inbook",
                "Incollection", "Manual", "Mastersthesis", "Misc", "PhdThesis", "Techreport", "Unpublished",
                "Proceedings", "Inproceedings"};

        Pattern pattern = Pattern.compile("@([a-z]*)\\{", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(lines[0]);

        if (!matcher.find()){
            throw new RuntimeException("No match found! " + lines[0]);
        }

        String name = matcher.group(1);

        String finalName = name;

        if (name.toLowerCase().equals("string") || name.toLowerCase().equals("preamble")) {return null;}

        if (Arrays.stream(recordTypes).noneMatch(g -> g.toLowerCase().equals(finalName.toLowerCase()))){
            throw new RuntimeException("Record name " + name + " is not valid!");
        }

        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase() + "Factory";

        Record record = null;
        Map<String, String> neededFields = null;
        Map<String, String> optionalFields = null;

        try {
            Class<?> c = Class.forName("pl.twardy.records." + name);
            Method method = c.getMethod("make");
            record = (Record) method.invoke(null);
            neededFields = record.getNeededFields();
            optionalFields = record.getOptionalFields();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        String[] atributes = lines[1].split(",\n");

        pattern = Pattern.compile("[{](.*)[,]");
        matcher = pattern.matcher(lines[0]);

        if (matcher.find()){
            String key = matcher.group(1);
            record.setTypeKey(key);
        }

        Map<String, String> finalNeededFields = neededFields;
        Map<String, String> finalOptionalFields = optionalFields;

        Arrays.stream(atributes)
                .filter(g -> !g.equals("}"))
                .forEach(g ->
                {
                    String[] tmp = g.split("=", 2);
                    tmp[0] = tmp[0].replaceAll(" ", "");
                    tmp[1] = tmp[1].replaceAll("\\s+", " ");

                    Pattern p = Pattern.compile("[\"](.*)[\"]|[\"].*[\n](.*)[\"]|[{](.*)[}]");
                    Matcher m = p.matcher(tmp[1]);
                    String value = "";
                    if (!m.find()) {
                        value = tmp[1].replaceAll(" ", "");
                        value = value.replace("{", "").replace("}", "");
                    }
                    else {
                        value = m.group(0);
                        value = value.replace("\"", "");
                        value = value.replace("{", "").replace("}", "");
                    }
                    if(finalNeededFields.containsKey(tmp[0])) finalNeededFields.put(tmp[0],value);
                    else if(finalOptionalFields.containsKey(tmp[0])) finalOptionalFields.put(tmp[0], value);
                    else {
                        throw new RuntimeException("Atribute " +  tmp[0] + " not found!");
                    }
                });

        return record;
    }

    /**
     * This method is used to handle special @STRING records from BibTex file
     * @param s String with correct declaration of @STRING record
     * @return Map of key and values to replace in main BiBTtex file
     */
    Map<String, String> stringHandler(String s){
        String[] records = s.split("\n\n");

        Map<String, String> toReplace = new LinkedHashMap<>();
        Arrays.stream(records)
                .filter(g -> g.charAt(0) == '@')
                .forEach(g->{
                    String[] lines = g.split("\\n+", 2);

                    Pattern pattern = Pattern.compile("@([a-z]*)\\{", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(lines[0]);

                    if (!matcher.find()){
                        throw new RuntimeException("No match found! " + lines[0]);
                    }

                    String name = matcher.group(1);

                    if(name.toLowerCase().equals("string")){
                        pattern = Pattern.compile("\\{(.*)}");
                        matcher = pattern.matcher(lines[0]);
                        if(!matcher.find()){
                            throw new RuntimeException("No match found! In @STRING");
                        }

                        name = matcher.group(1);

                        String[] tmp = name.split("=");

                        String key = tmp[0].replaceAll(" ", "");
                        String val = tmp[1];

                        toReplace.put(key, val);

                    }
                });

        return toReplace;
    }
}
