package com.lh.cloud.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName
 * @Description
 * @Author lh
 * @Date 2024/8/29 15:23
 * @Version
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseTree {
    private String id;
    private String name;
    private String pid;
    private boolean hasChildren;
    private List<BaseTree> children;

    public boolean hasChildren() {
        return children!=null && !children.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTree baseTree = (BaseTree) o;
        return Objects.equals(id, baseTree.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
