package com.lh.cloud.common.util;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName
 * @Description
 * @Author lh
 * @Date 2024/8/29 15:23
 * @Version
 **/
public class TreeUtil {

   /**
    * 将数据格式化为树形
    * @Author lh
    * @date 2024-08-29 16:29:40
    **/
   public static <T extends BaseTree> List<T> buildTree(List<T> data){
      return buildTree(data,"-1");
   }


   /**
    * 将数据格式化为树形
    * @Param 需要格式化的数据
    * @Param 顶级节点ID
    * @Author lh
    * @date 2024-08-29 16:29:40
    **/
   public static <T extends BaseTree> List<T> buildTree(List<T> data, String topId){
      //如果pid为null "" 替换为 -1
      Map<String, List<BaseTree>> parentIdMap = data.stream().collect(Collectors.groupingBy(tree-> {
         if (StrUtil.isBlank(tree.getPid())){
            tree.setPid("-1");
         }
         return tree.getPid();
      }));
      data.forEach(zone -> {zone.setChildren(parentIdMap.get(zone.getId()));zone.setHasChildren(zone.hasChildren());});
      //可以进行扩展 parentId找不到的都可以作为顶级节点 不一定需要-1 修改下方过滤方法即可
      return data.stream().filter(v -> StringUtils.equals(v.getPid(),topId)).collect(Collectors.toList());
   }

   /**
    * 将数据格式化为树形所有的数据不过滤多余数据
    * @Param 需要格式化的数据
    * @Param 顶级节点ID
    * @Author lh
    * @date 2024-08-29 16:29:40
    **/
   public static <T extends BaseTree> List<T> buildTreeAll(List<T> data, String topId){
      //如果pid为null "" 替换为 -1
      Map<String, List<BaseTree>> parentIdMap = data.stream().collect(Collectors.groupingBy(tree-> {
         if (StrUtil.isBlank(tree.getPid())){
            tree.setPid("-1");
         }
         return tree.getPid();
      }));
      data.forEach(zone -> {zone.setChildren(parentIdMap.get(zone.getId()));zone.setHasChildren(zone.hasChildren());});
      //可以进行扩展 parentId找不到的都可以作为顶级节点 不一定需要-1 修改下方过滤方法即可
      return data;
   }


   /**
    * 测试树形数据工具类
    * @Author lh
    * @date 2024-08-29 16:29:22
    **/
  /* public static void main(String[] args) {
      ArrayList<BaseTree> baseTrees = new ArrayList<>();
      baseTrees.add(new BaseTree("1","1","-2",false,null));
      baseTrees.add(new BaseTree("2","2","-2",false,null));
      baseTrees.add(new BaseTree("3","3","1",false,null));
      baseTrees.add(new BaseTree("4","4","1",false,null));
      baseTrees.add(new BaseTree("5","5","2",false,null));
      baseTrees.add(new BaseTree("6","6","2",false,null));
      List<BaseTree> baseTrees1 = buildTree(baseTrees);
      System.out.println(baseTrees1);
   }*/
}
