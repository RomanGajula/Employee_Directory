package com.example.employee_directory.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.employee_directory.view.AddEmployee
import com.example.employee_directory.view.Home

class CancelDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Are you sure you want to cancel?")
                .setCancelable(true)
                .setPositiveButton("Cancel") { dialog, id ->
                    Toast.makeText(
                        activity, "Add an employee",
                        Toast.LENGTH_LONG
                    ).show()
                }
                .setNegativeButton("Yes",
                    DialogInterface.OnClickListener { dialog, id ->
                        val intent = Intent(context, Home::class.java)
                        startActivity(intent)
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}