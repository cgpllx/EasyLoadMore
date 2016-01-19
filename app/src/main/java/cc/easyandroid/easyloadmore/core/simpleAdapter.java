package cc.easyandroid.easyloadmore.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cc.easyandroid.easyloadmore.R;

/**
 * Created by chenguoping on 16/1/19.
 */
public class SimpleAdapter extends  ArrayAdapter<String>{
    public SimpleAdapter(Context context){
        this.context=context;

    }
    Context context;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.klistview_footer,parent,false);
        }
        return convertView;
    }
}
