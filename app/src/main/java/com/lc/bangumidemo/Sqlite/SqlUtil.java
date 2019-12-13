package com.lc.bangumidemo.Sqlite;

public class SqlUtil {
    public static Nvdetil findbookisexist(MyDatabaseHelper dbhelper, Nvdetil data)
    {
        Nvdetil isexist=Bookselect.select(dbhelper,data);
        //如果有这本书的历史记录
        if(isexist!=null)
        {
            return isexist;
        }
        return null;//找不到这本书
    }
    public static void recordbookindex(MyDatabaseHelper dbhelper, Nvdetil data)
    {
        Bookinsert.insert(dbhelper,data);

    }
}
