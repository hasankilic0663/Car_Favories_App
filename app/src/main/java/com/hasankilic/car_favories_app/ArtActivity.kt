package com.hasankilic.car_favories_app

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.hasankilic.car_favories_app.databinding.ActivityArtBinding
import java.util.jar.Manifest

class ArtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityArtBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
    }




    fun saveButtonClicked(view: View){



    }

    fun selectImage(view: View){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED/*izin verilmediyse*/) {//izin durumu kontrolu read_external_stroage
                                                                                                                                                                            // kontrolu için{
            //rationale
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){//izin alma mntıgını kullanıcıya gosterıyımmı
                                                                                                                 /*BUTON İSMİ*/
                Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give PERMİSSİON" ,View.OnClickListener {
                                                                                 /*belirsiz süre. yani kullanıcı okey tusuna basan kaadar goster*/
                }).show()

            }else{
                //request permission
            }



        }else{
            val intenToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI/*secilen görselin telefonda nerede kayıtlı oldugunu gosteroyo gıdıyo*/)//aksiyon yapmak actıonpıck-> gidip almak

            //intent


        }



    }
}