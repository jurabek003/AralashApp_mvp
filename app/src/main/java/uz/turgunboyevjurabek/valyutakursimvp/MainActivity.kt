package uz.turgunboyevjurabek.valyutakursimvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.findNavController
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import uz.turgunboyevjurabek.valyutakursimvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        meowButtonNavigationCreate()
        AppObject.binding = binding
        AppObject.fragmentManager = supportFragmentManager
    }

    override fun onResume() {
        super.onResume()
    }
    private fun meowButtonNavigationCreate() {
        val navController = findNavController(R.id.my_navigation_host)
        binding.meowButtonNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_home))
        binding.meowButtonNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_notification))
        binding.meowButtonNavigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_note))
        binding.meowButtonNavigation.add(MeowBottomNavigation.Model(4,R.drawable.ic_info))

        binding.meowButtonNavigation.show(1)

        // meowButtonNavigation ni aytimlari bosilishini eshitish uchun
        binding.meowButtonNavigation.setOnClickMenuListener { t->
            when(t.id){
                1->{
                    navController.popBackStack()
                    navController.navigate(R.id.homekFragment)
                    binding.meowButtonNavigation.show(1)
                }
                2->{
                    navController.popBackStack()
                    navController.navigate(R.id.notifationFragment)
                    binding.meowButtonNavigation.show(2)
                }
                3->{
                    navController.popBackStack()
                    navController.navigate(R.id.accountFragment)
                    binding.meowButtonNavigation.show(3)
                }
                4->{
                    navController.popBackStack()
                    navController.navigate(R.id.infoFragment)
                    binding.meowButtonNavigation.show(4)
                }
                else -> {
                    navController.popBackStack()
                    navController.navigate(R.id.homekFragment)
                    binding.meowButtonNavigation.show(1)
                }
            }

        }

    }
}