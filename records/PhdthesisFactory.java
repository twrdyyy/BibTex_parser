package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Phdthesis record class factory used to create empty Phdthesis object
 */
public class PhdthesisFactory extends RecordFactory{
    public static Record make(){
        Record record = new Record();
        record.setType("PHDTHESIS");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "author",
                "title",
                "school",
                "year"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "type",
                "note",
                "key",
                "address",
                "month",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }

}
