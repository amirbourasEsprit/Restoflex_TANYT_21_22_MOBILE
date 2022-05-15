package  utils.FileChooser;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.system.NativeInterface;

public interface FileChooserNative extends NativeInterface {
    public boolean showNativeChooser(String accept, boolean multi);
}
