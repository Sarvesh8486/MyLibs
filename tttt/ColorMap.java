package com.whiteboard.securenotes.utils;

import android.content.Context;

import com.whiteboard.securenotes.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stark on 14/05/2017.
 */

public class ColorMap {
    Map<String, Integer> map;
    public ColorMap(Context context){
        map = new HashMap<>();
        map.put("brick", context.getResources().getColor(R.color.note_color_brick_code));
        map.put("grey", context.getResources().getColor(R.color.note_color_grey_code));
        map.put("green", context.getResources().getColor(R.color.note_color_green_code));
        map.put("skin", context.getResources().getColor(R.color.note_color_skin_code));
        map.put("white", context.getResources().getColor(R.color.note_color_white_code));
        map.put("yellow", context.getResources().getColor(R.color.note_color_yellow_code));


    }

    public int getColorInt(String color){
        return map.get(color);
    }
}
