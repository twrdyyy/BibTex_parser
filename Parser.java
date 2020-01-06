package pl.twardy;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is used to parse bibtex file to record classes
 */
class Parser {

    private String fileName;

    private StringParser oneRecordParser = new StringParser();

    /**
     * This constructor method is used to get filename to parse bibtex from
     * @param fileName This is the path to the file for parser
     */
    Parser(String fileName){
        this.fileName = fileName;
    }

    /**
     * This method is used to get list of records from given file
     * @return Method returns list of records
     * @throws RuntimeException It throws error while BiBTex file is incorrect
     */
    List<Record> getRecordsFromFile() throws RuntimeException {
        List<Record> ans = new ArrayList<>();

        List<Record> toDelete = new ArrayList<>();
        
        List<Record> records = new ArrayList<>();

        List<Record> crossrefRecords = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    this.fileName));
            String line = reader.readLine();
            String f = "";
            boolean flag = false;
            while (line != null){
                if (!line.equals("") && line.charAt(0) == '@')
                    flag = true;

                if (flag)
                    f += line + "\n";

                line = reader.readLine();
            }

            f = f.replaceAll("\n\n+", "\n\n");

            Map<String, String> toReplace = oneRecordParser.stringHandler(f);

            for (Map.Entry<String, String> entry : toReplace.entrySet()){
                f = f.replace(entry.getKey(), entry.getValue());
            }

            String[] lines = f.split("\n\n");

            final Record[] record = {null};

            Arrays.stream(lines)
                    .filter(g -> g.charAt(0) == '@')
                    .forEach(g -> {
                        try {
                            record[0] = oneRecordParser.parseFromString(g);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (record[0] != null) {
                            if(!record[0].getOptionalFields().get("crossref").equals("")) {
                                crossrefRecords.add(record[0]);
                            }
                            else {
                                records.add(record[0]);
                            }
                        }
                    });


            crossrefRecords
                    .forEach(g -> {
                       records.stream()
                               .filter(x -> x.getTypeKey().toLowerCase()
                                       .equals(g.getOptionalFields().get("crossref").toLowerCase()))
                               .forEach(x -> {

                                   toDelete.add(x);
                                   Map<String, String> fields = x.getNeededFields();

                                   fields.entrySet().stream()
                                           .filter(y -> !y.getValue().equals("")
                                                   && g.getNeededFields().containsKey(y.getKey())
                                                   && g.getNeededFields().get(y.getKey()).equals(""))
                                           .forEach(y -> {
                                               g.getNeededFields().replace(y.getKey(), y.getValue());
                                           });

                                   fields = x.getOptionalFields();

                                   fields.entrySet().stream()
                                           .filter(y -> !y.getValue().equals("")
                                                   && g.getOptionalFields().containsKey(y.getKey())
                                                   && g.getOptionalFields().get(y.getKey()).equals(""))
                                           .forEach(y -> g.getOptionalFields().put(y.getKey(), y.getValue()));
                              });
                       records.add(g);
                    });
            ans = records.stream()
                    .filter(g -> !toDelete.contains(g))
                    .collect(Collectors.toList());
            ans.forEach(g -> {
                if (!g.checkNeededFields()) {
                    throw new RuntimeException("Missing needed fileds in " + g.getType());
//                    System.out.println("Missing needed fileds in " + g.getType());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

}
