package com.gst.move.base;

import android.content.Context;

import com.ebodoo.raz.server.CommonAdressConstant;
import com.ebodoo.raz.server.CommonInterfaces;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gst-pc on 2016/11/2.
 */

public class Medal {

    public List<ListviewData> getData() {
        int[] mLenArray = new int[] {
                4, 4, 5, 8, 7, 8, 14
        };
        List<ListviewData> listData = new ArrayList<ListviewData>();
        for (int i=0; i<7; i++) {
            ListviewData data = new ListviewData();
            data.setName("-----" + i);
            List<GridViewData> listGrid = new ArrayList<GridViewData>();
            for(int j=0; j<mLenArray[i]; j++) {
                GridViewData grid = new GridViewData();
                grid.setLen(j);
                listGrid.add(grid);
            }
            data.setGridData(listGrid);
            listData.add(data);
        }
        return listData;
    }

    // 获得勋章列表
    public void getMedalList(Context context) {
        String result = new CommonInterfaces().getDataFromUrlByGet(context,
                CommonAdressConstant.wearLouly, "");
        System.out.println("------getLoudlyList--result-----------" + result);
//        return parseSpeak(context, result, false);
    }

    private Medal parseMedal(String result) {
        if (result == null || result.equals("")) {
            return null;
        }
        try {
            JSONObject data = new JSONObject(result);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
