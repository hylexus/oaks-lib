package io.github.hylexus.oaks.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * @author hylexus
 * Created At 2019-07-24 20:43
 */
public abstract class ClassUtils {

    private static final String CLASS_FILE_EXTENSION = ".class";
    private static final String JAR_FILE_EXTENSION = ".jar";

    private static final Predicate<Class<?>> CLASS_PREDICATE = cls -> true;
    private static final Predicate<String> CLASS_NAME_FILTER = name -> true;
    private static final ClassLoader SYSTEM_CLASS_LOADER = ClassLoader.getSystemClassLoader();

    public static List<Class<?>> loadClass(String path) throws IOException {
        return loadClass(path, SYSTEM_CLASS_LOADER);
    }

    public static List<Class<?>> loadClass(String path, ClassLoader parentClassLoader) throws IOException {
        return loadClass(path, CLASS_NAME_FILTER, CLASS_PREDICATE, parentClassLoader);
    }

    public static List<Class<?>> loadClass(String path, Predicate<Class<?>> classFilter) throws IOException {
        return loadClass(path, classFilter, SYSTEM_CLASS_LOADER);
    }

    public static List<Class<?>> loadClass(String path, Predicate<Class<?>> classFilter, ClassLoader parentClassLoader) throws IOException {
        return loadClass(path, CLASS_NAME_FILTER, classFilter, parentClassLoader);
    }

    public static List<Class<?>> loadClass(String path, Predicate<String> classNameFilter, Predicate<Class<?>> classFilter) throws IOException {
        return loadClass(path, classNameFilter, classFilter, SYSTEM_CLASS_LOADER);
    }

    public static List<Class<?>> loadClass(String path, Predicate<String> classNameFilter, Predicate<Class<?>> classFilter, ClassLoader parentClassLoader) throws IOException {
        final List<Class<?>> ret = new ArrayList<>();
        final File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            return ret;
        }

        final Stack<File> stack = new Stack<>();
        stack.push(dir);
        while (!stack.isEmpty()) {
            final File currentDir = stack.pop();
            final File[] files = currentDir.listFiles(name -> name.isDirectory() || name.getName().endsWith(JAR_FILE_EXTENSION));

            if (files == null || files.length <= 0) {
                continue;
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    stack.push(file);
                    continue;
                }
                final List<Class<?>> classList = loadFromJarFile(file, classNameFilter, classFilter, parentClassLoader);
                ret.addAll(classList);
            }
        }
        return ret;
    }

    private static List<Class<?>> loadFromJarFile(File file, Predicate<String> classNameFilter, Predicate<Class<?>> classFilter, ClassLoader parentClassLoader) throws IOException {
        final URL url = file.toURI().toURL();
        final JarFile jarFile = new JarFile(file);

        final URLClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[]{url}, parentClassLoader);

        return jarFile.stream()
                .filter(jarEntry -> !jarEntry.isDirectory())
                .map(JarEntry::getName)
                .filter(name -> name.endsWith(CLASS_FILE_EXTENSION))
                .map(name -> name.replace(File.separator, "."))
                .map(name -> name.substring(0, name.length() - CLASS_FILE_EXTENSION.length()))
                .filter(classNameFilter)
                .map(className -> {
                    try {
                        return urlClassLoader.loadClass(className);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("failed to load class [" + className + " ]", e);
                    }
                })
                .filter(classFilter)
                .collect(Collectors.toList());
    }

}
