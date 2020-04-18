//package com.sdp.eteaching.util.AssiginHomeworkUtil;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import com.sdp.eteaching.R;
//
//import java.util.List;
//
///**
// * Created by Administrator on 2015/10/20.
// */
//public class ListViewWithCheckBoxAdapter extends BaseAdapter {
//
//    private List<ItemBean> list;
//    private LayoutInflater layoutInflater;
//
//    public ListViewWithCheckBoxAdapter(Context context,List<ItemBean> list){
//        this.list = list;
//        layoutInflater = LayoutInflater.from(context);
//
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        if(convertView == null){
//            convertView = layoutInflater.inflate(R.layout.class_info_item_for_homework,null);
//
//            ViewHolder viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        }
//
//        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
//        //viewHolder.imageView.setBackgroundResource(list.get(position).getPictureId());
//        viewHolder.class_name_homework.setText(list.get(position).getClass_name_homework());
//        viewHolder.class_id_homework.setText(list.get(position).getClass_id_homework());
//        viewHolder.school_name_homework.setText(list.get(position).getSchool_name_homework());
//
//        if(list.get(position).isShowCheckBox()){
//            viewHolder.checkBox.setVisibility(View.VISIBLE);
//            if(list.get(position).isSelect()){
//                viewHolder.checkBox.setChecked(true);
//            }else{
//                viewHolder.checkBox.setChecked(false);
//            }
//        }else{
//            viewHolder.checkBox.setVisibility(View.INVISIBLE);
//        }
//
//        return convertView;
//    }
//
//    public class ViewHolder {
////        public final ImageView imageView;
//        public final TextView class_name_homework;
//        public final TextView class_id_homework;
//        public final TextView school_name_homework;
//        public final CheckBox checkBox;
//        public final View root;
//
//        public ViewHolder(View root) {
////            imageView = (ImageView) root.findViewById(R.id.imageButton);
//            class_name_homework = (TextView) root.findViewById(R.id.class_name_homework);
//            class_id_homework = (TextView) root.findViewById(R.id.class_id_homework);
//            school_name_homework=(TextView) root.findViewById(R.id.school_name_homework);
//            checkBox = (CheckBox) root.findViewById(R.id.class_checkBox);
//            this.root = root;
//        }
//    }
//}
//
