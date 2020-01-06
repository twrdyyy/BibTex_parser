package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Unpublished record class factory used to create empty Unpublished object
 */
public class UnpublishedFactory {

    public static Record make(){
        Record record = new Record();
        record.setType("UNPUBLISHED");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "author",
                "title",
                "note"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "month",
                "year",
                "key",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }



}
