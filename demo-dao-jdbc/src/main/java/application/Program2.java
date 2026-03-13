package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("==== TEST 1: Department findById 3 ====");
        Department department = departmentDao.findById(3);
        System.out.println(department);

        System.out.println("\n==== TEST 2: Department findAll ====");
        List<Department> list = departmentDao.findAll();
        for (Department d : list) {
            System.out.println(d);
        }

        /*System.out.println("\n==== TEST 3: Department insert ====");
        Department newDep = new Department(null, "TI");
        departmentDao.insert(newDep);
        System.out.println("Inserted! New id = " + newDep.getId());*/

        /*System.out.println("\n==== TEST 4: Department update ====");
        department = departmentDao.findById(7);
        department.setName("Sports");
        departmentDao.update(department);
        System.out.println("Update completed");*/

        System.out.println("\n==== TEST 6: Department delete ====");
        System.out.print("Enter id for delete: ");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Id "+ id + " Deleteded!");
        sc.close();
    }
}
