package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Booklet record class factory used to create empty Booklet object
 */
public class BookletFactory extends RecordFactory{

    public static Record make(){
        Record record = new Record();
        record.setType("BOOKLET");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "title"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "author",
                "howpublished",
                "address",
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
