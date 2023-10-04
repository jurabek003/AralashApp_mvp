package uz.turgunboyevjurabek.valyutakursimvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        meowButtonNavigationCreate()


    }

    private fun meowButtonNavigationCreate() {
        binding.meowButtonNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_home))
        binding.meowButtonNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_notification))
        binding.meowButtonNavigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_account))
        binding.meowButtonNavigation.add(MeowBottomNavigation.Model(4,R.drawable.ic_info))

    }
}