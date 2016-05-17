package com.ps.app.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ps.app.R;
import com.ps.app.support.db.TagsManager;

import me.gujun.android.taggroup.TagGroup;

public class EditRecSearchTagActivity extends BaseActivity {
    private TagGroup mTagGroup;
    private TagsManager mTagsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rec_search_tag);
        initActionBar(-1, "编辑最近搜索");
        mTagsManager = TagsManager.getInstance(getApplicationContext());
        String[] tags = mTagsManager.getTags();
        mTagGroup = (TagGroup) findViewById(R.id.tag_group);
        mTagGroup.setTags(tags);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.menu_tag, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mTagGroup.submitTag();
            mTagsManager.updateTags(mTagGroup.getTags());
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        mTagsManager.updateTags(mTagGroup.getTags());
        super.onBackPressed();
    }
}
