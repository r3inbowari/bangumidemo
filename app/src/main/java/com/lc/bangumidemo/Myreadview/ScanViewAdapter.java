package com.lc.bangumidemo.Myreadview;


import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lc.bangumidemo.KT.MapupdatKt;
import com.lc.bangumidemo.R;
import com.lc.bangumidemo.Sqlite.Bookindexclass;
import com.lc.bangumidemo.Sqlite.Bookreadclass;
import com.lc.bangumidemo.Sqlite.Bookselect;
import com.lc.bangumidemo.Sqlite.Bookupdata;
import com.lc.bangumidemo.Sqlite.MyDatabaseHelper;
import com.lc.bangumidemo.Sqlite.Selectclass;

import java.sql.ResultSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ScanViewAdapter extends PageAdapter
{
    Context context;
    AssetManager am;
    MyDatabaseHelper db=null;
    Selectclass selectclass=null;
    Bookindexclass startindex=null;
    public ScanViewAdapter(Context context, MyDatabaseHelper db, Bookindexclass startindex)
    {
        this.context = context;
        am = context.getAssets();
        this.db=db;
        selectclass=new Selectclass(startindex.getBookname(),startindex.getAuthor(),startindex.getPagecount());
        this.startindex=startindex;
    }

    public void addContent(View view, int position)
    {
        Bookreadclass retsult=null;
        TextView content = (TextView) view.findViewById(R.id.content);
        TextView tv = (TextView) view.findViewById(R.id.index);
        String st=null;
        Bookselect.selectalldata(db);
        try {
            retsult=Bookselect.selectbookreaddata(db,selectclass,position);
            st = retsult.getBookdata();
        }catch (Exception e){
        }
        content.setText(st);
        tv.setText(new Integer(position).toString());
        try {

            Bookindexclass requese = new Bookindexclass(null, startindex.getAuthor(), startindex.getBookname(), startindex.getHardpageindex(), startindex.getHardcontentindex(), startindex.getPagecount(), retsult.getPageindex(), retsult.getContentindex());
            Bookupdata.updata(db,requese);
            MapupdatKt.Mapupdata(context, requese);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int getCount()
    {
        return 0;
    }

    public View getView()
    {
        View view = LayoutInflater.from(context).inflate(R.layout.page_layout,
                null);
        return view;
    }
}