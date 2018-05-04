package com.paranhaslett.toolbox.loader;

import com.paranhaslett.toolbox.model.Artifact;
import com.paranhaslett.toolbox.tools.Tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmaLoader implements Loader {

    private List<String> content = new ArrayList<>();
    private File file;

    @Override
    public String getText(String key) {
        if (getName().equals("book") && key.equals("title")) {
            for (String line : content) {
                if (line.contains("#")) {
                    int pos = line.lastIndexOf('#');
                    return line.substring(pos + 1);
                }
            }
        }

        return null;
    }

    @Override
    public void setText(String key, String value) {
        if (key.equals("title")) {
            content.add("##" + value);
        }

    }

    @Override
    public List<Loader> getChildren(String... childrenKeys) {
        HashSet<String> keySet = new HashSet<>(Arrays.asList(childrenKeys));
        List<Loader> result = new ArrayList<>();
        String childKey = null;
        for (String contentItem : content) {

            Pattern link = Pattern.compile(".*\\* \\[(.*)\\]\\(ema:(.*)\\).*");
            Matcher linkmat = link.matcher(contentItem);
            if (linkmat.matches() && childKey != null) {
                EmaLoader subLoad = new EmaLoader();
                String filename = linkmat.group(1).replace(" ", "_") + ".txt";
                File newfile = new File(file.getPath() + filename);
                subLoad.content = getContent(newfile);
                result.add(subLoad);
            } else {

                Pattern heading = Pattern.compile(".*([a-zA-Z]+).*");
                Matcher headingMat = heading.matcher(contentItem);
                childKey = null;
                if (headingMat.matches()) {
                    String head = headingMat.group();
                    if (keySet.contains(head)) {
                        childKey = head;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Loader getChild(String childkey) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Loader create(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    private List<String> getContent(File file) {
        List<String> result = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(line);
            }
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Artifact load(File file, Tool tool) {
        this.file = file;
        content = getContent(file);

        return tool.load(this);
    }

    @Override
    public void save(File file, Artifact item) {
        // TODO Auto-generated method stub

    }

}
