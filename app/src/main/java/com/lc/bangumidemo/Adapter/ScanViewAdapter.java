package com.lc.bangumidemo.Adapter;


import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lc.bangumidemo.Adapter.PageAdapter;
import com.lc.bangumidemo.KT.MapupdatKt;
import com.lc.bangumidemo.Myreadview.Pageview;
import com.lc.bangumidemo.Myreadview.ScanView;
import com.lc.bangumidemo.R;
import com.lc.bangumidemo.Sqlite.Bookindexclass;
import com.lc.bangumidemo.Sqlite.Bookreadclass;
import com.lc.bangumidemo.Sqlite.Bookselect;
import com.lc.bangumidemo.Sqlite.Bookupdata;
import com.lc.bangumidemo.Sqlite.MyDatabaseHelper;
import com.lc.bangumidemo.Sqlite.Selectclass;

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
        Pageview pageview =view.findViewById(R.id.pageview);
        String st=null;
        Bookselect.selectalldata(db);
        try {
            retsult=Bookselect.selectbookreaddata(db,selectclass,position);
            st = retsult.getBookdata();
            pageview.setContent(st);
        }catch (Exception e){
        }
        try {
            if(ScanView.INDEXTAGRIG==true||ScanView.INDEXTAGLETE==true) {
                Bookindexclass requese=null;
                if(ScanView.INDEXTAGRIG==true) {
                    requese = new Bookindexclass(null, startindex.getAuthor(), startindex.getBookname(), startindex.getHardpageindex(), startindex.getHardcontentindex(), startindex.getPagecount(), retsult.getPageindex(), retsult.getContentindex()-1);
                }else
                {
                    requese = new Bookindexclass(null, startindex.getAuthor(), startindex.getBookname(), startindex.getHardpageindex(), startindex.getHardcontentindex(), startindex.getPagecount(), retsult.getPageindex(), retsult.getContentindex()+1);

                }
                Bookupdata.updata(db, requese);
                MapupdatKt.Mapupdata(context, requese);
                ScanView.INDEXTAGRIG=false;
                ScanView.INDEXTAGLETE=false;
            }
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