package pl.twardy;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store Records
 * It also has finder to filter Records by given regular expression
 */
public class Storage {

    private List<Record> recordList;

    /**
     * This constructor method initializes private class fields
     */
    Storage(){
        this.recordList = new ArrayList<>();
    }

    /**
     * Get list of records
     * @return list of records as ArrayList
     */
    public List<Record> getRecordList() {
        return recordList;
    }

    /**
     * Set storage list of records
     * @param recordList List of records as a ArrayList
     */
    public void setRecordList(List<Record> recordList) {
        this.recordList = recordList;
    }

    /**
     * This method adds one record to storage
     * Record has to be correct
     * @param record Record class object
     */
    public void addRecord(Record record){
        this.recordList.add(record);
    }
}
