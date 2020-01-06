package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Manual record class factory used to create empty Manual object
 */
public class ManualFactory extends RecordFactory{

    public static Record make(){
        Record record = new Record();
        record.setType("MANUAL");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,

                "title"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "author",
                "organization",
                "address",
                "note",
                "key",
                "year",
                "edition",
                "month",
                "crossref"

        );

        record.setOptional(optional);
        return record;
    }

}
