package org.tasks.activities.attribution

import android.os.Bundle
import androidx.activity.viewModels
import butterknife.ButterKnife
import com.google.android.material.composethemeadapter.MdcTheme
import dagger.hilt.android.AndroidEntryPoint
import org.tasks.R
import org.tasks.compose.AttributionList.AttributionList
import org.tasks.databinding.ActivityAttributionsBinding
import org.tasks.injection.ThemedInjectingAppCompatActivity

@AndroidEntryPoint
class AttributionActivity : ThemedInjectingAppCompatActivity() {
    val viewModel: AttributionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAttributionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ButterKnife.bind(this)
        with(binding.toolbar.toolbar) {
            setTitle(R.string.third_party_licenses)
            setNavigationIcon(R.drawable.ic_outline_arrow_back_24px)
            setNavigationOnClickListener { finish() }
            themeColor.apply(this)
        }
        viewModel.attributions.observe(this) {
            binding.compose.setContent {
                MdcTheme {
                    AttributionList(it)
                }
            }
        }
    }
}