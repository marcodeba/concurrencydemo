package com.demo.javademo.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directory {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }

    public static TreeInfo walk(String start, String regex) {
        // Begin recursion
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(File start, String regex) {
        return recurseDirs(start, regex);
    }

    static TreeInfo recurseDirs(File startDir, String regex) {
        TreeInfo result = new TreeInfo();
        for (File item : startDir.listFiles()) {
            if (item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else {// Regular file
                if (item.getName().matches(regex))
                    result.files.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            TreeInfo walk = walk("/Users/marcopan/mysourcecode/javademo/src/main/java/com/demo/javademo/io/file", ".*");
            List<File> dirs = walk.dirs;
            for (File dir : dirs) {
                System.out.println(dir.getName());
            }

            List<File> files = walk.files;
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            for (String arg : args) {
                System.out.println(walk(arg, ".*"));
            }
        }
    }

    // A two-tuple for returning a pair of objects:
    public static class TreeInfo implements Iterable<File> {
        // 文件集合
        public List<File> files = new ArrayList<>();
        // 目录集合
        public List<File> dirs = new ArrayList<>();

        // The default iterable element is the file list:
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }
    }
}
