package com.lc.bangumidemo.KT

import android.content.Context
import com.lc.bangumidemo.Sqlite.*

fun Mapinit(context: Context,data:Bookindexclass)
{
        //查询数据
    var db= MyDatabaseHelper(context,"bookstore",null,1)
       var selectclass=Selectclass(data.bookname,data.author,data.pagecount)
       var returnsult=Bookselect.selectbookdata(db,selectclass,data.pageindex)!!
       var list=PagesizeUtil.txttolist(returnsult.content,context, fontsize, linesize)
        var index=-data.hardcontentindex
        var content=0
        for(st in list )
       {
           var readdata= Bookreadclass(data.author,data.bookname,data.pagecount,st,data.pageindex,content++,-data.hardcontentindex,list.size-hardcontentindex,index)
           //插入数据库
           Bookinsert.insertbookread(db,readdata)
           index++
       }
    db.close()
}
fun Mapupdata(context: Context,data:Bookindexclass)
{
    //获取本章的start end信息
    var db= MyDatabaseHelper(context,"bookstore",null,1)
    var selectclass=Selectclass(data.bookname,data.author,data.pagecount)
    var returnsult=Bookselect.selectbookread(db,selectclass,data.pageindex)!!
    var start=returnsult.start
    var end =returnsult.end
    //查询上下章是否已映射，查询上下章信息 //如果没有数据，则加载
    var returnsultpre=Bookselect.selectbookread(db,selectclass,data.pageindex-1)
    //如果没有数据，则加载
    if(data.pageindex!=0) {
    var i=MyDatabaseHelper(context,"bookstore",null,1)
    var selectdata = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
    var resultnow= Bookselect.selectbookdata(i,selectdata,data.pageindex-1)

        if (resultnow == null) loadbookdatatopage(context, bookDetail, data.pageindex - 1)
        i.close()
    }


    //
    if(data.pageindex!=0) {
        if (returnsultpre == null) {
            var selectclass = Selectclass(data.bookname, data.author, data.pagecount)
            var returnsult = Bookselect.selectbookdata(db, selectclass, data.pageindex - 1)
            var list = PagesizeUtil.txttolist(returnsult.content, context, fontsize, linesize)
            var index = start - 1 - list.size
            var content=0
            for (st in list) {
                var readdata = Bookreadclass(
                    data.author,
                    data.bookname,
                    data.pagecount,
                    st,
                    data.pageindex-1,
                    content++,
                    start - 1 - list.size,
                    start - 1,
                    index
                )
                //插入数据库
                Bookinsert.insertbookread(db, readdata)
                index++

            }
        }
    }
    db.close()
    //如果没有数据，则加载
    var i2=MyDatabaseHelper(context,"bookstore",null,1)
    var selectdata2 = Selectclass(bookDetail!!.data.name, bookDetail!!.data.author, bookDetail!!.list.size)
    var resultnow2= Bookselect.selectbookdata(i2,selectdata2,data.pageindex+1)
    if (resultnow2==null)loadbookdatatopage(context, bookDetail,data.pageindex+1)
    i2.close()
    //
    var db2= MyDatabaseHelper(context,"bookstore",null,1)
    var returnsultnext=Bookselect.selectbookread(db2,selectclass,data.pageindex+1)
    if(returnsultnext==null)
    {
        var selectclass=Selectclass(data.bookname,data.author,data.pagecount)
        var returnsult=Bookselect.selectbookdata(db2,selectclass,data.pageindex+1)
        var list=PagesizeUtil.txttolist(returnsult.content,context, fontsize, linesize)
        var index=end
        var content=0
        for(st in list)
        {
            var readdata= Bookreadclass(data.author,data.bookname,data.pagecount,st,data.pageindex+1,content++,end+1,list.size+end+1,index)
            //插入数据库
            Bookinsert.insertbookread(db2,readdata)
            index++

        }
    }
    db2.close()


    //对上下章进行映射

}