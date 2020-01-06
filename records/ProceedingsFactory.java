package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Proceedings record class factory used to create empty Proceedings object
 */
public class ProceedingsFactory extends RecordFactory {

    public static Record make(){
        Record record = new Record();
        record.setType("Proceedings");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "title",
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
