package com.coding.meet.formvalidation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edName = findViewById<TextInputEditText>(R.id.edName)
        val edNameL = findViewById<TextInputLayout>(R.id.edNameL)

        edName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateName(edName, edNameL)
            }

        })


        val edEmail = findViewById<TextInputEditText>(R.id.edEmail)
        val edEmailL = findViewById<TextInputLayout>(R.id.edEmailL)

        edEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateEmail(edEmail, edEmailL)
            }

        })


        val edPassword = findViewById<TextInputEditText>(R.id.edPassword)
        val edPasswordL = findViewById<TextInputLayout>(R.id.edPasswordL)

        edPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validatePassword(edPassword, edPasswordL)
            }

        })


        val edConPassword = findViewById<TextInputEditText>(R.id.edConPassword)
        val edConPasswordL = findViewById<TextInputLayout>(R.id.edConPasswordL)
        edConPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(s: Editable) {
                validateConPassword(edPassword, edConPassword, edConPasswordL)
            }

        })


        val validateBtn = findViewById<Button>(R.id.validateBtn)
        validateBtn.setOnClickListener {
            if (validateName(edName, edNameL)
                && validateEmail(edEmail, edEmailL)
                && validatePassword(edPassword, edPasswordL)
                && validateConPassword(edPassword, edConPassword, edConPasswordL)
            ) {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun validateConPassword(
        edPassword: TextInputEditText,
        edConPassword: TextInputEditText,
        edConPasswordL: TextInputLayout,
    ): Boolean {

        return when {
            edConPassword.text.toString().trim().isEmpty() -> {
                edConPasswordL.error = "Required"
                false
            }
            edConPassword.text.toString().trim().length < 8 || edConPassword.text.toString()
                .trim().length > 10 -> {
                edConPasswordL.error = "Password must be 8 to 10 Character!"
                false

            }
            edPassword.text.toString().trim() != edConPassword.text.toString().trim() -> {
                edConPasswordL.error = "Password Don't Match!"
                false
            }
            else -> {
                edConPasswordL.error = null
                true
            }
        }
    }

    private fun validatePassword(
        edPassword: TextInputEditText,
        edPasswordL: TextInputLayout,
    ): Boolean {
        return when {
            edPassword.text.toString().trim().isEmpty() -> {
                edPasswordL.error = "Required"
                false
            }
            edPassword.text.toString().trim().length < 8 || edPassword.text.toString()
                .trim().length > 10 -> {
                edPasswordL.error = "Password must be 8 to 10 Character!"
                false
            }
            else -> {
                edPasswordL.error = null
                true
            }
        }
    }

    private fun validateEmail(edEmail: TextInputEditText, edEmailL: TextInputLayout): Boolean {
        val emailPattern = Regex("[a-zA-Z\\d._-]+@[a-z]+\\.+[a-z]+")
        return when {
            edEmail.text.toString().trim().isEmpty() -> {
                edEmailL.error = "Required"
                false
            }
            !edEmail.text.toString().trim().matches(emailPattern) -> {
                edEmailL.error = "Valid E-mail"
                false
            }
            else -> {
                edEmailL.error = null
                true
            }
        }
    }

    private fun validateName(edName: EditText, edNameL: TextInputLayout): Boolean {
        return when {
            edName.text.toString().trim().isEmpty() -> {
                edNameL.error = "Required"
                false
            }
            else -> {
                edNameL.error = null
                true
            }
        }
    }
}