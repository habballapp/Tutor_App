package com.example.tutor_app.Loader;

import android.app.Dialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;

import com.example.tutor_app.R;

public class Loader {
    private Dialog fbDialogue;
    private Context mContext;

    public Loader(Context context) {
        mContext = context;
        fbDialogue = new Dialog(mContext, R.style.theme_loader);

        fbDialogue.setContentView(R.layout.loader);
        Window window = fbDialogue.getWindow();

        //window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        window.setBackgroundDrawableResource(R.color.dialog_back);
    }

    public void showLoader() {

        Log.i("mContext", String.valueOf(mContext));


        fbDialogue.show();

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Log.i("mContext_hide", String.valueOf(mContext));
                fbDialogue.dismiss();

            }
        }.start();
    }

    public void hideLoader() {
        Log.i("mContext_hide", String.valueOf(mContext));
        fbDialogue.dismiss();
    }

    public boolean isShowing() {
        return fbDialogue.isShowing();
    }
}
