package com.example.escanerqr

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.escanerqr.databinding.ActivityMainBinding
import android.widget.Toast

import com.journeyapps.barcodescanner.ScanContract

import com.journeyapps.barcodescanner.ScanOptions

import androidx.activity.result.ActivityResultLauncher
import com.example.escanerqr.fragment.TextoFragment

import com.example.escanerqr.fragment.WebViewFragment
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.journeyapps.barcodescanner.ScanIntentResult


class MainActivity : AppCompatActivity() {

    private lateinit var barcodeLauncher: ActivityResultLauncher<ScanOptions>
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        barcodeLauncher = registerForActivityResult(
            ScanContract()
        ) { result: ScanIntentResult ->
            if (result.contents == null) {
                Toast.makeText(   this,   "Cancelado: " + result.contents,     Toast.LENGTH_LONG  ).show()
            } else {
                    if(result.contents.contains("mapas")){
                    val uri = Uri.parse(result.contents)
                    val intent= Intent(Intent.ACTION_VIEW,uri)
                    startActivity(intent)

                }else
                    if(result.contents.contains("http")){
                    val uri = Uri.parse(result.contents).toString()
                    val bundle=Bundle()
                    bundle.putString("param1", uri )
                    val newFragment = WebViewFragment()
                    newFragment.arguments=bundle
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.contenedor, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()

                }else {
                    //if(result.contents.contains("")){
                    val uri = Uri.parse(result.contents).toString()
                    val bundle=Bundle()
                    bundle.putString("param1", uri)
                    val newFragment = TextoFragment()
                    newFragment.arguments=bundle
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.contenedor, newFragment)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }

            }
        }

        //generarCodigoQr()
    }

    /*fun mostrarFragment(opcion :Int, datos:String){



        when(opcion){
            1-> {
                val bundle=Bundle()
                bundle.putString("param1", uri )
                val newFragment = WebViewFragment()

            }
            2->{

            }


        }


        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedor, newFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }*/

    fun generarCodigoQr(){

        try{
            val barcodeEncoder = BarcodeEncoder()
            val bitmap : Bitmap = barcodeEncoder.encodeBitmap("Mensaje del QR", BarcodeFormat.QR_CODE,400, 400)
            binding.imgQr.setImageBitmap(bitmap)


        } catch (e: Exception){
            Log.e("Mensaje", e.message.toString())

        }


    }

    fun scan(view: android.view.View) {

        val options = ScanOptions()
        options.setOrientationLocked(false)
        options.setPrompt("Enfoca el codigo qr")
        options.setBeepEnabled(true)
        barcodeLauncher.launch(options)
        //barcodeLauncher.launch(ScanOptions())

    }

}
