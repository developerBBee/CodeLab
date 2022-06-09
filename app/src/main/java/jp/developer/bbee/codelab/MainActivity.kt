package jp.developer.bbee.codelab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val SEPALATER: String = ": "
    private var mYourNameString: String = "You"

    private lateinit var resultText: TextView
    private lateinit var yourNameEdit: EditText

    private lateinit var rollButton: Button
    private lateinit var countUpButton: Button
    private lateinit var nameUpdateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)
        yourNameEdit = findViewById(R.id.yourNameEdit)

        rollButton = findViewById(R.id.rollButton)
        rollButton.setOnClickListener { rollDice() }
        countUpButton = findViewById(R.id.countUpButton)
        countUpButton.setOnClickListener { countUp() }
        nameUpdateButton = findViewById(R.id.nameUpdateButton)
        nameUpdateButton.setOnClickListener { nameUpdate() }
    }

    /**
     * Generate random number 1..6
     */
    private fun rollDice() {
        val randomInt: Int = (1..6).random()
        setResultText(randomInt)
    }

    /**
     * If it does not exceed 6, the number is incremented by 1.
     */
    private fun countUp() {
        val resultText: TextView = findViewById(R.id.resultText)
        val resultStr: String = resultText.text.toString()
        var resultNum: Int = resultStr
            .replace(resultStr.substring(0, resultStr.indexOf(SEPALATER) + SEPALATER.length), "")
            .toIntOrNull() ?: 0
        if (resultNum < 6) {
            resultNum++
        }
        setResultText(resultNum)
    }

    /**
     * Update the text in resultText with the name and number
     * @param num New value to update
     */
    private fun setResultText(num: Int) {
        val resultString: String = mYourNameString + SEPALATER + num.toString()
        resultText.text = resultString
        Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show()
    }

    /**
     * Update to the name entered in yourNameEdit
     */
    private fun nameUpdate() {
        synchronized(mYourNameString) {
            val oldNameString: String = mYourNameString
            val newNameString: String = yourNameEdit.text.toString()
            if ("".equals(newNameString)) return

            resultText.text = resultText.text.toString().replaceFirst(oldNameString, newNameString)
            mYourNameString = newNameString
        }
    }
}