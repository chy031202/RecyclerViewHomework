package com.example.recyclerviewhomework

import android.content.ClipData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemDialog(private val itemPos: Int = -1) : BottomSheetDialogFragment() {
    private val viewModel by activityViewModels<MyViewModel> ()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_dialog_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonOK).setOnClickListener{
            val item = Item(
                view.findViewById<EditText>(R.id.editTextName).text.toString(),
                view.findViewById<EditText>(R.id.editTextAddress).text.toString()

            )
            if(itemPos < 0)
                viewModel.addItem(item)
            else
                viewModel.updateItem(itemPos, item)
            dismiss()
        }
//        view.findViewById<EditText>(R.id.editTextName).setText(item.firstname)
//        view.findViewById<EditText>(R.id.editTextAddress).setText(item.lastname)
    }
}