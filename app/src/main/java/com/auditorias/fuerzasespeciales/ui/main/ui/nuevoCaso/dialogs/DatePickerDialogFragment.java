package com.auditorias.fuerzasespeciales.ui.main.ui.nuevoCaso.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

public class DatePickerDialogFragment extends DialogFragment {
    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day_of_month = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), STYLE_NORMAL, (DatePickerDialog.OnDateSetListener) getTargetFragment(), year,  month, day_of_month);
        dialog.getDatePicker().setTag(getTag());
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        return dialog;
    }
}