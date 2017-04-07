package com.tapwater.phoneerp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.askerov.dynamicgrid.BaseDynamicGridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tapwater on 17-1-21.
 */

public class  UserGridViewAdapter extends BaseDynamicGridAdapter {


    private Context mContext;
    public ArrayList<UserGridViewItem> arrayListIcon;
    public boolean isShowDelete = false;
    private UserGridViewAdapter userGridViewAdapter;


    public UserGridViewAdapter(Context context, List<?> items, int columnCount) {
        super(context, items, columnCount);
        mContext = context;
        arrayListIcon = (ArrayList<UserGridViewItem>)items;
    }

    public void setShowDelete(boolean isShowDelete)
    {
        this.isShowDelete = isShowDelete;

    }

    public void setAdapter(UserGridViewAdapter userGridViewAdapter)
    {
        this.userGridViewAdapter = userGridViewAdapter;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHost viewHost = null;
        if (view == null)
        {
            view = LayoutInflater.from(mContext).inflate(R.layout.user_grid_item,null);
//            view = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item,viewGroup,false);
            viewHost = new ViewHost();
            viewHost.imageViewHead = (ImageView) view.findViewById(R.id.imageViewGridItem);
            viewHost.imageViewDelete = (ImageView) view.findViewById(R.id.imageViewDelete);
            viewHost.textView = (TextView) view.findViewById(R.id.textViewGridItem);
            view.setTag(viewHost);
        }
        else
        {
            viewHost = (ViewHost) view.getTag();
        }
        //**
        UserGridViewItem userGridViewItem = (UserGridViewItem) getItem(i);
        //**
        viewHost.imageViewHead.setBackgroundResource(userGridViewItem.getIconHead());
        viewHost.imageViewDelete.setVisibility(isShowDelete?View.VISIBLE:View.GONE);
        if(viewHost.imageViewDelete.getVisibility() == View.VISIBLE)
        {
            viewHost.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    userGridViewAdapter.remove((UserGridViewItem) userGridViewAdapter.getItem(i));
                    arrayListIcon.remove(i);
                    notifyDataSetChanged();
                }
            });
        }
        viewHost.textView.setText(userGridViewItem.getIconName());

//        viewHost.imageViewHead.setBackgroundResource(arrayListIcon.get(i).getIconHead());
//        viewHost.textView.setText(arrayListIcon.get(i).getIconName());

        view.setPadding(10,10,10,10);

//        View v = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
//        TextView text = (TextView) v.findViewById(android.R.id.text1);
//        text.setText("项目-" + i);
//        v.setPadding(10, 10, 10, 10);
        return view;
    }

    public void reFresh()
    {
        notifyDataSetChanged();
//        notifyDataSetInvalidated();
//        Toast.makeText(mContext,"Refresh",Toast.LENGTH_LONG).show();
    }
//    public MyAdapter(ArrayList<Icon> arrayListIcon, Context context, List<?> items, int columnCount) {
//        super(context, items, columnCount);
//        mContext = context;
//        this.arrayListIcon = arrayListIcon;
//    }


    static class ViewHost
    {
        ImageView imageViewHead;
        ImageView imageViewDelete;
        TextView textView;
    }
}
