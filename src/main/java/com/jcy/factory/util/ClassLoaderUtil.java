package com.jcy.factory.util;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

public class ClassLoaderUtil {

    @SneakyThrows
    public static Class getClass(String url, String classFullName) {
        URL[] urls = new URL[1];
        URLStreamHandler streamHandler = null;
        File classPath = new File(url);
        String repository1 = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
        urls[0] = new URL(null, repository1, streamHandler);
        URLClassLoader loader = new URLClassLoader(urls);
        return loader.loadClass(classFullName);

    }

    public static void main(String[] args) {
        Class aClass = getClass("E:\\git-new-res\\crud-generator\\crud-example\\target\\classes", "com.lhy.example.user.model.User");
        System.out.println(aClass.getSimpleName());
    }
}