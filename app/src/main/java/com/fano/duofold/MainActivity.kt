package com.fano.duofold
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
class MainActivity: ComponentActivity() {
 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  val duo=DuoView(this); setContentView(duo)
  lifecycleScope.launch { WindowInfoTracker.getOrCreate(this@MainActivity).windowLayoutInfo(this@MainActivity).collectLatest { info -> duo.setFoldFeature(info.displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()) } }
 }
}