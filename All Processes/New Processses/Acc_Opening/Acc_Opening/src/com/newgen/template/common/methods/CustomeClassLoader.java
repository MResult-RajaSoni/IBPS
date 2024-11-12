/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.newgen.template.common.methods;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 *
 * @author Amol
 */
public class CustomeClassLoader extends URLClassLoader {
    public CustomeClassLoader(URL[] urls, ClassLoader parent){
        super(urls,parent);
    }
    public void addJarToClassPath(String jarPath) throws MalformedURLException{
        File file =new File(jarPath);
        URL url =file.toURI().toURL();
        this.addURL(url);
        
    }
    
}
