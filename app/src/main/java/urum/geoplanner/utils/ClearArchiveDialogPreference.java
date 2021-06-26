package urum.geoplanner.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.preference.DialogPreference;

import urum.geoplanner.R;
import urum.geoplanner.viewmodel.PlaceViewModel;


public class ClearArchiveDialogPreference extends DialogPreference {
    Context context;
    PlaceViewModel mViewModel;

    public ClearArchiveDialogPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    public ClearArchiveDialogPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    public ClearArchiveDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ClearArchiveDialogPreference(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onClick() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(R.string.archive_delete_title_ask);
        dialog.setMessage(R.string.archive_delete_ask);
        dialog.setCancelable(true);
        dialog.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mViewModel = new PlaceViewModel(((Activity) context).getApplication());
                mViewModel.deleteFromArchiveAll();

                Toast.makeText(getContext(), "Архив очищен.", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog al = dialog.create();
        al.show();
    }
}
