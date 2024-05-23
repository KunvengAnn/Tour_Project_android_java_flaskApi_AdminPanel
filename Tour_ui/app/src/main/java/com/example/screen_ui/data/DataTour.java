package com.example.screen_ui.data;

import com.example.screen_ui.models.ItemClass;
import com.example.screen_ui.models.ItemDt2;
import com.example.screen_ui.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataTour {
    static public List<ItemClass> listDataTour() {

        List<ItemClass> itemList = new ArrayList<>();
        List<Integer> drawableList1 = Arrays.asList(R.drawable.pk_1_img_1, R.drawable.pk_1_img_2, R.drawable.pk_1_img_3,R.drawable.pk_1_img_4,R.drawable.pk_1_img_5,R.drawable.pk_1_img_6,R.drawable.pk_1_img_7,R.drawable.pk_1_img_8,R.drawable.pk_1_img_9,R.drawable.pk_1_img_10,R.drawable.pk_1_img_11);
        List<Integer> drawableList2 = Arrays.asList(R.drawable.pk_2_img_1, R.drawable.pk_2_img_2, R.drawable.pk_2_img_3,R.drawable.pk_2_img_4,R.drawable.pk_2_img_5,R.drawable.pk_2_img_6,R.drawable.pk_2_img_7,R.drawable.pk_2_img_8,R.drawable.pk_2_img_9,R.drawable.pk_2_img_10,R.drawable.pk_2_img_11);
        List<Integer> drawableList3 = Arrays.asList(R.drawable.pk_3_img_1, R.drawable.pk_3_img_2, R.drawable.pk_3_img_3, R.drawable.pk_3_img_4, R.drawable.pk_3_img_5, R.drawable.pk_3_img_6, R.drawable.pk_3_img_7, R.drawable.pk_3_img_8, R.drawable.pk_3_img_9, R.drawable.pk_3_img_10, R.drawable.pk_3_img_11);
        List<Integer> drawableList4 = Arrays.asList(R.drawable.pk_4_img_1_, R.drawable.pk_4_img_2, R.drawable.pk_4_img_3,R.drawable.pk_4_img_4,R.drawable.pk_4_img_5,R.drawable.pk_4_img_6,R.drawable.pk_4_img_7,R.drawable.pk_4_img_8,R.drawable.pk_4_img_9,R.drawable.pk_4_img_10,R.drawable.pk_4_img_11);
        List<Integer> drawableList5 = Arrays.asList(R.drawable.pk_5_img_1, R.drawable.pk_5_img_2, R.drawable.pk_5_img_3,R.drawable.pk_5_img_4,R.drawable.pk_5_img_5,R.drawable.pk_5_img_6,R.drawable.pk_5_img_7,R.drawable.pk_5_img_8,R.drawable.pk_5_img_9,R.drawable.pk_5_img_10,R.drawable.pk_5_img_11);

        itemList.add(new ItemClass(drawableList1, "Cambodia 1", "Angkor wat 1"));
        itemList.add(new ItemClass(drawableList2, "Thai 2", "Bangkok 2"));
        itemList.add(new ItemClass(drawableList3, "USA 3", "London 3"));
        itemList.add(new ItemClass(drawableList4,"AVC","ABC"));
        itemList.add(new ItemClass(drawableList5,"BCD","ABC"));
        return itemList;
    }

    static public List<ItemDt2> listDataTourDetail(){
        List<ItemDt2> ItemPList = new ArrayList<>();
        List<Integer> drawableList6 = Arrays.asList(R.drawable.pk_6_img_1, R.drawable.pk_6_img_2, R.drawable.pk_6_img_3,R.drawable.pk_6_img_4,R.drawable.pk_6_img_5,R.drawable.pk_6_img_6,R.drawable.pk_6_img_7,R.drawable.pk_6_img_8,R.drawable.pk_6_img_9,R.drawable.pk_6_img_10,R.drawable.pk_6_img_11);
        List<Integer> drawableList7 = Arrays.asList(R.drawable.pk_7_img_1, R.drawable.pk_7_img_2, R.drawable.pk_7_img_3,R.drawable.pk_7_img_4,R.drawable.pk_7_img_5,R.drawable.pk_7_img_6,R.drawable.pk_7_img_7,R.drawable.pk_7_img_8,R.drawable.pk_7_img_9,R.drawable.pk_7_img_10,R.drawable.pk_7_img_11);
        List<Integer> drawableList8 = Arrays.asList(R.drawable.pk_8_img_1, R.drawable.pk_8_img_2, R.drawable.pk_8_img_3,R.drawable.pk_8_img_4,R.drawable.pk_8_img_5,R.drawable.pk_8_img_6,R.drawable.pk_8_img_7, R.drawable.pk_8_img_8,R.drawable.pk_8_img_9,R.drawable.pk_8_img_10,R.drawable.pk_8_img_11);
        List<Integer> drawableList9 = Arrays.asList(R.drawable.pk_9_img_1, R.drawable.pk_9_img_2, R.drawable.pk_9_img_3,R.drawable.pk_9_img_4,R.drawable.pk_9_img_5,R.drawable.pk_9_img_6,R.drawable.pk_9_img_7,R.drawable.pk_9_img_8,R.drawable.pk_9_img_9,R.drawable.pk_9_img_10,R.drawable.pk_9_img_11);
        List<Integer> drawableList10 = Arrays.asList(R.drawable.pk_10_img_1, R.drawable.pk_10_img_2, R.drawable.pk_10_img_3,R.drawable.pk_10_img_4,R.drawable.pk_10_img_5,R.drawable.pk_10_img_6,R.drawable.pk_10_img_7);

        ItemPList.add(new ItemDt2("USA", "Florida", "10$", drawableList6));
        ItemPList.add(new ItemDt2("Japan", "Tokyo", "20$", drawableList7));
        ItemPList.add(new ItemDt2("Cambodia", "Phnom Penh", "30$", drawableList8));
        ItemPList.add(new ItemDt2("Japan", "Tokyo", "20$",drawableList9));
        ItemPList.add(new ItemDt2("Cambodia", "Phnom Penh", "30$", drawableList10));

        return ItemPList;
    }

}
