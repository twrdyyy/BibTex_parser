package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Conference record class factory used to create empty Conference object
 */
public class ConferenceFactory extends RecordFactory{

    public static Record make(){
        Record record = new Record();
        record.setType("Conference");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "author",
                "title",
                "booktitle",
                "year"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "volume",
                "editor",
                "number",
                "series",
                "pages",
                "note",
                "key",
                "address",
                "month",
                "organization",
                "publisher",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }

}
