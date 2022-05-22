package com.leetcode.www.middle.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode-690:员工的重要性
 * 给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度和 直系下属的 id 。
 * 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的
 * 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在
 * 员工 1 的数据结构中。
 * 现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
 */
public class EmployeeImportance {


    /**
     * 深度优先搜索:先维护雇员id和雇员映射的哈希表，然后进行深度优先搜索，根据id找到对应的雇员，将这个雇员的重要性加到总和，然后找到这个雇员的下属进行深度优先遍历，
     *            直到所有下属遍历完毕，此时total值就是给定员工及其下属的重要性之和
     * 复杂度分析
     *  时间复杂度:O(n),n是员工的数量，每个员工需要需要被遍历一次
     *  空间复杂度:O(n),n是员工的数量，哈希表的需要的空间是O(n),递归调用栈的空间也是O(n)
     * @param employees
     * @param id
     * @return
     */
    public int solution(List<Employee> employees, int id){

        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee e : employees){
            employeeMap.put(e.id, e);
        }

        return dfs(id, employeeMap);
    }


    private int dfs(int id, Map<Integer, Employee> employeeMap){

        Employee cur = employeeMap.get(id);
        int total = cur.importance;
        List<Employee> subordinates = cur.subordinates;
        if (subordinates != null){
            for (Employee e : cur.subordinates){
                total += dfs(e.id, employeeMap);
            }
        }


        return total;
    }

    public static class Employee{

        int id;
        int importance;
        List<Employee> subordinates;
    }

    public static void main(String[] args) {

        EmployeeImportance importance = new EmployeeImportance();

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        Employee employee3 = new Employee();

        employee3.importance = 3;
        employee3.id = 3;
        employee2.importance = 3;
        employee2.id = 2;
        employee1.importance = 5;
        employee1.id = 1;

        List<Employee> employees = new ArrayList<>();
        employees.add(employee2);
        employees.add(employee3);
        employee1.subordinates = employees;

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        int ans = importance.solution(employeeList, 1);
        System.out.println(ans);

    }
}
