package com.example.parkspace.view.fragments

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.widget.addTextChangedListener
import com.example.parkspace.R
import com.example.parkspace.databinding.FragmentVerifyEmailBinding

class VerifyEmail : Fragment() {
    companion object {
        private const val TEST_VERIFY_CODE = "6768"
    }

    private val constraintLayoutRoot: ConstraintLayout by lazy { binding.constraintLayoutRoot }
    private val code1: EditText by lazy { binding.code1 }
    private val code2: EditText by lazy { binding.code2 }
    private val code3: EditText by lazy { binding.code3 }
    private val code4: EditText by lazy { binding.code4 }

    private var _binding: FragmentVerifyEmailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVerifyEmailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()

        initFocus()

        binding.push.setOnClickListener { replaceFragment(CreateNewPass()) }
        binding.backarrow.setOnClickListener{ activity?.finish() }
    }

    private fun setListener(){
        constraintLayoutRoot.setOnClickListener {
            val inputManager = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(constraintLayoutRoot.windowToken, 0)
        }

        setTextChangeListener(fromEditText = code1, targetEditText = code2)
        setTextChangeListener(fromEditText = code2, targetEditText = code3)
        setTextChangeListener(fromEditText = code3, targetEditText = code4)
        setTextChangeListener(fromEditText = code4, done = { verifyOTPCode() })

        setKeyListener(fromEditText = code2, backToEditText = code1)
        setKeyListener(fromEditText = code3, backToEditText = code2)
        setKeyListener(fromEditText = code4, backToEditText = code3)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.framelay,fragment)
        fragmentTransaction?.commit()
    }

    private fun initFocus(){
        code1.isEnabled = true

        code1.postDelayed({
            code1.requestFocus()
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(code1,InputMethodManager.SHOW_FORCED)
        },500)
    }

    private fun reset(){
        code1.isEnabled = false
        code2.isEnabled = false
        code3.isEnabled = false
        code4.isEnabled = false

        code1.setText("")
        code2.setText("")
        code3.setText("")
        code4.setText("")

        initFocus()
    }

    private fun setTextChangeListener(fromEditText: EditText, targetEditText: EditText? = null, done: (() -> Unit)? = null){
        fromEditText.addTextChangedListener {
            it?.let { string ->
                if (string.isNotEmpty()){
                    targetEditText?.let { editText ->
                        editText.isEnabled = true
                        editText.requestFocus()
                    } ?: run{
                        done ?.let { done ->
                            done()
                        }
                    }

                    fromEditText.clearFocus()
                    fromEditText.isEnabled = false

                }
            }
        }
    }

    private fun setKeyListener(fromEditText: EditText, backToEditText: EditText){
        fromEditText.setOnKeyListener{ _,_, event ->

            if (null != event && KeyEvent.KEYCODE_DEL == event.keyCode){
                backToEditText.isEnabled = true
                backToEditText.requestFocus()
                backToEditText.setText("")

                fromEditText.clearFocus()
                fromEditText.isEnabled = false
            }

            false
        }
    }

    private fun verifyOTPCode(){
        val otpCode= binding.code1.text.toString().trim() +
                binding.code2.text.toString().trim() +
                binding.code3.text.toString().trim() +
                binding.code4.text.toString().trim()

        if (4 != otpCode.length) {
            return
        }

        if (otpCode == TEST_VERIFY_CODE){

            Toast.makeText(context,"success, should do next", Toast.LENGTH_LONG).show()

            val inputManager = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(constraintLayoutRoot.windowToken, 0)

            return
        }

        Toast.makeText(context,"Error, input again", Toast.LENGTH_SHORT).show()
        reset()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}