package my.edu.tarc.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.insurance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //no longer need to refer to layout
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener {
            var premium: Int = 0
            var extra: Int = 0
            var total: Int = 0

            val age = binding.spinnerAge.selectedItemPosition
            when (age) { //less than 17
                0 -> premium = 60
                1 -> premium = 70
                2 -> premium = 90
                3 -> premium = 120
                else -> premium = 150
            }

//            if(age == 0){
//                premium = 60
//            }
//            else if(age == 1){
//                premium = 70
//            }
//            else if(age == 2){
//                premium = 90
//            }
//            else if(age == 3){
//                premium = 120
//            }
//            else if(age == 4){
//                premium = 150
//            }
//            else{
//                premium = 150
//            }

            val gender = binding.radioGroupGender.checkedRadioButtonId
            if (gender == binding.radioButtonMale.id) {
                //extra premium for male
                when (age) { //less than 17
                    0 -> extra = 0
                    1 -> extra = 50
                    2 -> extra = 100
                    3 -> extra = 150
                    else -> premium = 200
                }
            }

            val smoker = binding.checkBoxSmoker.isChecked //boolean value
            if (smoker) {
                //calculate extra premium for smoker
                when (age) { //less than 17
                    0 -> extra += 0
                    1 -> extra += 100
                    2 -> extra += 150
                    3 -> extra += 200
                    4 -> extra += 250
                    else -> extra += 300
                }
            }
            total = premium + extra
            //pass value to class
            binding.myPremium = Premium(premium, extra, total)
        }

        binding.buttonReset.setOnClickListener {
            binding.spinnerAge.setSelection(0)
            binding.radioGroupGender.clearCheck()
            binding.checkBoxSmoker.isChecked = false
            binding.myPremium = Premium(premium = 0, extra = 0, total = 0)
        }
    }
}