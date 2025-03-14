package com.example.appgorjeta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appgorjeta.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClean.setOnClickListener {
            limparCampos()
        }

        binding.btnDone.setOnClickListener {
            calcularGorjeta()
        }
    }

    private fun limparCampos() {
        binding.tieTotal.text?.clear()
        binding.tieNumberOfPeople.text?.clear()
        binding.tvResult.text = ""
        binding.rgPercentage.clearCheck()
    }

    private fun calcularGorjeta() {
        val totalTableText = binding.tieTotal.text.toString()
        val nPeopleText = binding.tieNumberOfPeople.text.toString()

        if (totalTableText.isEmpty() || nPeopleText.isEmpty()) {
            binding.tvResult.text = getString(R.string.fill_all_fields)
            return
        }

        val totalTable: Float
        val nPeople: Int

        try {
            totalTable = totalTableText.toFloat()
            nPeople = nPeopleText.toInt()
        } catch (e: NumberFormatException) {
            binding.tvResult.text = getString(R.string.insert_valid_values)
            return
        }

        if (nPeople == 0) {
            binding.tvResult.text = getString(R.string.people_greater_than_zero)
            return
        }

        val percentage = when {
            binding.rbOptionOne.isChecked -> 10
            binding.rbOptionTwo.isChecked -> 15
            binding.rbOptionThree.isChecked -> 20
            else -> {
                binding.tvResult.text = getString(R.string.select_percentage)
                return
            }
        }


        val tipAmount = totalTable * percentage / 100
        val totalWithTips = totalTable + tipAmount
        val tipPerPerson = tipAmount / nPeople


        binding.tvResult.text = getString(
            R.string.total_with_tips, totalWithTips
        ) + "\n" + getString(R.string.tip_per_person, tipPerPerson)