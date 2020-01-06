package pl.twardy.records;

import pl.twardy.Record;

import java.util.*;

/**
 * Article record class factory used to create empty Article object
 */
public class ArticleFactory extends RecordFactory {

    public static Record make(){
        Record record = new Record();
        record.setType("ARTICLE");
        List<String> needed = new ArrayList<>();

        Collections.addAll(needed,
                "author",
                "title",
                "journal",
                "year"
                );

        record.setNeeded(needed);

        List<String> optional = new ArrayList<>();

        Collections.addAll(optional,
                "volume",
                "number",
                "pages",
                "month",
                "note",
                "key",
                "crossref"
        );

        record.setOptional(optional);
        return record;
    }
}
