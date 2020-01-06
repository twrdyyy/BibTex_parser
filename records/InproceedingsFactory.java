package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Inproceedings record class factory used to create empty Inproceedings object
 */
public class InproceedingsFactory extends RecordFactory {

    public static Record make(){
        Record record = new Record();
        record.setType("Inproceedings");
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
                "editor",
                "volume",
                "number",
                "series",
                "address",
                "pages",
                "month",
                "publisher",
                "organization",
                "note",
                "key",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }

}
