
package com.webskitters.webskitters_machine_test.view.dialog;
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.webskitters.webskitters_machine_test.R


class ProgressDialog (context: Context?) : AlertDialog(context) {
    override fun show() {
        super.show()
        setContentView(R.layout.dialog_progress)
    }

    init {
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}