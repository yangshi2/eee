package com.bawei.yangshi2019073.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bawei.yangshi2019073.R;
import com.bawei.yangshi2019073.bean.Bean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * User: 杨石
 * Date: 2019/7/3
 * Time: 09:43
 * 作用:listview的适配器
 */
public class MyAdapter extends BaseAdapter {
    private List<Bean.DataBean.NewsBean> list1;
    private Context context;

    public MyAdapter(List<Bean.DataBean.NewsBean> list1, Context context) {
        this.list1 = list1;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //判断是哪个布局
        switch (getItemViewType(position)){
            case 0:
                ViewHolder1 viewHolder1;
                if (convertView==null){
                    viewHolder1 = new ViewHolder1();
                    convertView=View.inflate(context, R.layout.layout,null);
                    viewHolder1.tv1=convertView.findViewById(R.id.tv1);
                    viewHolder1.iv1=convertView.findViewById(R.id.iv1);
                    convertView.setTag(viewHolder1);
                }else{
                    viewHolder1=(ViewHolder1)convertView.getTag();
                }
                viewHolder1.tv1.setText(list1.get(position).getTitle());
                Glide.with(context).load("http://blog.zhaoliang5156.cn/zixunnew/"+list1.get(position).getImageUrl()).into(viewHolder1.iv1);
                break;
            case 1:
                ViewHolder2 viewHolder2;
                if (convertView==null){
                    viewHolder2 = new ViewHolder2();
                    convertView=View.inflate(context, R.layout.layout1,null);
                    viewHolder2.tv2=convertView.findViewById(R.id.tv2);
                    viewHolder2.iv2=convertView.findViewById(R.id.iv2);
                    convertView.setTag(viewHolder2);
                }else{
                    viewHolder2=(ViewHolder2)convertView.getTag();
                }
                viewHolder2.tv2.setText(list1.get(position).getTitle());
                Glide.with(context).load("http://blog.zhaoliang5156.cn/zixunnew/"+list1.get(position).getImageUrl()).into(viewHolder2.iv2);
                break;
        }
        return convertView;
    }
   class ViewHolder1{
        TextView tv1;
        ImageView iv1;
   }
   class ViewHolder2{
        TextView tv2;
        ImageView iv2;
   }

   //两个方法
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }


}
