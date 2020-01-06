package pl.twardy;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  This class implements methods to
 *  filter records by specific record filed
 */
class Finder {

    /**
     * This method filters records by author
     * @param recordList this is the list of records to filter
     * @param reg this is regular expression used to filter record list
     * @return it returns list of records found by pattern matching
     */
    List<Record> findByAuthor(List<Record> recordList, String reg){

        Pattern pattern = Pattern.compile(reg);

        List<Record> ans = new ArrayList<Record>();

        recordList.stream()
                .filter(g -> g.getNeededFields().containsKey("author"))
                .forEach(g -> {
                    Matcher matcher = pattern.matcher(g.getNeededFields().get("author"));
                    if (matcher.find())
                        ans.add(g);
                });

        return ans;
    }
}
