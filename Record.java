package pl.twardy;

import de.vandermeer.asciitable.AsciiTable;

import java.util.*;

/**
 * This is basic Record class
 * here are specified methods that every record has
 */
public class Record {

    private String typeKey;
    private String type;
    private Map<String, String> neededFields;
    private Map<String, String> optionalFields;

    /**
     * Constructor initializes empty variables
     */
    public Record(){
        this.type = "";
        this.neededFields = new LinkedHashMap<>();
        this.optionalFields = new LinkedHashMap<>();
    }

    /**
     * This method is used to print record as a table with AsciiTable
     * @return It returns table as a string
     */
    @Override
    public String toString() {

        AsciiTable at = new AsciiTable();

        at.addRule();
        at.addRow(this.type, this.typeKey);

        neededFields
                .entrySet()
                .stream()
                .filter(g -> !g.getValue().equals(""))
                .forEach(g -> {
                    at.addRule();
                    at.addRow(g.getKey(), g.getValue());
                });
        optionalFields
                .entrySet()
                .stream()
                .filter(g -> !g.getValue().equals(""))
                .forEach(g -> {
                    at.addRule();
                    at.addRow(g.getKey(), g.getValue());
                });
        at.addRule();
        at.getContext().setWidth(150);
        return at.render();
    }

    /**
     * This method sets needed fields as a empty string
     * @param needed This is list of filed names
     */
    public void setNeeded(List<String> needed) {
        needed
                .forEach(g -> this.neededFields.put(g, ""));
    }
    /**
     * This method sets optional fields as a empty string
     * @param optional This is list of filed names
     */
    public void setOptional(List<String> optional) {
        optional
                .forEach(g -> this.optionalFields.put(g, ""));
    }

    /**
     * This method checks if the record has all needed fields different than empty string
     * it is used to make sure that record is correct
     * @return It returns True while record is correct and False while not
     */
    public boolean checkNeededFields(){
        if (this.neededFields.containsKey("author") && this.neededFields.containsKey("editor"))
            return !this.neededFields.get("author").equals("") || !this.neededFields.get("editor").equals("") ||neededFields
                    .entrySet()
                    .stream()
                    .noneMatch(g -> g.getValue().equals(""));
        return neededFields
                .entrySet()
                .stream()
                .noneMatch(g -> g.getValue().equals(""));
    }

    /**
     * @return type of the record as string
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the record as a String
     * it should start with capital letter
     * @param type type of the record as String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for Record needed fields
     * @return it returns record needed fields hashmap
     */
    public Map<String, String> getNeededFields(){
        return this.neededFields;
    }

    /**
     * Set Record needed fields,
     * Map where value cannot be empty string
     * @param neededFields String as key and String as value map
     */
    public void setNeededFields(Map<String, String> neededFields) {
        this.neededFields = neededFields;
    }

    /**
     * Getter for Record optional fields
     * @return it returns record needed fields hashmap
     */
    public Map<String, String> getOptionalFields() {
        return optionalFields;
    }
    /**
     * Set Record optional fields,
     * Map where value cannot be empty string
     * @param optionalFields String as key and String as value map
     */
    public void setOptionalFields(Map<String, String> optionalFields) {
        this.optionalFields = optionalFields;
    }

    /**
     * Getter used for cross reference
     * it is used to store record reference key
     * @return it returns reference key as a String
     */
    public String getTypeKey() {
        return typeKey;
    }

    /**
     * Set referenece key for a record as a String
     * @param typeKey This is record key
     */
    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }
}
