package com.rkleib.android.playground.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allyants.boardview.BoardAdapter;
import com.allyants.boardview.Item;
import com.allyants.boardview.R.layout;
import com.rkleib.android.playground.R;

import java.util.ArrayList;

public class BoardViewAdapter extends BoardAdapter {
    Context context;
    int header_resource;
    int item_resource;
    public BoardViewAdapter instance = this;

    public BoardViewAdapter(Context context, ArrayList<Column> data) {
        super(context);
        this.context = context;
        this.columns = data;
        this.header_resource = layout.column_header;
        this.item_resource = layout.column_item;
    }

    @Override
    public int getColumnCount() {
        return this.columns.size();
    }

    @Override
    public int getItemCount(int i) {
        return this.columns.get(i).objects.size();
    }

    @Override
    public int maxItemCount(int i) {
        return -1;
    }

    @Override
    public Object createHeaderObject(int i) {
        return this.columns.get(i).header_object;
    }

    @Override
    public Object createFooterObject(int i) {
        return null;
    }

    @Override
    public Object createItemObject(int i, int i1) {
        return null;
    }

    @Override
    public boolean isColumnLocked(int i) {
        return false;
    }

    @Override
    public boolean isItemLocked(int i) {
        return false;
    }

    @Override
    public View createItemView(Context context, Object header_obj, Object item_obj, int column_pos, int item_pos) {
        View item = View.inflate(context, R.layout.item_view, null);
        TextView textView = item.findViewById(R.id.title);
        textView.setText(this.columns.get(column_pos).objects.get(item_pos).toString());
        return item;
    }

    @Override
    public View createHeaderView(Context context, final Object o, final int i) {
        View column = View.inflate(context, R.layout.column_view, null);
        TextView textView = column.findViewById(R.id.title);
        final String header = this.columns.get(i).header_object.toString();
        textView.setText(header);
        return column;
    }

    @Override
    public View createFooterView(Context context, Object o, int i) {
        return null;
    }

    public static class CustomColumn extends Column {
        public String title;

        public CustomColumn(String title, ArrayList<Object> items) {
            this.title = title;
            this.header_object = new Item(title);
            this.objects = items;
        }
    }
}
