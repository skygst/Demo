/*===============================================================================
Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of QUALCOMM Incorporated, registered in the United States 
and other countries. Trademarks of QUALCOMM Incorporated are used with permission.
===============================================================================*/

package com.ebodoo.raz.samples.SampleApplication.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


// Support class for the Vuforia samples applications.
// Exposes functionality for loading a texture from the APK.
public class Texture
{
    private static final String LOGTAG = "Vuforia_Texture";
    
    public int mWidth;          // The width of the texture.
    public int mHeight;         // The height of the texture.
    public int mChannels;       // The number of channels.
    public ByteBuffer mData;    // The pixel data.
    public int[] mTextureID = new int[1];
    public boolean mSuccess = false;
    
  //---------------需要修改-----------修改为从sd卡加载图片-----------
    public static Texture loadTextureFromSdcard(String fileName)
        {
            InputStream inputStream = null;
            try
            {
                
                //第二种方式:从SD卡中得到图片(方法2)
                inputStream=getBitmapInputStreamFromSDCard(fileName);
                
//                inputStream = assets.open(fileName, AssetManager.ACCESS_BUFFER);
                
                BufferedInputStream bufferedStream = new BufferedInputStream(
                    inputStream);
                Bitmap bitMap = BitmapFactory.decodeStream(bufferedStream);
                
                int[] data = new int[bitMap.getWidth() * bitMap.getHeight()];
                bitMap.getPixels(data, 0, bitMap.getWidth(), 0, 0,
                    bitMap.getWidth(), bitMap.getHeight());
                
                return loadTextureFromIntBuffer(data, bitMap.getWidth(),
                    bitMap.getHeight());
            } catch (Exception e)
            {
                Log.e(LOGTAG, "Failed to log texture '" + fileName + "' from APK");
                Log.i(LOGTAG, e.getMessage());
                return null;
            }
        }
    //读取SD卡下的图片
    private static InputStream getBitmapInputStreamFromSDCard(String fileName){
      if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
        
        String filePath=Constant.path_raz_image+fileName;
        File file=new File(filePath);
        try {
          FileInputStream fileInputStream=new FileInputStream(file);
          return fileInputStream;
        } catch (Exception e) {
          e.printStackTrace();
        }
        
      }
        return null;
    }
    /* Factory function to load a texture from the APK. */
    public static Texture loadTextureFromApk(String fileName,
        AssetManager assets)
    {
        InputStream inputStream = null;
        try
        {
            inputStream = assets.open(fileName, AssetManager.ACCESS_BUFFER);
            
            BufferedInputStream bufferedStream = new BufferedInputStream(
                inputStream);
            Bitmap bitMap = BitmapFactory.decodeStream(bufferedStream);
            
            int[] data = new int[bitMap.getWidth() * bitMap.getHeight()];
            bitMap.getPixels(data, 0, bitMap.getWidth(), 0, 0,
                bitMap.getWidth(), bitMap.getHeight());
            
            return loadTextureFromIntBuffer(data, bitMap.getWidth(),
                bitMap.getHeight());
        } catch (IOException e)
        {
            Log.e(LOGTAG, "Failed to log texture '" + fileName + "' from APK");
            Log.i(LOGTAG, e.getMessage());
            return null;
        }
    }
    
    
    public static Texture loadTextureFromIntBuffer(int[] data, int width,
        int height)
    {
        // Convert:
        int numPixels = width * height;
        byte[] dataBytes = new byte[numPixels * 4];
        
        for (int p = 0; p < numPixels; ++p)
        {
            int colour = data[p];
            dataBytes[p * 4] = (byte) (colour >>> 16); // R
            dataBytes[p * 4 + 1] = (byte) (colour >>> 8); // G
            dataBytes[p * 4 + 2] = (byte) colour; // B
            dataBytes[p * 4 + 3] = (byte) (colour >>> 24); // A
        }
        
        Texture texture = new Texture();
        texture.mWidth = width;
        texture.mHeight = height;
        texture.mChannels = 4;
        
        texture.mData = ByteBuffer.allocateDirect(dataBytes.length).order(
            ByteOrder.nativeOrder());
        int rowSize = texture.mWidth * texture.mChannels;
        for (int r = 0; r < texture.mHeight; r++)
            texture.mData.put(dataBytes, rowSize * (texture.mHeight - 1 - r),
                rowSize);
        
        texture.mData.rewind();
        
        // Cleans variables
        dataBytes = null;
        data = null;
        
        texture.mSuccess = true;
        return texture;
    }
}
