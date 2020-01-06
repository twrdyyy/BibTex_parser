package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Incollection record class factory used to create empty Incollection object
 */
public class IncollectionFactory extends RecordFactory{

    public static Record make(){
        Record record = new Record();
        record.setType("INCOLLECTION");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "author",
                "title",
                "booktitle",
                "publisher",
                "year"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "volume",
                "editor",
                "number",
                "series",
                "type",
                "note",
                "key",
                "chapter",
                "pages",
                "address",
                "edition",
                "month",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }

}
