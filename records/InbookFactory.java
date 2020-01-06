package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Inbook record class factory used to create empty Inbook object
 */
public class InbookFactory extends RecordFactory {
    public static Record make(){
        Record record = new Record();
        record.setType("INBOOK");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "author",
                "editor",
                "title",
                "chapter",
                "pages",
                "publisher",
                "year"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "volume",
                "number",
                "series",
                "type",
                "address",
                "note",
                "key",
                "edition",
                "month",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }
}
