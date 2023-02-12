package kr.co.pureum.views.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import kr.co.pureum.databinding.DialogAccessRightsPhotoBinding

class AccessDialog: DialogFragment() {
    private lateinit var binding : DialogAccessRightsPhotoBinding

    interface AccessDialogListener {
        fun onConfirm()
    }

    private lateinit var accessDialogListener: AccessDialogListener

    fun setCustomListener(listener: AccessDialogListener){
        accessDialogListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogAccessRightsPhotoBinding.inflate(inflater)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        isCancelable = false
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            accessDialogOkBt.setOnClickListener {
                accessDialogListener.onConfirm()
                dismiss()
            }
        }
    }
}