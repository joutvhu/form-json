package com.joutvhu.spring.formjson.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DemoModel {
    private String name;
    private MultipartFile file;
    private List<ChildModel> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<ChildModel> getChildren() {
        return children;
    }

    public void setChildren(List<ChildModel> children) {
        this.children = children;
    }

    public static class ChildModel {
        private int id;
        private MultipartFile file;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public MultipartFile getFile() {
            return file;
        }

        public void setFile(MultipartFile file) {
            this.file = file;
        }
    }
}
