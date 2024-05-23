package com.example.screen_ui.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.screen_ui.R;

public class LoadingDialogUtils {

    private static Dialog customLoadingDialog;

    public static void showCustomLoadingDialog(Context context) {
        customLoadingDialog = new Dialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_loading_dialog, null);
        customLoadingDialog.setContentView(view);
        customLoadingDialog.setCancelable(false);
        customLoadingDialog.show();
    }

    public static void dismissCustomLoadingDialog() {
        if (customLoadingDialog != null && customLoadingDialog.isShowing()) {
            customLoadingDialog.dismiss();
        }
    }
}
