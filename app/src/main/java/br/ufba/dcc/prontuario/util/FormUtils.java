package br.ufba.dcc.prontuario.util;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import androidx.appcompat.app.AlertDialog;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormUtils {
    public static void initDatePicker(final Context context, final EditText editText) {
        editText.setKeyListener(null);

        final Calendar myCalendar = Calendar.getInstance();

        /*
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        DatePicker picker = new DatePicker(context);
        picker.setCalendarViewShown(false);

        builder.setTitle("Informe a data");
        builder.setView(picker);
        builder.setCancelable(false);
        */

        /*builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });*/

        /*builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });*/

        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH, i1);
                myCalendar.set(Calendar.DAY_OF_MONTH, i2);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                editText.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    DatePickerDialog datePicker = new DatePickerDialog(context, listener,
                            myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));

                    datePicker.setTitle("Informe a data");
                    datePicker.show();

                    //builder.show();
                }

                return false;
            }
        });
    }

    public static void initTimePicker(final Context context, final EditText editText) {
        editText.setKeyListener(null);

        final Calendar myCalendar = Calendar.getInstance();

        final TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                myCalendar.set(Calendar.HOUR_OF_DAY, i);
                myCalendar.set(Calendar.MINUTE, i1);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                editText.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    TimePickerDialog timePicker = new TimePickerDialog(context, listener,
                            myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true);

                    timePicker.setTitle("Informe a hora");
                    timePicker.show();
                }

                return false;
            }
        });
    }

    public static int getSpinnerSelectedIndex(Spinner spinner, String selectedValue) {
        int index = 0;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(selectedValue)) {
                index = i;
                break;
            }
        }

        return index;
    }
}
