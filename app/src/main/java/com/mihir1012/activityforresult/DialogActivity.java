package com.mihir1012.activityforresult;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DialogActivity extends AppCompatDialogFragment {
    private EditText ed,eventDate;
    private Calendar myCal = Calendar.getInstance();
    private DialogListener dialogListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            dialogListener =   (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must be implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view =  inflater.inflate(R.layout.layout_dialog,null);
        ed = view.findViewById(R.id.dialog_edit);
        eventDate = view.findViewById(R.id.dialog_date);
         final DatePickerDialog.OnDateSetListener datePick = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCal.set(Calendar.YEAR,year);
                myCal.set(Calendar.MONTH,monthOfYear);
                myCal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                updateLabel();
            }
        };
        eventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(),datePick , myCal
                        .get(Calendar.YEAR), myCal.get(Calendar.MONTH),
                        myCal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        builder.setView(view)
        .setTitle("Login")
        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name  = ed.getText().toString();
                dialogListener.applyText(name);
            }
         });

         return builder.create();

    }

    public interface DialogListener{
        void applyText(String name);
    }

    private void updateLabel(){
        String myFormat = "dd/mm/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        eventDate.setText(sdf.format(myCal.getTime()));
    }
}
