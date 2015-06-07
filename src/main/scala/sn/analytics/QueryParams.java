package sn.analytics;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Sumanth on 07/06/15.
 */
public class QueryParams implements Serializable {

    private String queryStr;
    private Set<String> filePaths;


    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public Set<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(Set<String> filePaths) {
        this.filePaths = filePaths;
    }
}
