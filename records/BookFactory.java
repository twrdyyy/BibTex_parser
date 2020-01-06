package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Book record class factory used to create empty Book object
 */
public class BookFactory extends RecordFactory{

    public static Record make(){
        Record record = new Record();
        record.setType("BOOK");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "author",
                "title",
                "editor",
                "publisher",
                "year"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "volume",
                "series",
                "address",
                "edition",
                "month",
                "note",
                "key",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }
}
