package com.izqisoft.tiantianshuqian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RankListActivity extends Activity{

	private ListView ltv; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ranklist_main);
		
		ltv=(ListView)findViewById(R.id.lt1);
		
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.ranklist,
                new String[]{"title","img"},
                new int[]{R.id.title,R.id.img_rank});
		ltv.setAdapter(adapter);
	}
	

	@Override
	protected void onDestroy() {
	}
	
	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "G1");
        map.put("img", R.drawable.stop_click_button);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "G2");
        map.put("img", R.drawable.stop_click_button);
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("title", "G3");
        map.put("img", R.drawable.stop_click_button);
        list.add(map);
         
        return list;
    }
}
