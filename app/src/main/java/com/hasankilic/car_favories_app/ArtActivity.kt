package com.hasankilic.car_favories_app

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.hasankilic.car_favories_app.databinding.ActivityArtBinding
import java.lang.Exception
import java.util.jar.Manifest

class ArtActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher:ActivityResultLauncher<String>
    var selectedBitmap: Bitmap? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityArtBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        registerLauncher()

    }




    fun saveButtonClicked(view: View){



    }

    fun selectImage(view: View){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            //Android 33+ -> READ_MEDIA_IMAGES
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_MEDIA_IMAGES)!=PackageManager.PERMISSION_GRANTED/*izin verilmediyse*/) {//izin durumu kontrolu read_external_stroage
                // kontrolu için{
                //rationale
                /*izin isteme*/
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_MEDIA_IMAGES)){//izin alma mntıgını kullanıcıya gosterıyımmı
                    /*BUTON İSMİ*/
                    Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give PERMİSSİON" ,View.OnClickListener {

                        /*belirsiz süre. yani kullanıcı okey tusuna basan kaadar goster*/
                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                    }).show()
                }else{//izin isteme
                    //request permission
                    permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                }
            }else{
                val intenToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI/*secilen görselin telefonda nerede kayıtlı oldugunu gosteroyo gıdıyo*/)//aksiyon yapmak actıonpıck-> gidip almak
                activityResultLauncher.launch(intenToGallery)//activteyi baslatma
                //intent

            }
        }else{
            //Android 32- ->READ_EXTERNAL_STORAGE
            if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED/*izin verilmediyse*/) {//izin durumu kontrolu read_external_stroage
                // kontrolu için{
                //rationale
                /*izin isteme*/
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){//izin alma mntıgını kullanıcıya gosterıyımmı
                    /*BUTON İSMİ*/
                    Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give PERMİSSİON" ,View.OnClickListener {

                        /*belirsiz süre. yani kullanıcı okey tusuna basan kaadar goster*/
                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    }).show()
                }else{//izin isteme
                    //request permission
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }else{
                val intenToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI/*secilen görselin telefonda nerede kayıtlı oldugunu gosteroyo gıdıyo*/)//aksiyon yapmak actıonpıck-> gidip almak
                activityResultLauncher.launch(intenToGallery)//activteyi baslatma
                //intent

            }
        }


    }
    private fun registerLauncher(){//bu fonksiyonu hiç anlamadım --- galeriye gitmek ve galeriden görseli secmek

        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if (result.resultCode== RESULT_OK){
                val intentFromResult=result.data
                if (intentFromResult!=null){
                    val imageData= intentFromResult.data
                    //binding.imageView.setImageURI(imageData)
                    if (imageData!=null){
                    try {
                        if (Build.VERSION.SDK_INT>=28){
                            val source = ImageDecoder.createSource(this@ArtActivity.contentResolver,imageData)
                            selectedBitmap=ImageDecoder.decodeBitmap(source)
                            binding.imageView.setImageBitmap(selectedBitmap)

                        }else{
                            selectedBitmap=MediaStore.Images.Media.getBitmap(contentResolver,imageData)
                            binding.imageView.setImageBitmap(selectedBitmap)
                        }

                    } catch (e:Exception){
                        e.printStackTrace()
                    }
                    }

                }
            }


        }

        permissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){result->//izin iste
            if (result){
//izin verildiyse
                val intenToGallery=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI/*secilen görselin telefonda nerede kayıtlı oldugunu gosteroyo gıdıyo*/)//aksiyon yapmak actıonpıck-> gidip almak

                activityResultLauncher.launch(intenToGallery)//activteyi baslatma

            }else{
                //izin verilmediyse

                Toast.makeText(this@ArtActivity,"permission nedeed",Toast.LENGTH_LONG).show()
            }


        }

    }

}