package com.bitshares.bitshareswallet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import com.akexorcist.localizationactivity.ui.LocalizationActivity;
import com.good.code.starts.here.ColorUtils;

public class ModelSelectActivity extends LocalizationActivity {
    private Toolbar mToolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //if(preferences.contains("locale")) setLanguage(preferences.getString("locale", "ru"));
        setContentView(R.layout.activity_model_select);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        int color = ColorUtils.getMainColor(this);
        mToolbar.setBackgroundColor(color);
        mToolbar.getRootView().setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ColorUtils.manipulateColor(color, 0.75f));
        }
        mToolbar.setNavigationOnClickListener(v -> finish());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.textViewAccountModel).setOnClickListener(v -> {
            Intent intent = new Intent(ModelSelectActivity.this, ImportActivity.class);
            intent.putExtra("model", ImportActivity.ACCOUNT_MODEL);
            startActivity(intent);
        });

        findViewById(R.id.textViewWalletModelWifKey).setOnClickListener(v -> {
            Intent intent = new Intent(ModelSelectActivity.this, ImportActivity.class);
            intent.putExtra("model", ImportActivity.WALLET_MODEL_WIF_KEY);
            startActivity(intent);
        });

        findViewById(R.id.textViewWalletModelBin).setOnClickListener(v -> {
            Intent intent = new Intent(ModelSelectActivity.this, ImportActivity.class);
            intent.putExtra("model", ImportActivity.WALLET_MODEL_BIN_FILE);
            startActivity(intent);
        });

        findViewById(R.id.textViewWalletModelBrainKey).setOnClickListener(v -> {
            Intent intent = new Intent(ModelSelectActivity.this, ImportActivity.class);
            intent.putExtra("model", ImportActivity.WALLET_MODEL_BRAIN_KEY);
            startActivity(intent);
        });
    }
}
