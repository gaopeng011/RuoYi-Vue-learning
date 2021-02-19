package com.ruoyi.project.shop.vo;

import java.util.List;

public class CatVo {
    private Long value = null;
    private String label = null;
    private List children = null;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }
}
