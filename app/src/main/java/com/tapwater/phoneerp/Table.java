package com.tapwater.phoneerp;

/**
 * Created by Tapwater on 17-4-19.
 * table object
 */

public class Table {
    private int tableColumnNum, tableRowNum;
    private String[] tableColumnNames, tableColumnTypes;
    private String[][] tableContent;

    public Table() {
    }

    public Table(int tableRowNum, int tableColumnNum, String[] tableColumnNames, String[] tableColumnTypes, String[][] tableContent) {
        this.tableColumnNames = tableColumnNames;
        this.tableColumnNum = tableColumnNum;
        this.tableColumnTypes = tableColumnTypes;
        this.tableContent = tableContent;
        this.tableRowNum = tableRowNum;
    }

    public String[] getTableColumnNames() {
        return tableColumnNames;
    }

    public void setTableColumnNames(String[] tableColumnNames) {
        this.tableColumnNames = tableColumnNames;
    }

    public int getTableColumnNum() {
        return tableColumnNum;
    }

    public void setTableColumnNum(int tableColumnNum) {
        this.tableColumnNum = tableColumnNum;
    }

    public String[] getTableColumnTypes() {
        return tableColumnTypes;
    }

    public void setTableColumnTypes(String[] tableColumnTypes) {
        this.tableColumnTypes = tableColumnTypes;
    }

    public String[][] getTableContent() {
        return tableContent;
    }

    public void setTableContent(String[][] tableContent) {
        this.tableContent = tableContent;
    }

    public int getTableRowNum() {
        return tableRowNum;
    }

    public void setTableRowNum(int tableRowNum) {
        this.tableRowNum = tableRowNum;
    }
}
