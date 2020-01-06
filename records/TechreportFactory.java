package pl.twardy.records;

import pl.twardy.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Techreport record class factory used to create empty Techreport object
 */
public class TechreportFactory extends RecordFactory {

    public static Record make(){
        Record record = new Record();
        record.setType("TECHREPORT");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "author",
                "title",
                "institution",
                "year"
        );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "editor",
                "volume",
                "series",
                "address",
                "month",
                "organization",
                "publisher",
                "note",
                "number",
                "key",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }

}
