package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Misc record class factory used to create empty Misc object
 */
public class MiscFactory extends RecordFactory{
    public static Record make(){
        Record record = new Record();
        record.setType("MISC");
        List<String> needed = new ArrayList<>();

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "author",
                "title",
                "howpublished",
                "year",
                "month",
                "note",
                "key",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }
}
