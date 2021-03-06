package com.good.code.starts.here.dialog.select;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;

import com.bitshares.bitshareswallet.R;

import java.util.List;

public class TokenSelectDialog {

    private AlertDialog dialog;
    private RecyclerView tokensRecycler;
    private SearchView searchView;
    private TokenSelectAdapter adapter;

    private Context context;

    public TokenSelectDialog(Context context) {
        this.context = context;
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_search, null, false);
        tokensRecycler = dialogView.findViewById(R.id.tokensRecycler);
        searchView = dialogView.findViewById(R.id.searchView);
        tokensRecycler.setLayoutManager(new LinearLayoutManager(context));
        tokensRecycler.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));


        searchView.setOnClickListener(v -> searchView.setIconified(false));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.filter(s);
                return true;
            }
        });

        dialog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .setTitle(R.string.select_token)
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    public void show(List<String> tokens, TokenSelectAdapter.OnTokenSelectListener listener) {
        adapter = new TokenSelectAdapter(context, tokens);
        adapter.setListener(listener);
        tokensRecycler.setAdapter(adapter);
        dialog.show();
    }

    public void close() {
        dialog.dismiss();
    }
}
